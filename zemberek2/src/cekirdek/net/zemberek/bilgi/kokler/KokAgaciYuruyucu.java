/*
 *  ***** BEGIN LICENSE BLOCK *****
 *
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
 *  The Original Code is "Zemberek Dogal Dil Isleme Kutuphanesi"
 *
 *  The Initial Developer of the Original Code is
 *  Ahmet A. Akin, Mehmet D. Akin.
 *  Portions created by the Initial Developer are Copyright (C) 2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 *  ***** END LICENSE BLOCK *****
 */

/*
 * Created on 17.Nis.2005
 *
 */
package net.zemberek.bilgi.kokler;

import net.zemberek.araclar.IstatistikAraclari;
import net.zemberek.araclar.TimeTracker;
import net.zemberek.bilgi.araclar.IkiliKokOkuyucu;
import net.zemberek.bilgi.araclar.KokOkuyucu;
import net.zemberek.yapi.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Sadece Kök ağacının istatistiklerini elde etmek için yazılmış basit bir yürüyücü.
 * @author MDA
 *
 */
public class KokAgaciYuruyucu {
    int walkCount = 0;
    int distanceCalculationCount = 0;
    private AgacSozluk sozluk;

    int dugumSayisi = 0;
    int kokTasiyanDugumSayisi = 0;
    int esSesliTasiyanDugumSayisi = 0;
    int ucDugumSayisi  = 0;
    int dugumSayilari[] = new int[50];
    Set<Kok> set;

    /**
     *
     * @param sozluk Taranacak sozluk
     * @param set Yurume sırasında bulunan düğümler toplanmak istiyorsa buraya bir set gönderilir.
     * istenmiyorsa null verilir.
     */
    public KokAgaciYuruyucu(AgacSozluk sozluk, Set<Kok> set)
    {
        this.sozluk =sozluk;
        this.set = set;
    }

    public int getWalkCount()
    {
        return walkCount;
    }

    public void agaciTara(){
        yuru(sozluk.getAgac().getKokDugumu(), "");
    }

    public void yuru(KokDugumu dugum, String olusan)
    {
        String tester = (olusan + dugum.harf()).trim();
        walkCount++;
        if (dugum != null){
            dugumSayisi++;
            if(dugum.kok() != null){
/*            	if (dugum.getKelime() != null &&
            			!dugum.getKelime().equals(dugum.getKok().icerik())){
            		System.out.println("!!!!! " + dugum.getKelime() + " - " + dugum.getKok().icerik());
            	}*/
                kokTasiyanDugumSayisi++;
                if(set != null){
                    set.add(dugum.kok());
                }
            }
            if(dugum.esSesliler() != null){
                esSesliTasiyanDugumSayisi++;
                if(set!= null){
                	set.addAll(dugum.esSesliler());
                }
            }
            if(!dugum.altDugumVarMi()){
                ucDugumSayisi++;
            }else{
                KokDugumu[] altDugumler = dugum.altDugumDizisi();
                int top = 0;
                for (KokDugumu altDugum : altDugumler) {
                    if(altDugum != null) top++;
                }
                dugumSayilari[top]++;
            }
        }
        KokDugumu[] altDugumler = dugum.altDugumDizisi();
        if (altDugumler != null){
        	for (KokDugumu altDugum : altDugumler){
        		if (altDugum != null){
        			this.yuru(altDugum, tester);
        		}
        	}
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Toplam yurume sayisi (walks) " + walkCount + "\n");
        sb.append("Toplam Dugum Sayisi: " + dugumSayisi + "\n");
        sb.append("Kok tasiyan dugum sayisi: " + kokTasiyanDugumSayisi + "\n");
        sb.append("Es sesli tasiyan dugum sayisi: " + esSesliTasiyanDugumSayisi + "\n");
        sb.append("Alt dugumu olan dugum sayisi:" + (dugumSayisi - ucDugumSayisi) + "\n");
        sb.append("Alt dugumu olmayan (yaprak) dugum sayisi: " + ucDugumSayisi + "\n");
        sb.append("AltDugumu olanların dökümü: \n");
        int araToplam = 0;
        for(int i=1; i<30; i++){
            araToplam += dugumSayilari[i];
            sb.append(i + " alt dugumu olanlar: " + dugumSayilari[i]
                        + " Ara Toplam: " + araToplam + " Yuzdesi: %"
                        + IstatistikAraclari.yuzdeHesaplaStr(dugumSayilari[i], (dugumSayisi - ucDugumSayisi))
                        + " - Kaplam: "
                        + IstatistikAraclari.yuzdeHesaplaStr(araToplam, (dugumSayisi - ucDugumSayisi))
                        + "\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName("net.zemberek.tr.yapi.TurkiyeTurkcesi");
        DilBilgisi dilBilgisi = new TurkceDilBilgisi((DilAyarlari) c.newInstance());
        Alfabe alfabe = dilBilgisi.alfabe();
        AgacSozluk sozluk = null;
        TimeTracker.startClock("a");
        for(int i=0; i<10; i++){
        	KokOkuyucu okuyucu = new IkiliKokOkuyucu("kaynaklar/tr/bilgi/kokler_tr.bin", dilBilgisi.kokOzelDurumlari());
        	sozluk = new AgacSozluk(okuyucu, alfabe, dilBilgisi.kokOzelDurumlari());
        	System.out.println(TimeTracker.getElapsedTimeString("a"));
        }
    	KokAgaciYuruyucu yuruyucu = new KokAgaciYuruyucu(sozluk, new HashSet<Kok>());
    	yuruyucu.agaciTara();
    	System.out.println(yuruyucu);
	}
}
