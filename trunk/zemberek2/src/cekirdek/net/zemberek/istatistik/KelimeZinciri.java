/*
 * Created on 11.Tem.2004
 */
package net.zemberek.istatistik;


class KelimeZinciri implements Comparable {
    int kullanim = 1;
    String ikili = null;

    public KelimeZinciri(String ikili) {
        this.ikili = ikili;
    }

    public void hit() {
        kullanim++;
    }

    public int getKullanim() {
        return this.kullanim;
    }
    
    public String toString(){
        return ikili + " (" + kullanim + ")";
    }

    public int compareTo(Object o) {
        KelimeZinciri gelen = (KelimeZinciri) o;
        return gelen.kullanim - this.kullanim;
    }
}