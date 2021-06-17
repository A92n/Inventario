/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.KeyChain;
import Vista.VistaLogin;
import Vista.Vistaprimeruser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import main.Main;


/**
 *
 * @author GioXsanX
 */
public class UserController implements ActionListener{
    private VistaLogin login;
    private Vistaprimeruser firstUserForm;
    private KeyChain keyChain;
    
    public UserController(VistaLogin login) {
        this.login = login;
        keyChain = new KeyChain();
        keyChain.init();
    }
    
    public UserController(Vistaprimeruser firstUserForm ) {
        this.firstUserForm = firstUserForm;
        keyChain = new KeyChain();
        keyChain.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "login":
                login();
                break;
            case "exit":
                this.login.dispose();
                break;
            case "firstuserform.save":
                saveFirstUser();
                break;
            case "firstuserform.cancel":
                this.firstUserForm.dispose();
                break;
        }
    }
    
    public void saveFirstUser(){
        keyChain.add(this.firstUserForm.getData());
        if(keyChain.save()){
            JOptionPane.showMessageDialog(firstUserForm, "Tu cuenta ha sido registrada correctamente. Ahora usa tus datos para iniciar sesión.", "Registrar", JOptionPane.INFORMATION_MESSAGE);
            this.firstUserForm.dispose();
            Main.showLoginForm();
        }
        else{
            JOptionPane.showMessageDialog(firstUserForm, "El usuario no pudo ser registrado.", "Registrar", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public void login(){
        if(keyChain.isAuthorized(this.login.getData())){
            this.login.dispose();
            Main.showMainForm();
        }
        else
        {
            JOptionPane.showMessageDialog(login, "El intento de inicio de sesión ha fallado.", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
