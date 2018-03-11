/*
 * DefaultPhraseService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.model.Phrase;
import com.aleggeup.confagrid.model.QPhrase;
import com.aleggeup.confagrid.model.QWord;
import com.aleggeup.confagrid.model.Word;
import com.aleggeup.confagrid.model.WordSequence;
import com.aleggeup.confagrid.repository.PhraseRepository;
import com.aleggeup.confagrid.repository.WordRepository;
import com.aleggeup.confagrid.service.PhraseService;

@Service
public class DefaultPhraseService implements PhraseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhraseService.class);

    private final PhraseRepository phraseRepository;

    private final WordRepository wordRepository;

    @Autowired
    public DefaultPhraseService(final PhraseRepository phraseRepository, final WordRepository wordRepository) {
        this.phraseRepository = phraseRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public void save(final Phrase phrase) {
        phraseRepository.save(phrase);
    }

    @Override
    public Phrase phraseFromText(final String text) {
        final Set<PhraseElement> phraseElements = new LinkedHashSet<>();
        final Map<String, Integer> duplicates = new TreeMap<>();
        final String[] phraseWords = text.toUpperCase().split("\\s");

        int sequence = 0;
        for (final String phraseWord : phraseWords) {
            if (phraseWord.trim().length() > 0) {
                if (!duplicates.containsKey(phraseWord)) {
                    duplicates.put(phraseWord, 0);
                    phraseElements.add(new PhraseElement(phraseWord, 0, sequence++));
                } else {
                    final int dupCount = duplicates.get(phraseWord);
                    duplicates.replace(phraseWord, dupCount + 1);
                    phraseElements.add(new PhraseElement(phraseWord, dupCount + 1, sequence++));
                }
            }
        }

        final QPhrase phrase = QPhrase.phrase;
        final Phrase foundPhrase = phraseRepository.findOne(phrase.raw.equalsIgnoreCase(text));

        if (foundPhrase == null) {
            final List<WordSequence> foundWords = new LinkedList<>();
            final Phrase newPhrase = new Phrase(text);

            phraseElements.forEach(phraseElement -> {
                LOGGER.info(" ---> " + phraseElement.toString());
                final QWord word = QWord.word;
                final Word foundWord = wordRepository.findOne(word.text.eq(phraseElement.text)
                    .and(word.occurrence.eq(phraseElement.occurrence)));

                if (foundWord == null) {
                    final Word newWord = wordRepository.save(new Word(phraseElement.text, phraseElement.occurrence));
                    foundWords.add(new WordSequence(newPhrase, newWord, phraseElement.sequence));
                } else {
                    foundWords.add(new WordSequence(newPhrase, foundWord, phraseElement.sequence));
                }
            });

            foundWords.forEach(newPhrase::addWord);

            LOGGER.info(" ---> (pre-save) > " + newPhrase.toString());

            final Phrase updatedPhrase = phraseRepository.saveAndFlush(newPhrase);
            LOGGER.info(" ---> (updated) > " + updatedPhrase.toString());
            return newPhrase;

        } else {
            LOGGER.info(" ---> (found) > " + foundPhrase.toString());
            return foundPhrase;
        }
    }

    @Override
    public long count() {
        return phraseRepository.count();
    }

    private class PhraseElement {
        private final String text;

        private final int occurrence;

        private final int sequence;

        PhraseElement(final String text, final int occurrence, final int sequence) {
            this.text = text;
            this.occurrence = occurrence;
            this.sequence = sequence;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final PhraseElement that = (PhraseElement) o;

            return new EqualsBuilder()
                .append(occurrence, that.occurrence)
                .append(text, that.text)
                .append(sequence, that.sequence)
                .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                .append(text)
                .append(occurrence)
                .append(sequence)
                .toHashCode();
        }

        @Override
        public String toString() {
            return String.format("PhraseElement{text='%s', occurrence=%d, sequence=%d}", text, occurrence, sequence);
        }
    }
}
