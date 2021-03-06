/*
 * Lisans bilgisi icin lutfen proje ana dizinindeki zemberek2-lisans.txt dosyasini okuyunuz.
 */

package net.zemberek.demo;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 */
class CikisAlani {
    private JPanel mainPanel;
    private JTextPane outputArea;
    private boolean html;

    public CikisAlani() {
        configure();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setYazi(String yazi) {
        outputArea.setText(yazi);
    }

    public void configure() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(makeInputPanel(), BorderLayout.CENTER);
        mainPanel.setBorder(new TitledBorder(new EmptyBorder(2,2,2,2), "\u00c7\u0131k\u0131\u015f alan\u0131"));
    }

    private void toggleHtml() {

    }


    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public JPanel makeInputPanel() {
        JPanel pq = new JPanel(new BorderLayout());
        outputArea = new JTextPane();
        outputArea.setBorder(new EmptyBorder(1, 3, 1, 3));
        outputArea.setEditable(false);
       // outputArea.setContentType("text/html");

        // jTextPanel scroll bar icermez. O yuzden onu scroll pane'e ekliyoruz.
        JScrollPane ps = new JScrollPane();
        ps.getViewport().add(outputArea);

        pq.add(ps,BorderLayout.CENTER);
        return pq;
    }
}
