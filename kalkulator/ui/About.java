/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.ui;

/**
 * Class about
 * @author Dzakira Rizka
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class About {
    public JFrame frame;
    
    public About() {
	content();
	frame.setVisible(true);
    }

	/*Isi*/
	private void content() {
		frame = new JFrame("DR. Calculator");
		frame.setBounds(100, 100, 487, 634);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204,229,255));
		panel.setBounds(0, 0, 471, 69);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
                
		/* bagian atas*/
		JLabel lblNewLabel = new JLabel("Kelompok 5");
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
		lblNewLabel.setBounds(147, 21, 195, 37);
		panel.add(lblNewLabel);
                
                /*Button untuk kembali ke HOME (welcome.java)*/
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
		panel.add(btnHome);
                
		/*Bagian bawah : isi*/
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(0, 70, 471, 526);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel nama1 = new JLabel("Dzakira Fabillah");
		nama1.setFont(new Font("Century Gothic", Font.BOLD, 19));
		nama1.setBounds(120, 50, 153, 159);
		panel_1.add(nama1);
               
                JLabel NIM1 = new JLabel("191524040");
		NIM1.setFont(new Font("Century Gothic", Font.BOLD, 19));
		NIM1.setBounds(120, 70, 190, 159);
		panel_1.add(NIM1);
                
                JLabel nama2 = new JLabel("Rizka Auliarahmi");
		nama2.setFont(new Font("Century Gothic", Font.BOLD, 19));
		nama2.setBounds(119, 318, 199, 32);
		panel_1.add(nama2);
                
                JLabel NIM2 = new JLabel("191524057");
		NIM2.setFont(new Font("Century Gothic", Font.BOLD, 19));
		NIM2.setBounds(119, 338, 199, 32);
		panel_1.add(NIM2);
                      
	}
}
