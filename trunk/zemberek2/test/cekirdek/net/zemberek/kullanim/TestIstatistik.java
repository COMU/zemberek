package net.zemberek.kullanim;

import net.zemberek.araclar.TimeTracker;
import net.zemberek.araclar.turkce.TurkceMetinOkuyucu;
import net.zemberek.erisim.Zemberek;
import net.zemberek.istatistik.DosyaRaporlayici;
import net.zemberek.istatistik.HeceIstatistikleri;
import net.zemberek.istatistik.Istatistikler;
import net.zemberek.istatistik.KonsolRaporlayici;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.Kelime;

public class TestIstatistik {

    private static Zemberek zemberek;
    private static Istatistikler istatistikler;

    public static void istatistikCikar(String dosya) {
        System.out.println("Okunan dosya: " + dosya);
        TurkceMetinOkuyucu tmo = new TurkceMetinOkuyucu();
        tmo.setStatistics(istatistikler);
        HeceIstatistikleri heceIst = istatistikler.getHeceIstatistikleri();
        String[] yazi = tmo.MetinOku(dosya);
        for (int i = 0; i < yazi.length; i++) {
            try {
                Kelime[] kelimeler = zemberek.kelimeCozumle(yazi[i]);
                if (kelimeler != null && kelimeler.length > 0) {
                    final Kelime ilk = kelimeler[0];
                    istatistikler.kokIstatistikGuncelle(ilk.kok(), ilk);
                    istatistikler.karakterIstatistikGuncelle(ilk.kok());
                    String[] heceler = zemberek.hecele(yazi[i]);
                    for (int j = 0; j < heceler.length; j++) {
                        heceIst.guncelle(heceler[j]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(TimeTracker.getElapsedTimeString("ist"));
    }

    public static void raporla() {
        istatistikler.sonlandir();
        istatistikler.setLimit(1000,100,100);
        KonsolRaporlayici konsolRaporlayici = new KonsolRaporlayici(istatistikler);
        konsolRaporlayici.raporla(System.out, "UTF-8");
        DosyaRaporlayici dr = new DosyaRaporlayici(istatistikler, "istatistik.txt");
        dr.raporla();
    }

    public static void main(String[] args) {
        zemberek = new Zemberek(new TurkiyeTurkcesi());
        istatistikler = new Istatistikler(zemberek.dilBilgisi());
        TimeTracker.startClock("ist");
        istatistikCikar("kitap/tolstoy_cocukluk_ve_genclik_yillari.txt");
        istatistikCikar("kitap/Tolstoy_Dirilis.txt");
        raporla();
        TimeTracker.stopClock("ist");
    }
}
