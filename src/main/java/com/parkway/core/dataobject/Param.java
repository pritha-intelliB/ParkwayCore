package com.parkway.core.dataobject;

import java.io.Serializable;
import java.text.StringCharacterIterator;

public class Param  implements Serializable
{
  private String name = "";
  private String value = "";
  private String type = "";
  private String format = "";
  private String formatValue = "";
  private String accessType = "";

  private transient Object objectValue = null;
  private transient boolean isObjectSet = false;

  public Param(String name, String value, String type, String format, String formatValue) {
    this.name = name;
    this.value = value;
    this.type = type;
    this.format = format;
    this.formatValue = formatValue;
  }

  public Param(String name, String value, String type, String format, String formatValue, String accessType)
  {
    this.name = name;
    this.value = value;
    this.type = type;
    this.format = format;
    this.formatValue = formatValue;
    this.accessType = accessType;
  }

  public Param(String name, String value, String type) {
    this.name = name;
    this.value = value;
    this.type = type;
  }
  public Param() {
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getFormatValue() {
    return this.formatValue;
  }

  public void setFormatValue(String formatValue) {
    this.formatValue = formatValue;
  }

  public String getAccessType() {
    return this.accessType;
  }

  public void setAccessType(String accessType) {
    this.accessType = accessType;
  }

  public String getEscapeXMLValue() {
    if ((this.value != null) && (this.value.trim().length() > 0)) {
      return forXML(this.value);
    }
    return this.value;
  }

  public void setObjectValue(Object objectValue)
  {
    this.objectValue = objectValue;
    this.isObjectSet = true;
  }

  public Object getObjectValue()
  {
    return this.objectValue;
  }

  public boolean isObjectSet()
  {
    return this.isObjectSet;
  }

  private static String forXML(String aText)
  {
    StringBuilder result = new StringBuilder();
    if (aText != null) {
      StringCharacterIterator iterator = new StringCharacterIterator(aText);
      char character = iterator.current();
      while (character != 65535) {
        if (character == '<')
          result.append("&lt;");
        else if (character == '>')
          result.append("&gt;");
        else if (character == '"')
          result.append("&quot;");
        else if (character == '\'')
          result.append("&#039;");
        else if (character == '&') {
          result.append("&amp;");
        }
        else
        {
          result.append(character);
        }
        character = iterator.next();
      }
    }
    return result.toString();
  }

  public static enum ParamType
  {
    TEXT, IMAGE;
  }
}
