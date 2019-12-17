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
public class FriendList extends AbstractListModel<String> {
    ArrayList<Friends> friendList = new ArrayList<>();

    public FriendList()
    {
        Friends f1 = new Friends("Adam");
        Friends f2 = new Friends("Fredrik");
        Friends f3 = new Friends("Allamo");
        Friends f4 = new Friends("Gustav");
        
        friendList.add(f1);
        friendList.add(f2);
        friendList.add(f3);
        friendList.add(f4);
        
    }
    @Override
    public int getSize() {
        return friendList.size();
    }

    @Override
    public String getElementAt(int index) {
        return friendList.get(index).toString();
    }
    
    public void addUser(Friends user)
    {
        this.friendList.add(user);
    }
    
}