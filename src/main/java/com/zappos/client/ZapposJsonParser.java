/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zappos.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ZapposJsonParser {

    public void parseJson(String productId) {
        try {

            ZapposFileDownloader zfd = new ZapposFileDownloader();
            
            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://api.zappos.com/Image?productId="+productId+"&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            //parse output 
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(output);

            JSONObject jsonObject = (JSONObject) obj;

            //get image objects
            JSONObject imageObj = (JSONObject)jsonObject.get("images");

            ArrayList nodes = new ArrayList(imageObj.keySet());
            for (int i = 0; i < nodes.size(); i++) {
                String id = (String)nodes.get(i);
                JSONArray images = (JSONArray) imageObj.get(id);
                Iterator<JSONObject> iterator = images.iterator();
		while (iterator.hasNext()) {
                        JSONObject jo = (JSONObject)iterator.next();
                        String remoteUrl = (String)jo.get("filename");
                        System.out.println("remoteUrl: " + remoteUrl);
			zfd.download(remoteUrl);
		}
            }
                
            System.out.println("Output from Server .... \n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}