/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Vista.configuracionDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import misc.Constants;
import misc.PropertyFile;


public class configuracionController implements ActionListener {

    private final configuracionDialog configDialog;
    private final String directory;

    
    public configuracionController(configuracionDialog settingsDialog) {
        this.configDialog = settingsDialog;
        PropertyFile prop = new PropertyFile();
        prop.open();
        if(prop.isFileExists()){
            this.directory = prop.getProperty(Constants.DIRECTORY_PROPERTY);
            this.configDialog.setSelectedDirectory(this.directory);
            this.configDialog.setCompanyName(prop.getProperty(Constants.COMPANYNAME_PROPERTY));
        }
        else{
            this.directory = "";
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "choose":
                chooseDirectory();
                break;
            case "ok":
                saveSettings();
                break;
        }
    }
    /**
     * Muestra una instancia de la clase JFileChooser, pero se configura para que solamente se selecionen carpetas.
     */
    public void chooseDirectory(){
        JFileChooser chooser = new JFileChooser(); 
        if(this.directory.isEmpty()){
            chooser.setCurrentDirectory(new java.io.File("."));
        }
        else{
            chooser.setCurrentDirectory(new java.io.File(this.directory));
        }
        chooser.setDialogTitle("Selecciona una carpeta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(configDialog) == JFileChooser.APPROVE_OPTION) {
           configDialog.setSelectedDirectory(chooser.getSelectedFile().getAbsolutePath());
        }
        else {
            if(configDialog.getSelectedDirectory().isEmpty()){
                configDialog.setSelectedDirectory("No ha seleccionado una carpeta.");
            }
        }     
    }
    /**
     * Guarda los valores configurados en un archivo llamado config.properties que estará ubicado en la ruta de la aplicación.
     */
    public void saveSettings(){
        PropertyFile prop = new PropertyFile();
        prop.setProperty(Constants.DIRECTORY_PROPERTY, configDialog.getSelectedDirectory());
        prop.setProperty(Constants.COMPANYNAME_PROPERTY, configDialog.getCompanyName());
        if(prop.saveProperties()){
            configDialog.dispose();
        }
        else{
            JOptionPane.showMessageDialog(configDialog, "Ocurrió un error al guardar la configuración.", "Configuración", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
