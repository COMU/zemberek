package net.zemberek.islemler.cozumleme;

import net.zemberek.yapi.HarfDizisi;

/**
 */
public class AsciiToleransliHDKiyaslayici implements HarfDizisiKiyaslayici {
    public final boolean kiyasla(HarfDizisi h1, HarfDizisi h2) {
        return h1.asciiToleransliKiyasla(h2);
    }

    public final boolean bastanKiyasla(HarfDizisi h1, HarfDizisi h2) {
        return h1.asciiToleransliBastanKiyasla(h2);
    }

    public final boolean aradanKiyasla(HarfDizisi h1, HarfDizisi h2, int index) {
        return h1.asciiToleransliAradanKiyasla(index, h2);
    }


}
