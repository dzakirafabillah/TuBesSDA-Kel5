package kalkulator;

/**
 * class Binary Tree yang memiliki field root bertipe Node
 * @author DZAKIRA RIZKA
 * @author Luka Kralj
 */

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/*
  NotNull dan Nullable tidak melakukan apa apa. hanya untuk dokumentasi
  (dari stackoverflow.com)
*/

public class BinaryTree {
    /*ATTRIBUTE*/
    
    private int size;
    private Node root;
  
    /*
      Konstruktor dengan parameter bertipe data Node yang akan menjadi ROOT 
      dari BinaryTree 
    */
    public BinaryTree(Node root) {
        size = 0;
        if (root != null) {
            size++;
        }
        this.root = root;
    }

    /*
      Konstruktor tanpa parameter sehingga ROOT dari BinaryTree masih NULL
    */
    public BinaryTree() {
        this(null);
    }
    
    
    /*GETTER METHOD*/
    public Node root() {
        return root;
    }

    public int size(){
        return size;
    }

    
    /*return True jika v bukan daun*/
    public boolean isNotLeaf(Node elm) {
        return hasLeft(elm) || hasRight(elm);
    }

    /*return True jika v adalah daun*/
    public boolean isLeaf(Node elm) {
        return !hasLeft(elm) && !hasRight(elm);
    }

    /*return True jika v memiliki anak kiri*/
    public boolean hasLeft(Node elm) {
        return elm.getLeftChild() != null;
    }

    /*return True jika v memiliki anak kanan*/
    public boolean hasRight(Node elm) {
        return elm.getRightChild() != null;
    }

    /* return True jika v adalah ROOT*/
    public boolean isRoot(Node elm) {
        return elm == root;
    }

    
    /**
     * Update Binary Tree. ROOT bintree diisi dengan root baru (dari parameter)
     * root sebelumnya menjadi anak kiri dari root baru
     * boleh diinsert anak kanan boleh juga tidak
     */
    public void updateTree(@NotNull Node newRoot, @Nullable Node rightChild) {
        if (root == null) {
            root = newRoot;
        }
        else {
            root.setParent(newRoot);
            newRoot.setLeft(root);
            root = newRoot;
        }
        size++;

        if (rightChild != null) {
           root.setRight(rightChild);
           size++;
        }
    }
    
    /***TRAVERSAL***/
    public void preOrder(Node node){
        if (node != null) {
            System.out.print(" " + node.getValue());
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }
    
    public void inOrder(Node node){
        if (node != null) {
            inOrder(node.getLeftChild());
            System.out.print(" " + node.getValue());
            inOrder(node.getRightChild());
        }
    }
    
    public void postOrder(Node node){
        if (node != null) {
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.print(" " + node.getValue());
        }
    }
}


