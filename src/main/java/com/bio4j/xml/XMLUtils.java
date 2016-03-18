package com.bio4j.xml;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.StringReader;

import java.io.*;

public final class XMLUtils {

  public static final Element parseXMLFrom(String rep)
    throws JDOMException, java.io.IOException
  {
    final SAXBuilder sax = new SAXBuilder();
    final Document doc = sax.build(new StringReader(rep));
    return doc.getRootElement();
  }

  public static final Element uniProtEntryFrom(String firstLine, BufferedReader reader)
    throws JDOMException, java.io.IOException
  {
    final StringBuilder entryStBuilder = new StringBuilder();
    entryStBuilder.append(firstLine);

    String line;

    while((line = reader.readLine()) != null && !line.trim().startsWith("</entry>")) {

      entryStBuilder.append(line);
    }

    entryStBuilder.append(line);

    final Element entryXMLElem = XMLUtils.parseXMLFrom(entryStBuilder.toString());
    entryStBuilder.delete(0, entryStBuilder.length());

    return entryXMLElem;
  }
}
