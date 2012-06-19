package com.vejagol.util;

import java.util.HashMap;

public class StringUtils {

	private StringUtils() {
	}

	private static HashMap<String, String> htmlEntities;
	static {
		htmlEntities = new HashMap<String, String>();
		htmlEntities.put("&lt;", "<");
		htmlEntities.put("&gt;", ">");
		htmlEntities.put("&amp;", "&");
		htmlEntities.put("&quot;", "\"");
		htmlEntities.put("&atilde;", "�");
		htmlEntities.put("&Atilde;", "�");
		htmlEntities.put("&aacute;", "�");
		htmlEntities.put("&agrave;", "�");
		htmlEntities.put("&Agrave;", "�");
		htmlEntities.put("&acirc;", "�");
		htmlEntities.put("&auml;", "�");
		htmlEntities.put("&Auml;", "�");
		htmlEntities.put("&Acirc;", "�");
		htmlEntities.put("&aring;", "�");
		htmlEntities.put("&Aring;", "�");
		htmlEntities.put("&aelig;", "�");
		htmlEntities.put("&AElig;", "�");
		htmlEntities.put("&ccedil;", "�");
		htmlEntities.put("&Ccedil;", "�");
		htmlEntities.put("&eacute;", "�");
		htmlEntities.put("&Eacute;", "�");
		htmlEntities.put("&egrave;", "�");
		htmlEntities.put("&Egrave;", "�");
		htmlEntities.put("&ecirc;", "�");
		htmlEntities.put("&Ecirc;", "�");
		htmlEntities.put("&euml;", "�");
		htmlEntities.put("&Euml;", "�");
		htmlEntities.put("&iuml;", "�");
		htmlEntities.put("&Iuml;", "�");
		htmlEntities.put("&iacute;", "�");
		htmlEntities.put("&Iacute;", "�");
		htmlEntities.put("&ocirc;", "�");
		htmlEntities.put("&Ocirc;", "�");
		htmlEntities.put("&ouml;", "�");
		htmlEntities.put("&Ouml;", "�");
		htmlEntities.put("&oslash;", "�");
		htmlEntities.put("&Oslash;", "�");
		htmlEntities.put("&szlig;", "�");
		htmlEntities.put("&ugrave;", "�");
		htmlEntities.put("&Ugrave;", "�");
		htmlEntities.put("&uacute;", "�");
		htmlEntities.put("&Uacute;", "�");
		htmlEntities.put("&ucirc;", "�");
		htmlEntities.put("&Ucirc;", "�");
		htmlEntities.put("&uuml;", "�");
		htmlEntities.put("&Uuml;", "�");
		htmlEntities.put("&ntilde;", "�");
		htmlEntities.put("&Ntilde;", "�");
		htmlEntities.put("&nbsp;", " ");
		htmlEntities.put("&copy;", "\u00a9");
		htmlEntities.put("&oacute;", "�");
		htmlEntities.put("&Oacute;", "�");
		htmlEntities.put("&reg;", "\u00ae");
		htmlEntities.put("&euro;", "\u20a0");
	}

	public static final String unescapeHTML(String source) {
		int i, j;

		boolean continueLoop;
		int skip = 0;
		do {
			continueLoop = false;
			i = source.indexOf("&", skip);
			if (i > -1) {
				j = source.indexOf(";", i);
				if (j > i) {
					String entityToLookFor = source.substring(i, j + 1);
					String value = (String) htmlEntities.get(entityToLookFor);
					if (value != null) {
						source = source.substring(0, i) + value
								+ source.substring(j + 1);
						continueLoop = true;
					} else if (value == null) {
						skip = i + 1;
						continueLoop = true;
					}
				}
			}
		} while (continueLoop);
		return source;
	}

	public static final String escapeHTML(String s) {
		StringBuffer sb = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '�':
				sb.append("&agrave;");
				break;
			case '�':
				sb.append("&Agrave;");
				break;
			case '�':
				sb.append("&acirc;");
				break;
			case '�':
				sb.append("&Acirc;");
				break;
			case '�':
				sb.append("&auml;");
				break;
			case '�':
				sb.append("&Auml;");
				break;
			case '�':
				sb.append("&aring;");
				break;
			case '�':
				sb.append("&Aring;");
				break;
			case '�':
				sb.append("&aelig;");
				break;
			case '�':
				sb.append("&AElig;");
				break;
			case '�':
				sb.append("&ccedil;");
				break;
			case '�':
				sb.append("&Ccedil;");
				break;
			case '�':
				sb.append("&eacute;");
				break;
			case '�':
				sb.append("&Eacute;");
				break;
			case '�':
				sb.append("&egrave;");
				break;
			case '�':
				sb.append("&Egrave;");
				break;
			case '�':
				sb.append("&ecirc;");
				break;
			case '�':
				sb.append("&Ecirc;");
				break;
			case '�':
				sb.append("&euml;");
				break;
			case '�':
				sb.append("&Euml;");
				break;
			case '�':
				sb.append("&iuml;");
				break;
			case '�':
				sb.append("&Iuml;");
				break;
			case '�':
				sb.append("&ocirc;");
				break;
			case '�':
				sb.append("&Ocirc;");
				break;
			case '�':
				sb.append("&ouml;");
				break;
			case '�':
				sb.append("&Ouml;");
				break;
			case '�':
				sb.append("&oacute;");
				break;
			case '�':
				sb.append("&Oacute;");
				break;
			case '�':
				sb.append("&oslash;");
				break;
			case '�':
				sb.append("&Oslash;");
				break;
			case '�':
				sb.append("&szlig;");
				break;
			case '�':
				sb.append("&ugrave;");
				break;
			case '�':
				sb.append("&Ugrave;");
				break;
			case '�':
				sb.append("&ucirc;");
				break;
			case '�':
				sb.append("&Ucirc;");
				break;
			case '�':
				sb.append("&uuml;");
				break;
			case '�':
				sb.append("&Uuml;");
				break;
			case '�':
				sb.append("&ntilde;");
				break;
			case '�':
				sb.append("&Ntilde;");
				break;
			case '�':
				sb.append("&reg;");
				break;
			case '�':
				sb.append("&copy;");
				break;
			case '�':
				sb.append("&euro;");
				break;
			// be carefull with this one (non-breaking whitee space)
			//case ' ':
				//sb.append("&nbsp;");
				//break;

			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public static void main(String args[]) throws Exception {
		// to see accented character to the console (Windows DOS Shell)
		java.io.PrintStream ps = new java.io.PrintStream(System.out, true,
				"Cp850");
		String test = "&copy; 2007  R&eacute;al Gagnon &lt;www.rgagnon.com&gt;";
		ps.println(test + "\n-->\n" + unescapeHTML(test));

		/*
		 * output ((Windows DOS Shell): &copy; 2007 R&eacute;al Gagnon
		 * &lt;www.rgagnon.com&gt; --> � 2007 R�al Gagnon <www.rgagnon.com>
		 */
	}
}