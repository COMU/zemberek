package net.zemberek.bilgi.araclar;

import net.zemberek.yapi.Kok;
import net.zemberek.yapi.kok.KokOzelDurumu;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * User: ahmet
 * Date: Jan 16, 2006
 */
public class IkiliKokYazici implements KokYazici {

    DataOutputStream dos;

    public IkiliKokYazici(String dosyaAdi) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        dos = new DataOutputStream(new BufferedOutputStream(fos));
    }

    public void yaz(List kokler) throws IOException {
        for (Iterator it = kokler.iterator(); it.hasNext();) {
            Kok kok = (Kok) it.next();
            // K�k i�erigi
            dos.writeUTF(kok.icerik());

            // asil icerik ozel karakterler barindiran koklerde olur. yoksa bos string yaz.
            if (kok.asil() != null) {
                // K�k asil i�erigi
                dos.writeUTF(kok.asil());
            } else
                dos.writeUTF("");

            // K�k tipi
            dos.write(kok.tip().getIndeks());

            dos.writeChar(kok.getKisaltmaSonSeslisi());

            KokOzelDurumu[] ozd = kok.ozelDurumDizisi();
            dos.write(ozd.length);
            for (KokOzelDurumu ozelDurum : ozd) {
                dos.write(ozelDurum.indeks());
            }
            // kullanim frekansi
            dos.writeInt(kok.getFrekans());

        }
        dos.close();
    }

}