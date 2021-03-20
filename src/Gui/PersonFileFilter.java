/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

//Allows user to select the file that is chosen
public class PersonFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        
        if(f.isDirectory()){
            return true;
        }
        
        String name = f.getName();
        String extension = swing.pkg3.forms.Utils.getFileExtension(name);
        
        if(extension == null){
        return false;
        }
        
        if(extension.equals("per")){
            
            return true;
        }
            
            return false;
    }

    @Override
    public String getDescription() {
        return "Person Database files (*.per)";
    }
    
}
