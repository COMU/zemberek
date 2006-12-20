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

import net.zemberek.erisim.Zemberek;
import net.zemberek.islemler.TurkceYaziTesti;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;

public class TestTurkceMi {

    private static Zemberek zemberek;

    public static void turkceMi(String str) {
        System.out.print("T�rk�e testi yap�lan metin: " + str + " Sonu� : ");
        int sonuc = zemberek.dilTesti(str);
        switch (sonuc) {
            case TurkceYaziTesti.KESIN:
                System.out.println("T�rk�e");
                break;
            case TurkceYaziTesti.YUKSEK:
                System.out.println("Y�ksek oranda T�rk�e");
                break;
            case TurkceYaziTesti.ORTA:
                System.out.println("K�smen T�rk�e");
                break;
            case TurkceYaziTesti.AZ:
                System.out.println("�ok az T�rk�e kelime i�eriyor");
                break;
            case TurkceYaziTesti.HIC:
                System.out.println("T�rk�e de�il");
                break;
        }
    }

    public static void main(String[] args) {
        zemberek = new Zemberek(new TurkiyeTurkcesi());
        turkceMi("Bug�n �ok ne�eliyim, hayat �ok g�zel");
        turkceMi("Abi yapt���m�z son commitler nasty regresyonlara yol a�m��. we are doomed yani.");
        turkceMi("Bugun cok neseliyim, hayat cok guzel");
        turkceMi("Obviously Java kicks some serious butts nowadays.");
    }
}
