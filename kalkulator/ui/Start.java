/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.ui;

/**
 * class welcome 
 * @author DR
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kalkulator.File;


public class Start {
    public JFrame frame;
    
    public Start(){
        content();
        frame.setVisible(true);
    }
    
    private void content(){
                frame = new JFrame("DR. Calculator");
                frame = new JFrame();
		frame.setBounds(295, 0, 480, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
                /*box sebelah kanan*/
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204,229,255));
		panel_1.setBounds(295, 0, 480, 408);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 157, 46, 14);
		panel_1.add(label_1);
		
                /*Button untuk kembali ke Home (welcome.java)*/
                JButton btnHome = new JButton("Home");
		btnHome.setFocusable(false);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Welcome home = new Welcome();
                                frame.setVisible(false);
	
			}
		});
		btnHome.setForeground(SystemColor.menu);
		btnHome.setBackground(new Color(119,136,153));
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setBounds(20, 15, 91, 41);
		panel_1.add(btnHome);
                
                /*tulisan welcome*/
		JLabel lblNewLabel_2 = new JLabel("Calculate ");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 40));
		lblNewLabel_2.setForeground(new Color(119,136,153));
		lblNewLabel_2.setBounds(114, 68, 213, 59);
		panel_1.add(lblNewLabel_2);
                
                JLabel lblNewLabel_3 = new JLabel("From");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 40));
		lblNewLabel_3.setForeground(new Color(119,136,153));
		lblNewLabel_3.setBounds(170, 115, 213, 59);
		panel_1.add(lblNewLabel_3);
		
                /*button menghitung operasi dari file*/
		JButton btnAbout = new JButton("File");
		btnAbout.setFocusable(false);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
                            try {
                                File calculator = new File();
                            } catch (IOException ex) {
                                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
		btnAbout.setForeground(SystemColor.menu);
		btnAbout.setBackground(new Color(119,136,153));
		btnAbout.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAbout.setHorizontalAlignment(SwingConstants.LEFT);
		btnAbout.setBounds(229, 182, 91, 41);
		panel_1.add(btnAbout);
		
                
                /*button untuk membuka kalkulator*/
		JButton btnStart = new JButton("Calc");
		btnStart.setFocusable(false);
		btnStart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {		
			View calculator=new View();
                    }
		});
		btnStart.setVerticalAlignment(SwingConstants.TOP);
		btnStart.setForeground(SystemColor.menu);
		btnStart.setBackground(new Color(119,136,153));
		btnStart.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnStart.setBounds(114, 182, 91, 41);
		panel_1.add(btnStart);
                
                
                 /*button untuk membuka kalkulator*/
		JButton btnMatriks = new JButton("M 2x2");
		btnMatriks.setFocusable(false);
		btnMatriks.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {	
    
			viewMatriks1 calculator=new viewMatriks1();
                        calculator.run();
                        
                    }
		});
                
                
                
		btnMatriks.setVerticalAlignment(SwingConstants.TOP);
		btnMatriks.setForeground(SystemColor.menu);
		btnMatriks.setBackground(new Color(119,136,153));
		btnMatriks.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnMatriks.setBounds(170, 240, 91, 41);
		panel_1.add(btnMatriks);
                
                
	}
}
