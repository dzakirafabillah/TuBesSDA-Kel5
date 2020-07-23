/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

/**
 *class Node untuk BinaryTree
 * value berupa object karena bisa diisi oleh objek apa saja
 * @author DZAKIRA RIZKA
 * 2 July 2020
 */

import com.sun.istack.internal.Nullable;

public class Node {
    /*ATTRIBUTE*/
    private Object value;
    private Node parent;
    private Node left;
    private Node right;

    
    /*CONSTRUCTOR*/
    /*
       Membuat Node baru dengan value yaitu aValue dan di set anak kanan
       dan anak kirinya (apabila ada), jika tidak ada akan di set sebagai null
     */
    public Node(Object aValue, @Nullable Node leftChild, @Nullable Node rightChild) {
        this.value = aValue;
        parent = null;
        left = leftChild;
        right = rightChild;
        /*Set parent dari anak anak node*/
        if (leftChild != null) {
            leftChild.setParent(this);
        }
        if (rightChild != null) {
            rightChild.setParent(this);
        }
    }
    
    /* ACCESSOR & MUTATOR */

    /****GETTER METHOD****/
    public Node getParent() {
        return parent;
    }

    public Node getLeftChild() {
        return left;
    }

    public Node getRightChild() {
        return right;
    }

    public Object getValue(){
        return value;
    }
    
    /****SETTER METHOD****/

    public void setRight(Node v) {
        right = v;
    }

    public void setLeft(Node v) {
        left = v;
    }

    public void setParent(Node v) {
        parent = v;
    }
    
}
