package com.zappos.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
 
public class ZapposFileDownloader {
 
        public boolean download(String remoteUrl) throws Exception{
            getFile(remoteUrl);
            return true;
        }
    
	public Response getFile(String remoteUrl) throws Exception {
                File theDir = new File("ImageDownloaded");
                // if the directory does not exist, create it
                if (!theDir.exists()) {
                    System.out.println("creating directory");
                    boolean result = theDir.mkdir();
                    if (result) {
                        System.out.println("DIR created");
                    }
                }
            
                URL url = new URL(remoteUrl);
                BufferedImage img = ImageIO.read(url);
                
                int i = remoteUrl.lastIndexOf("/");
                String imageName = remoteUrl.substring(i+1);
                System.out.println("imageName: " + remoteUrl);
                
                File file = new File(theDir + "/"+ imageName);
                
                ImageIO.write(img, "png", file);
            
		ResponseBuilder response = Response.ok((Object) file);
                
		response.header("Content-Disposition",
			"attachment; filename="+remoteUrl+".png");
		return response.build();
 
	}
 
}