package com.aleggeup.confagrid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.aleggeup.confagrid.model.WordSequence;

public interface WordSequenceRepository
    extends JpaRepository<WordSequence, UUID>, QueryDslPredicateExecutor<WordSequence> {
}
