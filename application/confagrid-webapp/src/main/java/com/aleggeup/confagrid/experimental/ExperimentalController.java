/*
 * ExperimentalController.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.experimental;

import java.io.UnsupportedEncodingException;

import javax.xml.transform.TransformerException;

import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleggeup.confagrid.content.WordGridGenerationService;

@RestController
@RequestMapping("/experimental")
public class ExperimentalController {

    private final WordGridGenerationService wordGridGenerationService;

    @Autowired
    public ExperimentalController(final WordGridGenerationService wordGridGenerationService) {
        this.wordGridGenerationService = wordGridGenerationService;
    }

    @RequestMapping(value = "svgtest", method = RequestMethod.GET, produces = "image/svg+xml")
    public @ResponseBody
    String svgTest()
        throws SVGGraphics2DIOException, UnsupportedEncodingException, TransformerException {
        return wordGridGenerationService.svgTest();
    }
}
