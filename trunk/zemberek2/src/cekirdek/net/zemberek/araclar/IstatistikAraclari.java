/*
 * Created on 19.Ara.2004
 *
 */
package net.zemberek.araclar;

import java.text.DecimalFormat;

/**
 * Bazi basit  yuzde hesaplamalar�nda kullannilan fonksiyonlar. 
 * TODO: istatistik paketine alinmasi dusunulebilir.
 * @author MDA
 */
public class IstatistikAraclari {

    public static DecimalFormat df = new DecimalFormat("#0.000");
    public static DecimalFormat df2 = new DecimalFormat("#0.00000");

    /**
     * Verilen giri�in toplam�n y�zde ka��n� olu�turdu�unu d�nd�r�r.
     * @param input
     * @param total
     * @return input, toplamin %kacini olusturuyor sa o de�er.
     * E�er total 0 ise -1 
     * 
     */
    public static double yuzdeHesapla(long input, long total) {
        if (total == 0) return -1;
        return (double) (input * 100) / total;
    }

    /**
     * yuzde hesaplamas�n�n ayn�s�, sadece formatl� String olarak d�nd�r�r.
     * @param input
     * @param total
     * @return
     */
    public static String yuzdeHesaplaStr(long input, long total) {
        if (total == 0) return "0";
        return df.format((double) (input * 100) / total);
    }

    /**
     * Gene yuzde hesab�. ama bu sefer virg�lden sonra 5 basamak hassasiyet
     * TODO: ismi hatal� asl�nda. Onbinde-bir hassasiyetle y�zde hesapla gibi bir �ey olmal�yd�.
     * @return
     */
    public static String onbindeHesaplaStr(long input, long total) {
        if (total == 0) return "0";
        return df2.format((double) (input * 100) / total);
    }
}
