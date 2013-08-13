package com.abada.eva.action.dimex;

/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.abada.springframework.web.client.RestTemplate;
import com.abada.springframework.web.client.RestTemplateFactory;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
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

    protected abstract URI getUrl(Map<String, Object> data);

    private T getDimexResult(Map<String, Object> data) throws Exception {
        RestTemplate restTemplate = templateFactory.createInstance();
        restTemplate.setRequestFactory(user, password);

        T result = (T) restTemplate.postForObject(getUrl(data), data, this.tClass);
        return result;
    }

    protected void doItPriv(Object[] oldMessages, Object[] newMessages) {
        if (actions != null && actions.size() > 0) {
            try {
                Map<String, Object> data = getData(oldMessages, newMessages);
                if (data != null) {
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
                }else{
                    if (logger.isWarnEnabled())
                        logger.warn("No data return from getData. So no call to actions.");
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
