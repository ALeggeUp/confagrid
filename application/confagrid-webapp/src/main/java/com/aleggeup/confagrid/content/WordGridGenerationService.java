/*
 * WordGridGenerationService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.content;

import java.io.UnsupportedEncodingException;

import javax.xml.transform.TransformerException;

import org.apache.batik.svggen.SVGGraphics2DIOException;

public interface WordGridGenerationService {

    String svgTest() throws SVGGraphics2DIOException, UnsupportedEncodingException, TransformerException;
}
