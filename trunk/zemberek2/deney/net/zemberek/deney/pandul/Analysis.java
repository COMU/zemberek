package net.zemberek.deney.pandul;

import net.zemberek.bilgi.KaynakYukleyici;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Simple test application to see the performance - footprint of trie
 * 
 * @author mdakin
 *
 */
public class Analysis {
  
  static int totalNodes = 0;
  static int[] childCounts = new int[40];
  static int[] chainLengths = new int[20];
  static int leafNodes = 0;
  
  /**
   * Recursively walks the trie and updates statistics.
   * @param node
   * @param chainLen : A chain is nodes with single childs without
   * a word mark. A leaf node can be part of chain as well.
   */
  public static void walk(Node node, int chainLen) {
    totalNodes++;
    Node[] children = node.getChildren();
    chainLen++;
    if(children == null) {
      chainLengths[chainLen]++;
      leafNodes++;
      return;
    }
    childCounts[children.length]++;
    if(children.length > 1 || node.getAttribute() != 0) {
      chainLengths[chainLen]++;
      chainLen = 0;
    } 
    for (Node childNode : children) {
      walk(childNode, chainLen);
    }
  }
  
  public static void main(String[] args) throws IOException {
    CompactStringTrie cst = new CompactStringTrie();
    BufferedReader reader = new KaynakYukleyici().getReader("kaynaklar/tr/bilgi/duzyazi-kilavuz.txt");
    String line;
    int total = 0;
    while((line = reader.readLine()) != null) {
      line = line.toLowerCase().trim();
      if (line.startsWith("#")) {
        continue;
      }
      String[] parts = line.split(" ");
      if (parts.length == 0 ) {
        continue;
      }
      line = parts[0].replaceAll("\'", "");
      line = line.replaceAll("\\.", "");
      line = line.replaceAll("\\`", "");
      line = line.replaceAll("[qwx-]", "");
      total++;
      System.out.println(line);  
      cst.add(line);
    }
    System.out.println("Total entries in the dictionary: " + total);
    
    walk(cst.getRoot(), 0);
    System.out.println("Total Nodes: " + totalNodes);
    System.out.println("Total Leaf Nodes: " + leafNodes);    
    for (int i = 0; i < childCounts.length; i++) {
      if(childCounts[i] == 0) continue;
      System.out.println("Nodes with " + i + " children: " + childCounts[i]);
    }
    for (int i = 0; i < chainLengths.length; i++) {
      if(chainLengths[i] == 0) continue;
      System.out.println("Chains of length " + i + " : " + chainLengths[i]);
    }
  }
}
