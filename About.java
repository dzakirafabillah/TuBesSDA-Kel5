/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class About {
    public JFrame frame;
    public About() {
		content();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void content() {
		frame = new JFrame();
		frame.setBounds(100, 100, 487, 634);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 471, 69);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		// bagian atas
		JLabel lblNewLabel = new JLabel("Kelompok 5");
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
		lblNewLabel.setBounds(147, 21, 195, 37);
		panel.add(lblNewLabel);
		
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
