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

package net.zemberek.yapi;

/**
 * Turkce Harf bilgilerini tasiyan sinif
 */

public final class TurkceHarf implements Cloneable {
    // Harfin bilgisayar karsiligi.
    private char charDeger;
    // Harfin kullanilan dil alfabesindeki sirasi
    private int alfabetikSira;
    private boolean sesli = false;
    private boolean sert = false;
    private boolean inceSesli = false;
    private boolean buyukHarf = false;
    private boolean asciiDisi = false;
    private boolean duzSesli = false;
    private boolean yuvarlakSesli = false;

    // harf bir sekilde yumusuyorsa hangi harfe donusuyor?
    private TurkceHarf yumusama = null;
    // eger bu harf bir sekilde sertlesiyorsa hangi harf?
    private TurkceHarf sertDonusum = null;
    // Harf ASCII kumesinde yer aliyorsa ve turkce'ye ozel bir karaktere benzesiyorsa, benzesen harf.
    // ornegin s icin sh, i -> noktasiz i
    private TurkceHarf turkceDonusum = null;
    // eger harf ASCII icinde yer almiyorsa ve benzesen bir ASCII karsiligi varsa bu harf.
    private TurkceHarf asciiDonusum = null;

    public TurkceHarf(char charValue, int sira) {
        this.charDeger = charValue;
        this.alfabetikSira = sira;
        if (Character.isUpperCase(charValue))
            this.buyukHarf = true;
    }

    public TurkceHarf(char charValue) {
        this.charDeger = charValue;
        if (Character.isUpperCase(charValue))
            this.buyukHarf = true;
    }

    public String toString() {
        return "harf:" + charDeger;
    }

    public final boolean asciiToleransliKiyasla(TurkceHarf ha) {

        //eger harf icerikleri ayni degilse turkce-ascii donusumleri kiyaslanir.
        if (charDeger != ha.charDeger()) {
            if (asciiDonusum != null && asciiDonusum.charDeger() == ha.charDeger())
                return true;
            return turkceDonusum != null && turkceDonusum.charDeger() == ha.charDeger();
        }
        return true;
    }

    public TurkceHarf clone() {
        try {
            return (TurkceHarf)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    public char charDeger() {
        return charDeger;
    }

    public void setCharDeger(char charDeger) {
        this.charDeger = charDeger;
    }

    public int alfabetikSira() {
        return alfabetikSira;
    }

    public void setAlfabetikSira(int alfabetikSira) {
        this.alfabetikSira = alfabetikSira;
    }

    public boolean sesliMi() {
        return sesli;
    }

    public void setSesli(boolean sesli) {
        this.sesli = sesli;
    }

    public boolean sertMi() {
        return sert;
    }

    public void setSert(boolean sert) {
        this.sert = sert;
    }

    public boolean inceSesliMi() {
        return inceSesli;
    }

    public void setInceSesli(boolean inceSesli) {
        this.inceSesli = inceSesli;
    }

    public boolean buyukHarfMi() {
        return buyukHarf;
    }

    public void setBuyukHarf(boolean buyukHarf) {
        this.buyukHarf = buyukHarf;
    }

    public boolean asciiDisindaMi() {
        return asciiDisi;
    }

    public void setAsciiDisi(boolean asciiDisi) {
        this.asciiDisi = asciiDisi;
    }

    public boolean duzSesliMi() {
        return duzSesli;
    }

    public void setDuzSesli(boolean duzSesli) {
        this.duzSesli = duzSesli;
    }

    public boolean yuvarlakSesliMi() {
        return yuvarlakSesli;
    }

    public void setYuvarlakSesli(boolean yuvarlakSesli) {
        this.yuvarlakSesli = yuvarlakSesli;
    }

    public TurkceHarf yumusama() {
        return yumusama;
    }

    public void setYumusama(TurkceHarf yumusama) {
        this.yumusama = yumusama;
    }

    public TurkceHarf sertDonusum() {
        return sertDonusum;
    }

    public void setSertDonusum(TurkceHarf sertDonusum) {
        this.sertDonusum = sertDonusum;
    }

    public TurkceHarf turkceDonusum() {
        return turkceDonusum;
    }

    public void setTurkceDonusum(TurkceHarf turkceDonusum) {
        this.turkceDonusum = turkceDonusum;
    }

    public TurkceHarf asciiDonusum() {
        return asciiDonusum;
    }

    public void setAsciiDonusum(TurkceHarf asciiDonusum) {
        this.asciiDonusum = asciiDonusum;
    }
}
