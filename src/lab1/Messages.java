/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author GustavNilsson
 */
public class Messages {
    
    private String messagesFromUser;
    
    //Getter
    public String getMessageFromUsers(String msg){
        return msg;
    }
    //Setter
    public void setMessageFromUsers(String msg){
    
        this.messagesFromUser = msg;
    }
    
    public String ToString()
    {
        return String.format(messagesFromUser);
    }
    
    public Messages(String msg)
    {
        this.messagesFromUser = msg;
    }
    
}
