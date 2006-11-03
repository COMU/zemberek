/*
 * Created on 15.Mar.2004
 */
package net.zemberek.araclar.turkce;

import net.zemberek.istatistik.Istatistikler;

import java.io.*;

/**
 * TurkishTokenStream
 * Verilen bir doayadan veya herhangi bir stream'dan T�rkce kelimeleri
 * sirayla almak i�in kullanilir. �ki constructor'u vard�r, istenirse verilen bir
 * dosyayi istenirse de herhangi bir inputstream'� isleyebilir.
 * Biraz optimizasyona ihtiyaci var ,ama corpus.txt deki t�m kelimeleri tek tek
 * nextWord() ile cekmek yaklasik 0.8 saniye aldi. (Athlon 900)
 *
 * @author MDA & GBA
 */
public class TurkishTokenStream {
    BufferedReader bis = null;
    Istatistikler statistics = null;
    char[] buffer = new char[1000];
    int index = 0;

    /**
     * Dosyadan kelime okuyan TurkishTokenStream olu�turur
     *
     * @param fileName
     * @param encoding: default i�in null verin
     */
    public TurkishTokenStream(String fileName, String encoding) {
        try {
            FileInputStream fis = new FileInputStream(new File(fileName));
            setupReader(fis, encoding);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Herhangibir input Streaminden'den kelime okuyan TurkishTokenStream olu�turur.
     *
     * @param is
     * @param encoding : default i�in null verin
     */
    public TurkishTokenStream(InputStream is, String encoding) {
        setupReader(is, encoding);
    }

    private void setupReader(InputStream is, String encoding) {
        if (encoding == null)
            bis = new BufferedReader(new InputStreamReader(is));
        else
            try {
                bis = new BufferedReader(new InputStreamReader(is, encoding));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
    }

    public static int MAX_KELIME_BOY = 256;
    private char[] kelimeBuffer = new char[MAX_KELIME_BOY];

    /**
     * Metindeki veya stream'deki bir sonraki kelimeyi getirir
     * - B�y�k harfleri k���lt�r
     * - Noktalama i�aretlerini yutar.
     *
     * @return Sonraki kelime, e�er kelime kalmam��sa null
     */
    public String nextWord() {
        char ch;
        int readChar;
        boolean kelimeBasladi = false;
        int kelimeIndex = 0;
        int harf = 0;
        boolean hypen = false;
        try {
            // TODO: bir char buffer'e toptan okuyup islemek h�z kazandirir mi?
            while ((readChar = bis.read()) != -1) {
                ch = (char) readChar;
                if (statistics != null) {
                    statistics.processChar(ch);
                }
                //System.out.println("Char: "+ ch);
                if (ch == '-') {
                    hypen = true;
                    continue;
                }
                if (Character.isLetter(ch)) {
                    kelimeBasladi = true;
                    hypen = false;
                    //cumleBasladi = true;
                    switch (ch) {
                        case 'I':
                            ch = '\u0131';
                            break; // dotless small i
                            // Buraya sapkal� a vs. gibi karakter donusumlari de eklenebilir.
                        default  :
                            ch = Character.toLowerCase(ch);
                    }
                    if (kelimeIndex < MAX_KELIME_BOY)
                        kelimeBuffer[kelimeIndex++] = ch;
                    else
                        System.out.println("Lagim tasti " + ch);
                    continue;
                }

                if (Character.isWhitespace(ch)) {
                    if (hypen) continue;

                    if (kelimeBasladi) {
                        return new String(kelimeBuffer, 0, kelimeIndex);
                    }
                    kelimeBasladi = false;
                    kelimeIndex = 0;
                    continue;
                } 
                
                // harfimiz bir cumle sinirlayici
                if (isSentenceDelimiter(ch)) {
                    /* if (cumleBasladi)
                     {
                         // kelimeyi cumleye ekle.
                         cumleTamamlandi = true;
                     }
                     continue;*/
                }

            }
            
            // T�m karakterler bitti, son kalan kelime varsa onu da getir.
            if (kelimeBasladi) {
                return new String(kelimeBuffer, 0, kelimeIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int MAX_CUMLE_BOY = 4000;
    private char[] cumleBuffer = new char[MAX_CUMLE_BOY];

    /**
     * Metindeki veya stream'deki bir sonraki kelimeyi getirir
     * - B�y�k harfleri k���lt�r
     * - Noktalama i�aretlerini yutar.
     *
     * @return Sonraki kelime, e�er kelime kalmam��sa null
     */
    public String nextSentence() {
        char ch;
        int readChar;
        boolean cumleBasladi = false;
        int cumleIndex = 0;
        int harf = 0;
        try {
            // TODO: bir char buffer'e toptan okuyup islemek h�z kazandirir mi? (sanmam)
            while ((readChar = bis.read()) != -1) {
                ch = (char) readChar;

                //System.out.println("Char: "+ ch);
                if (Character.isLetter(ch)) {
                    cumleBasladi = true;
                    switch (ch) {
                        case 'I':
                            ch = '\u0131';
                            break; // dotless small i
                            // Buraya sapkal� a vs. gibi karakter donusumlari de eklenebilir.
                        default  :
                            ch = Character.toLowerCase(ch);
                    }
                    if (cumleIndex < MAX_CUMLE_BOY)
                        cumleBuffer[cumleIndex++] = ch;
                    else
                        System.out.println("Lagim tasti " + ch);
                    continue;
                }

                // harfimiz bir cumle sinirlayici
                if (isSentenceDelimiter(ch)) {
                    if (cumleBasladi) {
                        return new String(cumleBuffer, 0, cumleIndex);
                    }
                    continue;
                }

                if (cumleIndex < MAX_CUMLE_BOY)
                    cumleBuffer[cumleIndex++] = ch;
                else {
                    System.out.println("Lagim tasti " + ch);
                    return null;
                }

            }
            
            // T�m karakterler bitti, son kalan kelime varsa onu da getir.
            if (cumleBasladi) {
                return new String(kelimeBuffer, 0, cumleIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public char harfIsle(char chIn) {
        char ch;
        switch (chIn) {
            case 'I':
                ch = '\u0131';
                break; // dotless small i
                // Buraya sapkal� a vs. gibi karakter donusumlari de eklenebilir.
            default  :
                ch = Character.toLowerCase(chIn);
        }
        return ch;
    }

    public boolean isSentenceDelimiter(char ch) {
        if (ch == '.' ||
                ch == ':' ||
                ch == '!' ||
                ch == '?'
        /*ch == Character.LINE_SEPARATOR ||
        ch == Character.PARAGRAPH_SEPARATOR*/
        )
            return true;
        return false;
    }
    
//    public void nextSentence(){
//        Locale currentLocale = new Locale("tr","Tr");
//        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
//    }

    public void setStatistics(Istatistikler statistics) {
        this.statistics = statistics;
    }
}