/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;

/**
 *
 * @author GustavNilsson
 */
public class Friends {
    private String username;
    ArrayList<String> messagesToUser = new ArrayList<>();
    
    //Getter
    public String getName()
    {
        return username;
    }
    //Setter
    public void setName(String newName){
        this.username = newName;
    }
    
    //Getter
    public String getMessageToUsers(String msg){
        return msg;
    }
    //Setter
    public void setMessageToUsers(String msg){
    
        this.messagesToUser.add(msg);
    }
    
    @Override
    public String toString() { 
        return String.format(username);
    }
    public Friends(String name){
    
        this.username = name;
    }
    
}