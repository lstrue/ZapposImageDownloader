package com.zappos.client;

import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * That program will accept a filename on the command line. 
 * That file will be a text file that contains a list of SKUs, one per line. 
 * When you run the application, it will use the Zappos API to find the URL for each product's default image 
 * (defaultImageUrl in the product API). 
 * The program will download each image and write it into an "images" directory underneath the current directory. 
 * All images should be returned after the program runs. 
 * The application should create that directory if it does not yet exist. 
 * Your application should also include sufficient error handling code so it does not crash if a bad product ID is found in the file. 
 * Use the attached SKU file (skus.txt) as a sample of what an SKU file might be like.
 * 
 * Key: 52ddafbe3ee659bad97fcce7c53592916a6bfd73
 * ProductId: 7515478
 * 
 * http://api.zappos.com/Image?productId=7515478&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73
 * 
 * http://www.jsoneditoronline.org/
 * 
 * @author David Lee
 */
public class ZapposFileReader {
    
    public static void main(String[] args) throws Exception{
        
//            String id;
//            System.out.println("Enter file name here : ");
//            File f = new File(System.in);
//            Scanner sc = new Scanner(System.in);
//            ZapposJsonParser zjp = new ZapposJsonParser();
//            while (sc.hasNextLine()) {
//                id = sc.nextLine();
//                String productId = testId(id);
////                zjp.parseJson(productId);
//            }
//            sc.close();
//            System.out.println("Images downloaded.");
        
        
        JFileChooser jfc = new JFileChooser(".");
        jfc.showOpenDialog(null);
        File f = jfc.getSelectedFile();
        Scanner sc = new Scanner(f);
        ZapposJsonParser zjp = new ZapposJsonParser();
        while(sc.hasNextLine()){
            String productId = testFile(sc.nextLine());
            System.out.println("productId: "+productId);
//            zjp.parseJson(productId);
        }
        System.out.println("Images downloaded.");
    }
    
    static String testFile(String s){
        s = s.trim();
        //use regular expression to make sure the id is the right format
        
        return s;
    }
    
}
