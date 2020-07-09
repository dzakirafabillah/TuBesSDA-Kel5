package kalkulator;

import java.lang.*; 
import java.io.*; 
import java.util.*; 
import kalkulator.ui.Start;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author DZAKIRA RIZKA
 */

public class File {
    public File() throws IOException{
        run();
    }
    
    public void run() throws IOException{
        /*Buka dan pilih file*/
	JFileChooser chooser=new  JFileChooser(); 
        BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
        
	int returnVal = chooser.showOpenDialog(null);
        
        /*Jika tidak ada file yg di pilih*/
	if(returnVal == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "file not choosen");
            Start awal = new Start();
	}
	    
        if(returnVal == JFileChooser.APPROVE_OPTION) {
	    java.io.File f = chooser.getSelectedFile();
	    String fname = f.getName();
            
            /*hanya boleh file .txt saja, jika bukan txt maka ends program*/
            StringBuilder type = new StringBuilder(); 
            type.append(fname); 
            type = type.reverse(); 
            String type_fix = type.substring(0,3);
            //System.out.println(type_fix);
	    if( !type_fix.equals("txt")){ 
	        JOptionPane.showMessageDialog(null, "gagal memproses file\nekstensi file harus .txt");
                Start awal = new Start();
	    }else{
                /*file berupa .txt, di proses*/
                BufferedReader br = new BufferedReader(new FileReader(f));
                String st="";
                while ((st = br.readLine()) != null) {
                    if (st.equals("")) {
                        return;
                    }
                    Process proses = new Process(st);
                    String result = proses.getResult();
                    if (result.startsWith("-")) {
                        result = result ;
                    }
                    /*Menuliskan ekspresi matematika dan hasil nya ke file result.txt*/
                    out.write(st + " = " + result);  out.newLine();
                }

                br.close();
                out.close();
                JOptionPane.showMessageDialog(null, "Results has been saved");
            }
        }
    }
}