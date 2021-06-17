/*
 * Licencia GPL.
 * Desarrollado por: William Sánchez
 * El software se proporciona "TAL CUAL", sin garantía de ningún tipo,
 * expresa o implícita, incluyendo pero no limitado a las garantías de
 * comerciabilidad y adecuación para un particular son rechazadas.
 * En ningún caso el autor será responsable por cualquier reclamo,
 * daño u otra responsabilidad, ya sea en una acción de contrato,
 * agravio o cualquier otro motivo, de o en relación con el software
 * o el uso u otros tratos en el software.
 */
package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William Sanchez
 */
public class PropertyFile {    
    Properties properties;
    boolean fileExists;
    
    public PropertyFile(){
        properties = new Properties();                     
    }
    public void open(){
        try {
            File f = new File("config.properties");
            if (f.isFile() && f.canRead()) {                
                InputStream input = new FileInputStream("config.properties");
                properties.load(input);
                input.close();  
                fileExists=true;
            }
            else
            {
                fileExists=false;
            }
        } 
        catch (FileNotFoundException ex) {  
            Logger.getLogger(PropertyFile.class.getName()).log(Level.SEVERE, "PropertyFile", ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(PropertyFile.class.getName()).log(Level.SEVERE, "PropertyFile", ex);
        }  
    }

    public boolean isFileExists() {
        return fileExists;
    }
    
    public String getProperty(String PropertyName){  
        String PropertyValue;
        try{
            PropertyValue = properties.getProperty(PropertyName);
        }
        catch(Exception ex){
            PropertyValue= "";
        }
        if(PropertyValue==null)
            PropertyValue = "";
        return PropertyValue;
    }
    public void setProperty(String PropertyName,String PropertyValue)
    {
        properties.setProperty(PropertyName, PropertyValue);
    }
    public boolean saveProperties(){
        boolean r;        
        try {
            try (OutputStream output = new FileOutputStream("config.properties")) {
                properties.store(output, "Configuración");
                output.flush();
            }
            r=true;
        } catch (IOException ex) {
            r=false;
            Logger.getLogger(PropertyFile.class.getName()).log(Level.SEVERE, "PropertyFile", ex);
        }
        return r;
    }   
}

