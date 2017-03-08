package com.github.lynxchina.babypi.misc;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author chris
 * @version 1/22/16-5:35 PM
 */
public class HessianServiceUtil {

    public static final HessianProxyFactoryBean generateService(String url, Class<?> service) {
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl(url);
        factoryBean.setServiceInterface(service);
        return factoryBean;
    }
}
