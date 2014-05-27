package com.zappos.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author David Lee
 */
public class Test {

    public static void main(String[] args) throws Exception {
        File theDir = new File("newTemp");
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory");
            boolean result = theDir.mkdir();
            if (result) {
                System.out.println("DIR created");
            }
        }
        
        String remoteUrl = "http://www.zappos.com/images/z/2/6/1/3/0/9/2613095-p-4x.jpg";
        int i = remoteUrl.lastIndexOf("/");
        String imageName = remoteUrl.substring(i+1);
        System.out.println("imageName: " + imageName);

        int j = remoteUrl.lastIndexOf(".");
        String extention = remoteUrl.substring(j+1);
        
        URL url = new URL(remoteUrl);
        BufferedImage img = ImageIO.read(url);
        File file = new File(theDir + "/"+ imageName);
        ImageIO.write(img, extention, file);

    }
}
