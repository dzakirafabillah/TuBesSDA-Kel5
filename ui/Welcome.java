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

import kalkulator.ui.Start;
import kalkulator.ui.About;
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

public class Welcome {
    public JFrame frame;
    
    public Welcome(){
        content();
        frame.setVisible(true);
    }
    
    private void content(){
                //box semua
                frame = new JFrame("DR. Calculator");
		frame.setBounds(100, 100, 691, 391);
		frame.getContentPane().setLayout(null);
		
                /*box bagian kiri*/
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 246, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DR.Calculator");
		lblNewLabel.setBounds(29, 77, 184, 44);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 27));
		
		JLabel lblNewLabel_1 = new JLabel("Tugas Besar SDA");
		lblNewLabel_1.setForeground(new Color(245, 245, 245));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_1.setBounds(61, 331, 117, 16);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		Image logo=new ImageIcon(this.getClass().getResource("pngguru.com.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setForeground(new Color(245, 245, 245));
		label.setFont(new Font("Century Gothic", Font.BOLD, 27));
		label.setBackground(Color.WHITE);
		label.setBounds(71, 132, 107, 81);
		panel.add(label);
		
                /*box sebelah kanan*/
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(245, 0, 430, 358);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(174, 157, 46, 14);
		panel_1.add(label_1);
		
                /*tulisan welcome*/
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 40));
		lblNewLabel_2.setForeground(new Color(119,136,153));
		lblNewLabel_2.setBounds(114, 68, 213, 59);
		panel_1.add(lblNewLabel_2);
		
                /*button about*/
		JButton btnAbout = new JButton("About");
		btnAbout.setFocusable(false);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				About tentang = new About();
                                frame.setVisible(false);
	
			}
		});
		btnAbout.setForeground(SystemColor.menu);
		btnAbout.setBackground(new Color(119,136,153));
		btnAbout.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnAbout.setHorizontalAlignment(SwingConstants.LEFT);
		btnAbout.setBounds(229, 182, 91, 41);
		panel_1.add(btnAbout);
		
                
                /*button start*/
		JButton btnStart = new JButton("Start");
		btnStart.setFocusable(false);
		btnStart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Start start = new Start();
                        frame.setVisible(false);
                    }
		});
		btnStart.setVerticalAlignment(SwingConstants.TOP);
		btnStart.setForeground(SystemColor.menu);
		btnStart.setBackground(new Color(119,136,153));
		btnStart.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnStart.setBounds(114, 182, 91, 41);
		panel_1.add(btnStart);
	}
}
