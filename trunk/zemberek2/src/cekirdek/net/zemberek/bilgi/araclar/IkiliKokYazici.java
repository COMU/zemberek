/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.bilgi.araclar;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.zemberek.yapi.Kok;
import net.zemberek.yapi.kok.KokOzelDurumu;

public class IkiliKokYazici implements KokYazici {

    DataOutputStream dos;

    public IkiliKokYazici(String dosyaAdi) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        dos = new DataOutputStream(new BufferedOutputStream(fos));
    }

    public void yaz(List<Kok> kokler) throws IOException {

        for (Kok kok : kokler) {
            // Kök içerigi
            dos.writeUTF(kok.icerik());

            // asil icerik ozel karakterler barindiran koklerde olur. yoksa bos string yaz.
            if (kok.asil() != null) {
                // Kök asil içerigi
                dos.writeUTF(kok.asil());
            } else
                dos.writeUTF("");

            // Kök tipi
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