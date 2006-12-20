/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is Zemberek Do�al Dil ��leme K�t�phanesi.
 *
 *  The Initial Developer of the Original Code is
 *  Ahmet A. Ak�n, Mehmet D. Ak�n.
 *  Portions created by the Initial Developer are Copyright (C) 2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  ***** END LICENSE BLOCK *****
 */

package net.zemberek.araclar.turkce;

import net.zemberek.araclar.TimeTracker;

/**
 */
public class TestTurkishTokenStreamPerf {

    public void testNextWordPerf() {
        TimeTracker.startClock("x");
        TurkishTokenStream tstream = new TurkishTokenStream("kaynaklar/tr/metinler/Frank_Herbert_Dune1.txt", null);
        String s = null;
        int wordCount = 0;
        while ((s = tstream.nextWord()) != null) {
            wordCount++;
        }
        System.out.println("Kelime sayisi: " + wordCount);
        System.out.println("Toplam islem suresi:" + TimeTracker.stopClock("x"));

    }


    public void testNextSentencePerf() {
        TimeTracker.startClock("x");
        TurkishTokenStream tstream = new TurkishTokenStream("kaynaklar/tr/metinler/Frank_Herbert_Dune1.txt", null);
        String s = null;
        int sentenceCount = 0;
        while ((s = tstream.nextSentence()) != null) {
            sentenceCount++;
        }
        System.out.println("Cumle sayisi: " + sentenceCount);
        System.out.println("Toplam islem suresi:" + TimeTracker.stopClock("x"));
    }
}
