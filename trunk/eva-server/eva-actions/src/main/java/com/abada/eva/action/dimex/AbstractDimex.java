/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

import com.abada.springframework.web.client.RestTemplate;
import com.abada.springframework.web.client.RestTemplateFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author katsu
 */
public abstract class AbstractDimex<T> {
    private static final Log logger = LogFactory.getLog(AbstractDimex.class);
    
    private RestTemplateFactory templateFactory;
    private String user;
    private String password;
    
    /**
     * List of actions to execute
     */
    private List<DimexAction> actions;        
    private Class<?> tClass;        

    public AbstractDimex() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.tClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }
    
    protected abstract Map<String, Object> getData(Object[] oldMessages, Object[] newMessages);

    protected abstract String getUrl(Map<String, Object> data);

    private T getDimexResult(Map<String, Object> data) throws Exception {
        RestTemplate restTemplate = templateFactory.createInstance();
        restTemplate.setRequestFactory(user, password);

        T result = (T)restTemplate.postForObject(getUrl(data), data, this.tClass);
        return result;
    }
    
    protected void doItPriv(Object[] oldMessages, Object[] newMessages) {
        if (actions != null && actions.size() > 0) {
            try {
                Map<String, Object> data = getData(oldMessages, newMessages);
                T resultFromDimex = getDimexResult(data);

                for (DimexAction ac : actions) {
                    try {
                        ac.doIt(resultFromDimex, data);
                    } catch (Exception e) {
                        if (logger.isErrorEnabled()) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            } catch (Exception e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    } 

    public RestTemplateFactory getTemplateFactory() {
        return templateFactory;
    }

    public void setTemplateFactory(RestTemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DimexAction> getActions() {
        return actions;
    }

    public void setActions(List<DimexAction> actions) {
        this.actions = actions;
    }
    
    
}
