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

package net.zemberek.kullanim;

import net.zemberek.araclar.JaroWinkler;
import net.zemberek.araclar.MetinAraclari;

import java.text.DecimalFormat;

public class TestBenzerlik {

    private static JaroWinkler jaro = new JaroWinkler();
    public static DecimalFormat df = new DecimalFormat("#0.000");

    public static void levenshtein(String str1, String str2) {
        System.out.println("Levenshtein d�zeltme mesafesi (" + str1 + " ve " + str2 + ") = " +
                MetinAraclari.editDistance(str1, str2));
    }

    public static void jaro(String str1, String str2) {
        System.out.println("Jaro-Winkler benzerlik oran� (" + str1 + " ve " + str2 + ") = " +
                df.format(jaro.benzerlikOrani(str1, str2)));
    }

    public static void main(String[] args) {
        levenshtein("elma", "lma");
        levenshtein("elma", "kelma");
        levenshtein("elma", "elpa");
        levenshtein("elma", "emla");
        levenshtein("elma", "elti");
        System.out.println();
        jaro("elma", "lma");
        jaro("elma", "kelma");
        jaro("elma", "elpa");
        jaro("elma", "emla");
        jaro("elma", "elti");
    }
}
