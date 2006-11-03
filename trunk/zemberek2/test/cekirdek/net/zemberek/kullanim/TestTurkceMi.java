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
