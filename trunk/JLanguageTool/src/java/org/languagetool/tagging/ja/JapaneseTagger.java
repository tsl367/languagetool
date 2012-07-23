/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.tagging.ja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.tagging.Tagger;

public class JapaneseTagger implements Tagger {

  @Override
  public List<AnalyzedTokenReadings> tag(List<String> sentenceTokens) throws IOException {
    final List<AnalyzedTokenReadings> tokenReadings = new ArrayList<AnalyzedTokenReadings>();
    int pos = 0;

    for (String word : sentenceTokens) {

      final List<AnalyzedToken> l = new ArrayList<AnalyzedToken>();

      AnalyzedToken at = asAnalyzedToken(word);
      l.add(at);
      tokenReadings.add(new AnalyzedTokenReadings(l, pos));
      pos += at.getToken().length();
    }

    return tokenReadings;
  }

  @Override
  public final AnalyzedTokenReadings createNullToken(final String token, final int startPos) {
    return new AnalyzedTokenReadings(new AnalyzedToken(token, null, null), startPos);
  }

  @Override
  public AnalyzedToken createToken(String token, String posTag) {
    return new AnalyzedToken(token, posTag, null);
  }

  private AnalyzedToken asAnalyzedToken(final String word) {
    if (word.indexOf(" ") < 0) {
      return new AnalyzedToken(" ", null, null);
    }
    String[] parts = word.split(" ");
    return new AnalyzedToken(parts[0], parts[1], parts[2]);
  }

  public static final String arrayToString(byte[] bytes) {
    StringBuffer buff = new StringBuffer();

    for (int i = 0; i < bytes.length; i++) {
      buff.append(bytes[i] + " ");
    }
    return buff.toString();
  }

}

