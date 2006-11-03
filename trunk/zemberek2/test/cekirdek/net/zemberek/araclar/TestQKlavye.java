/*
 * Created on 30.Tem.2005
 *
 */
package net.zemberek.araclar;

import junit.framework.TestCase;

public class TestQKlavye extends TestCase {
    public void testQKlavye(){
        int mesafe = 0;
        int mesafe2 = 0;
        mesafe = QTusTakimi.mesafeHesapla('a','a');
        assertEquals(" Ayn� karakterin mesafesi s�f�r olmal�yd�.", 0, mesafe);

        mesafe = QTusTakimi.mesafeHesapla('a','%');
        assertEquals(" Tan�ms�z karakter -1 d�nd�rmeliydi.", -1, mesafe);

        mesafe = QTusTakimi.mesafeHesapla('a','s');
        mesafe2 = QTusTakimi.mesafeHesapla('a','e');
        System.out.println("a-s: " + mesafe + " a-e: " + mesafe2);
        assertTrue("a'n�n s ye mesafesi e'ye olan mesafesinden az olmal�yd�.", mesafe < mesafe2);

        mesafe = QTusTakimi.mesafeHesapla('s','q');
        mesafe2 = QTusTakimi.mesafeHesapla('s','e');
        System.out.println("s-q: " + mesafe + " s-e: " + mesafe2);
        assertTrue("s'n�n q ye mesafesi ile e'ye olan mesafesi ayn� olmal�yd�.", mesafe == mesafe2);

        mesafe = QTusTakimi.mesafeHesapla('s','q');
        mesafe2 = QTusTakimi.mesafeHesapla('s','a');
        System.out.println("s-q: " + mesafe + " s-a: " + mesafe2);
        assertTrue("s'n�n q ye mesafesi ile a'ye olan mesafesinden fazla olmal�yd�.", mesafe > mesafe2);
    }
}
