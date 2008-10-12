package net.zemberek.deney.pandul;

/**
 * A compact implementation for trie nodes.  
 * 
 * @author mdakin
 *
 */
public class Node {
  private byte letter;
  private int bitmap;
  // For compactness, sacrifice some construction performance 
  // (Using an arraylist would require an extra int) 
  private Node[] children;

  public Node() {
  }
  
  public Node(char c) {
    this.letter = (byte) Letters.getIndex(c);
  }
  
  public final Node getChildNode(char c) {
    int index = Letters.getIndex(c);
    assert (index != -1);
    if (hasChild(index)) {
      return children[getArrayIndex(index) - 1];
    }
    return null;
  }
  
  public final void addNodeFor(char c) {
    int index = Letters.getIndex(c);
    if(hasChild(index)) {
      // Subnode with given character already exists.
    }
    addChild(c, index);
  }
  
  final void addChild(char c, int index) {
    // Create a new Node.
    Node child = new Node(c);
    if(children == null) {
      children = new Node[1];
      children[0] = child; 
    } else {
      // Expand the array, insert child to correct position.
      // TODO(mdakin): optimize for very common small arrays of len < 3
      int aindex = getArrayIndex(index);
      Node[] tmp = new Node[children.length + 1];
      if(index < children.length) {
        System.arraycopy(children, 0, tmp, 0, aindex);
        children[aindex] = child;
        System.arraycopy(children, aindex, tmp, aindex, tmp.length - aindex);
      }
    }
    // update bitmap
    bitmap |= (1 << index);
  }
  
  final boolean hasChild (int index) {
    return ((bitmap & (1 << index)) == 1);
  }
  
  final int getArrayIndex(int index) {
    return Integer.bitCount(bitmap & (-1 << 32 - index));
  }
  
}
