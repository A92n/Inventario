/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;






import Vista.VistaCaja;
import Vista.VistaPrincipal;
import Vista.configuracionDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Amy Leiva
 */
public class MainController implements ActionListener{
     VistaPrincipal vp;
     
    public MainController(VistaPrincipal vp){
        this.vp = vp;
    }
  
 
    @Override
    public void actionPerformed(ActionEvent e) {
          switch(e.getActionCommand()){
            case "Caja":
               showVentasFrame();
                break;
            
            case "configuracion":
              showSettingsDialog();
                break;


                case "salir":
                System.exit(0);
                break;
        }
    }

       private void showVentasFrame(){
           VistaCaja vf=new VistaCaja();
           vp.showChild(vf, false);
           

    }
     
      
        public void showSettingsDialog(){
       configuracionDialog cd= new configuracionDialog(vp, true);
        cd.setLocationRelativeTo(null);
        cd.setVisible(true);
    }

}
