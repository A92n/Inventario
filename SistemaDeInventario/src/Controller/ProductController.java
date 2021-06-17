/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Model.Caja;


import Vista.VistaCaja;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Amy Leiva
 */
public class ProductController extends KeyAdapter implements ActionListener{
private VistaCaja vc;
private JFileChooser d;
private Caja caja;


  public ProductController(VistaCaja visc) {
        this.vc= visc;
        d = new JFileChooser();
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

   
    public void setPerson(String filePath){
        File f = new File(filePath);
        //readPerson(f);
    }
  
    
    

 
  
  
@Override
  public void actionPerformed(ActionEvent e){
 
     
switch(e.getActionCommand()){
     case"guardar":
        save();
        break;
       case "agregar":
     vc.mostrar();
       break;
       case "pago":
        vc.pago(vc.sumar());
        
        break;
       
        case "select":
       //  select();

            break;
      
        case "limpiar":
          vc.clear();
          
            break;


    }

  
  } 
  
 
  private void save() {
        d.showSaveDialog(vc);
        if (d.getSelectedFile() != null) {
            writeFile(d.getSelectedFile(), vc.getdata(vc.sumar()));
        }

    }

    private void writeFile(File file, Caja caja) {
        try {
            ObjectOutputStream w = new ObjectOutputStream(new FileOutputStream(file));
            w.writeObject(caja);
            w.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

@Override
 public void keyTyped(KeyEvent event) {
     JTextField input =(JTextField)event.getSource();
       
        
              switch (input.getName()) {
           
            
                case "cantidad":
                char r = event.getKeyChar();
                if (!(Character.isDigit(r) || r == KeyEvent.VK_BACK_SPACE 
                        || r == KeyEvent.VK_ENTER || r == KeyEvent.VK_PERIOD)) {
                    event.consume();
                }
                break;
           
           case "precio":
                char d = event.getKeyChar();
                if (!(Character.isDigit(d) || d == KeyEvent.VK_BACK_SPACE 
                        || d == KeyEvent.VK_ENTER || d == KeyEvent.VK_PERIOD)) {
                    event.consume();
                }
                break;
           
        }
           
        
    }

  
  
  
}
