package net.zemberek.islemler;

import net.zemberek.TemelTest;

/**
 */
public class TestTurkcedenDonusturucu extends TemelTest {

    public void testToAscii() {
        AsciiDonusturucu donusturucu = new AsciiDonusturucu(alfabe);
        String turkce = "abci\u00e7\u011f\u0131\u00f6\u015f\u00fc";
        String sonuc = donusturucu.toAscii(turkce);
        assertEquals("abcicgiosu", sonuc);
        String abuk = "32432aas_";
        sonuc = donusturucu.toAscii(abuk);
        assertEquals("32432aas_", sonuc);
    }

}
