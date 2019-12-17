/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author GustavNilsson
 */
public class Temperature {
    
    
    static String temperature(String lat, String lon){
        
        HttpsURLConnection myConnection = null;
        String temps = null;
        String units = null;
        
        try {
            URL url = new URL("https://api.met.no/weatherapi/locationforecast/1.9/?lat="+lat+"&lon="+lon);
            myConnection = (HttpsURLConnection) url.openConnection();
            myConnection.setRequestMethod("GET");
            int checkResponseCode = myConnection.getResponseCode();

            if(checkResponseCode == HttpURLConnection.HTTP_OK){
                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null){
                        
                        response.append(inputLine);   
                    }
                }

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                InputSource is;
                try{
                    builder = factory.newDocumentBuilder();
                    is = new InputSource(new StringReader(response.toString()));
                    Document doc = builder.parse(is);       
                    NodeList list = doc.getElementsByTagName("temperature");
                    Node node = list.item(0);
                    NamedNodeMap nodeMap = node.getAttributes();
                    temps = nodeMap.getNamedItem("value").getFirstChild().getTextContent();
                    units = nodeMap.getNamedItem("unit").getFirstChild().getTextContent();
                    
                } catch(SAXException e){
                    System.err.println("SAXException: " + e);
                } catch (ParserConfigurationException e)
                {
                    System.err.println("ParserConfigurationException: " + e);
                }
            }
            
        } catch(MalformedURLException e){
            
            System.err.println("MalformedURLException: " + e);
            
        } catch(IOException e){
            
            System.err.println("IOException: " + e);
            return "Unable to connect api.met.no";
        } finally {
            if(myConnection != null)
            {
                myConnection.disconnect();
            }
            
        }
        
        return temps+ " "+units;
    }
}
