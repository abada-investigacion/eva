/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Para simular locale en una columna
 */
Abada.grid.column.LocaleRenderer.defaultRenderer=function(value){
    if(typeof value == "string"){
        if (value.length < 1){
            return " ";
        }else{
            //intento localizar el valor para ello busco un array que debe existir
            //con los valores de localización
            try{
                result=Abada.grid.ColumnModel.LocaleRenderer.defaultLocale[value];
                if (result)
                    return result;
            }catch (e){
            }
        }
    }
    return value;
};
/**
 * Se debe sobreescribir en un javascript con las traducciones
 */
Abada.grid.ColumnModel.LocaleRenderer.defaultLocale={};