/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.pkg3.forms;


public class Utils {
    
    
    //getting the file
    public static String getFileExtension(String name){
    
        int pointIndex = name.lastIndexOf(".");
        
        if(pointIndex == -1){
            return null;
        }
        if(pointIndex == name.length()-1){
            return null;
        }
        
        return name.substring(pointIndex+1,name.length());
    }
}

