/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;
import kalkulator.ui.Welcome;
import java.awt.EventQueue;


public class Main {
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
		try {
                    Welcome window = new Welcome();
                    window.frame.setVisible(true); //buat nampilin frame ke layar
		} catch (Exception e) {
                    e.printStackTrace();
		}
            }
            
	});
    }
}

