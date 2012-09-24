/**
 *is a number
 */
function isNumeric(sText)
{
    var ValidChars = "0123456789.";
    var IsNumber=true;
    var Char;

    for (i = 0; i < sText.length && IsNumber; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            IsNumber = false;
        }
    }
    return IsNumber;
}

/**
 * Set an Ext Component in the center panel
 * @param panelCentral
 * @return
 */
function setCentralPanel(panelCentral,autoDestroy){
    var aux=Ext.getCmp('centralPanel');
    if (aux){
        //aux.suspendEvents();
        if (autoDestroy){
            aux.removeAll(true);
        }
        else{
            aux.removeAll(false);
        }
        aux.add(panelCentral);
        aux.doLayout();
    //aux.resumeEvents();
    }
}

function getRelativeURI(url){
    if (url.substr(0,1) != "/")
        url='/'+url;    
    return window.location.pathname.substr(0,window.location.pathname.indexOf('/',1))+url;
}