/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author GustavNilsson
 */
public class MessageList extends AbstractListModel<String>{
    ArrayList<String> MsgList = new ArrayList<>();

    public MessageList()
    {}
    @Override
    public int getSize() {
        return MsgList.size();
    }

    @Override
    public String getElementAt(int index) {
        return MsgList.get(index);
    }
    
    public void addMessage(String msg)
    {
        this.MsgList.add("< me > : "+ msg);
    }
}
