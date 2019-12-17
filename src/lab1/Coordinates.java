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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author GustavNilsson
 */
public class Coordinates {

    static String getLatitude(String city){
        String cordinate = longlatiCords(city)[0];
        return cordinate;
    }
    static String getLongitude(String city){
        String cordinate = longlatiCords(city)[1];
        return cordinate;
    }
    static String getCityName(Double latitude, Double longitude)
    {
        String cityName = longlatiCordsToCityName(latitude,longitude);
        return cityName;
    }


   private static String[] longlatiCords(String city){
        HttpsURLConnection myConnection = null;
        String latitudeCoordinate = null;
        String longitudeCordinate = null;
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/search?format=xml&q="+city);
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
                    NodeList list = doc.getElementsByTagName("place");
                    Node node = list.item(0);
                    
                    NamedNodeMap nodeMap = node.getAttributes();
                    latitudeCoordinate = nodeMap.getNamedItem("lat").getFirstChild().getTextContent();
                    longitudeCordinate = nodeMap.getNamedItem("lon").getFirstChild().getTextContent();
                    
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
            //return "Unable to connect api.met.no";
        } finally {
            if(myConnection != null)
            {
                myConnection.disconnect();
            }
            
        }
        return new String[] { latitudeCoordinate, longitudeCordinate };
    }
   
   
    private static String longlatiCordsToCityName(Double latitudeCoordinate, Double longitudeCordinate){
        HttpsURLConnection myConnection = null;
        String nameOfCity = null;
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/reverse?format=xml&lat="+latitudeCoordinate.toString()+"&lon="+longitudeCordinate);
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
                    Element list = doc.getDocumentElement();
                    NodeList nl = list.getChildNodes();
                    int length = nl.getLength();
                    for(int i = 0; i < length; i++){
                        if(nl.item(i).getNodeType() == Node.ELEMENT_NODE){
                            Element el = (Element) nl.item(i);
                            if(el.getNodeName().contains("addressparts")){
                                
                                nameOfCity = el.getElementsByTagName("city").item(0).getTextContent();
                                System.out.println(nameOfCity);
                            }
                        }
                    }
                    
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
            //return "Unable to connect api.met.no";
        } finally {
            if(myConnection != null)
            {
                myConnection.disconnect();
            }
            
        }
        return nameOfCity;
    }
}
