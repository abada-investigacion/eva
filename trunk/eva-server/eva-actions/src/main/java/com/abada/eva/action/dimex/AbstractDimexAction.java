/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

import ca.uhn.hl7v2.model.Message;
import com.abada.eva.api.Action;
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
public abstract class AbstractDimexAction<T> implements Action {

    private static final Log logger = LogFactory.getLog(AbstractDimexAction.class);
    /**
     * List of actions to execute
     */
    private List<DimexAction> actions;
    private RestTemplateFactory templateFactory;
    private String user;
    private String password;
    private Class<?> tClass;

    public AbstractDimexAction() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.tClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    public void doIt(Message[] oldMessages, Message[] newMessages) {
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

    protected abstract Map<String, Object> getData(Message[] oldMessages, Message[] newMessages);

    protected abstract String getUrl(Map<String, Object> data);

    private T getDimexResult(Map<String, Object> data) throws Exception {
        RestTemplate restTemplate = templateFactory.createInstance();
        restTemplate.setRequestFactory(user, password);

        T result = (T)restTemplate.postForObject(getUrl(data), data, this.tClass);
        return result;
    }
}