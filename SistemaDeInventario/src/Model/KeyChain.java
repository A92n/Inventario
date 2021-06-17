/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GioXsanX
 */
public class KeyChain {
    List<User> users;

    public KeyChain() {
    }
    public void init(){
        users = new ArrayList();
        File file = new File("KeyChain.bin");
        if(file.exists()){
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) in.readObject();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(KeyChain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(KeyChain.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
          
    }

    public int getSize() {
        return users.size();
    }
    public User find(String userName){
        return users.stream().filter(user-> user.getUserName().equals(userName)).findFirst().orElse(null);
    }    
    public boolean isAuthorized(User user){
        boolean a = false;
        for(User u:users){
            if(u.equals(user)){
                a = true;
                break;
            }
        }
        return a;
    }
    public void add(User user){
        users.add(user);
    }
    public void update(User input){
        User user = find(input.getUserName());
        if(user!=null){
            int index = users.indexOf(user);
            users.set(index, input);
        }
    }
    public boolean save(){
        boolean outcome =false;
        try {
            File file = new File("KeyChain.bin");
            ObjectOutputStream w = new ObjectOutputStream(new FileOutputStream(file));
            w.writeObject(users);
            w.flush();
            outcome = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeyChain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KeyChain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outcome;        
    }
}
