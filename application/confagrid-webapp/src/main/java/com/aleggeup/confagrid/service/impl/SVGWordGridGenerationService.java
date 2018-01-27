/*
 * SVGWordGridGenerationService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.batik.svggen.SVGSyntax;
import org.apache.batik.svggen.StyleHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGSVGElement;

import com.aleggeup.confagrid.service.WordGridGenerationService;

@Service
public class SVGWordGridGenerationService implements WordGridGenerationService {

    public String test() throws SVGGraphics2DIOException, UnsupportedEncodingException, TransformerException {

        final String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
        Document myFactory = domImpl.createDocument(svgNS, "svg", null);

        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(myFactory);

        ctx.setEmbeddedFontsOn(true);
        ctx.setComment("Generated by Confagrid [ A Legge Up ] with Batik SVG Generator");
        CDATASection styleSheet = myFactory.createCDATASection("\n@font-face {\n" +
                "  font-family: \"Cooper\";\n" +
                "  src: url(data:application/octet-stream;charset=utf-8;base64,AAAA) format('truetype');\n" +
                "}\n");

        // ctx.setStyleHandler(new StyleSheetStyleHandler(styleSheet));

        // Create an instance of the SVG Generator.
        final boolean textAsShapes = false;
        final SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx, textAsShapes);

        // Ask the test to render into the SVG Graphics2D implementation.
        paint(svgGenerator);

        SVGSVGElement root = (SVGSVGElement) svgGenerator.getRoot();
        Element defs = root.getElementById(SVGSyntax.ID_PREFIX_GENERIC_DEFS);
        Element style = myFactory.createElementNS(SVGSyntax.SVG_NAMESPACE_URI, SVGSyntax.SVG_STYLE_TAG);
        style.setAttributeNS(null, SVGSyntax.SVG_TYPE_ATTRIBUTE, "text/css");
        style.appendChild(styleSheet);
        defs.appendChild(style);

        Node lastNode = root.getLastChild();

        Element txtElem = myFactory.createElementNS(svgNS, "text");
        txtElem.setAttributeNS(svgNS, "style", "font-family:Cooper;font-size:32px;stroke:#0000FF;#fill:#00ff00;");
        txtElem.setAttributeNS(svgNS, "x", "50");
        txtElem.setAttributeNS(svgNS, "y", "50");
        txtElem.setTextContent("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz");
        lastNode.appendChild(txtElem);

        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        final boolean useCSS = true;
        final boolean escape = true;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Writer out = new OutputStreamWriter(baos, "UTF-8");
        svgGenerator.stream(root, out, useCSS, escape);

        return baos.toString("UTF-8");
    }

    private void setupFont(final Element defs, SVGGraphics2D svgGenerator, final Document document, final Element svg) {
        final String svgNS = "http://www.w3.org/2000/svg";
        // SVGSVGElement root = (SVGSVGElement)svgGenerator.getRoot();

        Element fontface = document.createElementNS(svgNS, "font-face");
        fontface.setAttributeNS(null, "font-family", "DroidSansRegular");

        Element fontfacesrc = document.createElementNS(svgNS, "font-face-src");
        Element fontfaceuri = document.createElementNS(svgNS, "font-face-uri");

        fontfaceuri.setAttributeNS(svgNS, "xlink:href", "fonts/DroidSans-webfont.svg#DroidSansRegular");

        Element fontfaceformat = document.createElementNS(svgNS, "font-face-format");
        fontfaceformat.setAttributeNS(svgNS, "string", "svg");

        fontfaceuri.appendChild(fontfaceformat);
        fontfacesrc.appendChild(fontfaceuri);
        fontface.appendChild(fontfacesrc);
        defs.appendChild(fontface);

        // svgRoot.appendChild(defs);
    }

    public void paint(final Graphics2D g2d) {
        g2d.setPaint(new Color(0, 0, 0, 0.1f));
        g2d.fill(new Rectangle(0, 0, 800, 600));
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.setPaint(new Color(0, 0, 0, 1.0f));

        g2d.drawRect(0, 0, 800, 600);
        for (int i = 0; i < 16 + 1; ++i) {
            g2d.drawLine(i * 50, 0, i * 50, 600);
        }
        for (int i = 0; i < 12 + 1; ++i) {
            g2d.drawLine(0, i * 50, 800, i * 50);
        }
    }

    private class StyleSheetStyleHandler implements StyleHandler {

        // The CDATA section that holds the CSS stylesheet.
        private CDATASection styleSheet;

        // Build the handler with a reference to the stylesheet section.
        public StyleSheetStyleHandler(CDATASection styleSheet) {
            this.styleSheet = styleSheet;
        }

        @Override
        @SuppressWarnings("rawtypes")
        public void setStyle(Element element, Map styleMap, SVGGeneratorContext generatorContext) {
            Iterator iter = styleMap.keySet().iterator();

            // Create a new class in the style sheet.
            String id = generatorContext.getIDGenerator().generateID("C");
            styleSheet.appendData("." + id + " {");

            if (id.equals("C9")) {
                styleSheet.appendData("stroke:rgb(255,0,0);}\n");
            } else {
                // Append each key/value pair.
                while (iter.hasNext()) {
                    String key = (String) iter.next();
                    String value = (String) styleMap.get(key);
                    styleSheet.appendData(key + ":" + value + ";");
                }
                styleSheet.appendData("}\n");
            }

            // Reference the stylesheet class on the element to be styled.
            element.setAttributeNS(null, "class", id);
        }
    }
}