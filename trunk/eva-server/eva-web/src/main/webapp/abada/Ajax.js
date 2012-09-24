 /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/***
 *Usado para llamadas Ajax y espera la respuesta en Json
 */
Ext.define('Abada.Ajax',{
    extend:'Ext.data.Connection',
    requires:['Ext.window.MessageBox'],
    singleton: true,
    autoAbort: false,
    constructor:function(config){
        this.setConfig(config);
        this.callParent([config]);
    },
    autoInitConfigRequest:function(args,o){        
        var result=Ext.applyIf(args,o);
        var aux={
            method:'POST'
        };
        result=Ext.applyIf(result,aux);
        return result;
    },
    requestJson: function(o){                
        //Sacar mensaje de wait
        if (o.waitTitle || o.waitMsg){
            if (!o.waitTitle) o.waitTitle='Esperando...';
            if (!o.waitMsg) o.waitMsg='Esperando...';
            o.waitMessageBox=Ext.Msg.wait(o.waitMsg,o.waitTitle);
        }

        var args={};
        args.success=function(result,request) {
            if (o.waitMessageBox)
                o.waitMessageBox.hide();
            var requestJson=Ext.decode(result.responseText,true);
            if (requestJson && requestJson!=null && requestJson.success){
                if (requestJson.errors){
                    Ext.callback(o.success,o.scope,[requestJson.errors]);
                    //o.success(requestJson.errors);
                }else{
                    Ext.callback(o.success,o.scope);
                    //o.success();
                }
            }
            else{
                if (o.failure){
                    if (requestJson && requestJson!=null && requestJson.errors){
                        Ext.callback(o.failure,o.scope,[requestJson.errors]);
                        //o.failure(requestJson.errors);
                    }
                    else{
                        Ext.callback(o.failure,o.scope,['Error']);
                        //o.failure('Error');
                    }
                }
            }
        };
        args.failure=function(result,request){
            if (o.waitMessageBox)
                o.waitMessageBox.hide();
            if (o.failure){
                Ext.callback(o.failure,o.scope,[result.responseText]);
                //o.failure(result.responseText);
            }
        };
        args=this.autoInitConfigRequest(args,o);
        
        this.request(args);
    },
    requestJsonData: function(o){
        var args={};
        args.success=function(result,request) {
            var requestJson=Ext.decode(result.responseText,true);
            if (requestJson && requestJson!=null && requestJson.total && requestJson.total>=0 && requestJson.data){
                Ext.callback(o.success,o.scope,[requestJson]);
                //o.success(requestJson);
            }else{
                Ext.callback(o.failure,o.scope,['Error']);
                //o.failure('Error');
            }
        };
        args.failure=function(result,request){
            if (o.failure){
                if (result.responseText){
                    Ext.callback(o.failure,o.scope,[result.responseText]);
                    //o.failure(result.responseText);
                }else if (result.statusText){
                    Ext.callback(o.failure,o.scope,[result.statusText]);
                    //o.failure(result.statusText);
                }else{
                    Ext.callback(o.failure,o.scope,['']);
                    //o.failure('');
                }
            }
        };
        args=this.autoInitConfigRequest(args,o);
        this.request(args);
    },    
    requestJsonObject: function(o){
        var args={};
        args.success=function(result,request) {
            var requestJson=Ext.decode(result.responseText,true);
            if (requestJson && requestJson!=null){
                Ext.callback(o.success,o.scope,[requestJson]);
                //o.success(requestJson);
            }else{
                Ext.callback(o.failure,o.scope,['Error']);
                //o.failure('Error');
            }
        };
        args.failure=function(result,request){
            if (o.failure){
                if (result.responseText){
                    Ext.callback(o.failure,o.scope,[result.responseText]);
                    //o.failure(result.responseText);
                }else if (result.statusText){
                    Ext.callback(o.failure,o.scope,[result.statusText]);
                    //o.failure(result.statusText);
                }else{
                    Ext.callback(o.failure,o.scope,['']);
                    //o.failure('');
                }
            }
        };        
        args=this.autoInitConfigRequest(args,o);
        this.request(args);
    }
});

/*Ajax =new Ext.data.Connection({
    autoAbort : false,
    serializeForm : function(form){
        return Ext.lib.Ajax.serializeForm(form);
    },
    requestJson: function(o){
        //Sacar mensaje de wait
        if (o.waitTitle || o.waitMsg){
            if (!o.waitTitle) o.waitTitle='Esperando...';
            if (!o.waitMsg) o.waitMsg='Esperando...';
            o.waitMessageBox=Ext.MessageBox.wait(o.waitMsg,o.waitTitle);
        }

        this.request({
            url: o.url,
            //Parametros
            params: o.params,
            success: function(result,request) {
                if (o.waitMessageBox)
                    o.waitMessageBox.hide();
                var requestJson=Ext.decode(result.responseText);
                if (requestJson.success){
                    if (requestJson.errors){
                        o.success(requestJson.errors);
                    }else{
                        o.success();
                    }
                }
                else{
                    if (o.failure){
                        if (requestJson.errors){
                            o.failure(requestJson.errors);
                        }
                        else{
                            o.failure('Error');
                        }
                    }
                }
            },
            failure:function(result,request){
                if (o.waitMessageBox)
                    o.waitMessageBox.hide();
                if (o.failure){
                    o.failure(result.responseText);
                }
            }
        }
        );
    }
});*/
