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
 *  The Original Code is Zemberek Doðal Dil Ýþleme Kütüphanesi.
 *
 *  The Initial Developer of the Original Code is
 *  Ahmet A. Akýn, Mehmet D. Akýn.
 *  Portions created by the Initial Developer are Copyright (C) 2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  ***** END LICENSE BLOCK *****
 */

package net.zemberek.bilgi.araclar;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.zemberek.bilgi.KaynakYukleyici;
import net.zemberek.yapi.KelimeTipi;
import net.zemberek.yapi.Kok;
import net.zemberek.yapi.kok.KokOzelDurumBilgisi;
import net.zemberek.yapi.kok.KokOzelDurumu;

/**
 * User: ahmet
 * Date: Jan 15, 2006
 */
public class IkiliKokOkuyucu implements KokOkuyucu {

    private DataInputStream dis;
    private KokOzelDurumBilgisi ozelDurumlar;

    public IkiliKokOkuyucu(InputStream is, KokOzelDurumBilgisi ozelDurumlar) {
        dis = new DataInputStream(new BufferedInputStream(is));
        this.ozelDurumlar = ozelDurumlar;
    }

    public IkiliKokOkuyucu(String dosyaAdi, KokOzelDurumBilgisi ozelDurumlar) throws IOException {
        InputStream fis = new KaynakYukleyici("UTF-8").getStream(dosyaAdi);
        dis = new DataInputStream(new BufferedInputStream(fis));
        this.ozelDurumlar = ozelDurumlar;
    }

    /**
     * Sözlükteki Tüm kökleri okur ve bir ArrayList olarak döndürür.
     */
    public List<Kok> hepsiniOku() throws IOException {
        ArrayList<Kok> list = new ArrayList<Kok>();
        Kok kok;
        while ((kok = oku()) != null) {
            list.add(kok);
        }
        dis.close();
        return list;
    }

    /**
     * İkili (Binary) sözlükten bir kök okur. çağrıldıkça bir sonraki kökü alır.
     *
     * @return bir sonraki kök. Eğer okunacak kök kalmamışsa null
     */
    public Kok oku() throws IOException {

        String icerik;
        //kok icerigini oku. eger dosya sonuna gelinmisse (EOFException) null dondur.
        try {
            icerik = dis.readUTF();
        } catch (EOFException e) {
            dis.close();
            return null;
        }
        String asil = dis.readUTF();

        // Tip bilgisini oku (1 byte)
        KelimeTipi tip = KelimeTipi.getTip(dis.read());
        Kok kok = new Kok(icerik, tip);

        if (asil.length() != 0)
            kok.setAsil(asil);

        kok.setKisaltmaSonSeslisi(dis.readChar());

        // Özel durum sayısını (1 byte) ve ozel durumlari oku.
        int ozelDurumSayisi = dis.read();
        for (int i = 0; i < ozelDurumSayisi; i++) {
            int ozelDurum = dis.read();
            KokOzelDurumu oz = ozelDurumlar.ozelDurum(ozelDurum);
            kok.ozelDurumEkle(oz);
        }
        int frekans = dis.readInt();
        if (frekans != 0) {
            kok.setFrekans(frekans);
        }
        return kok;
    }
}
