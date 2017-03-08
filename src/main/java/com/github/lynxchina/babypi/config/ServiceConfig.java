package com.github.lynxchina.babypi.config;

import com.github.lynxchina.babypi.misc.HessianServiceUtil;
import com.github.lynxchina.babypi.service.model.account.AccountService;
import com.github.lynxchina.babypi.service.model.account.TokenService;
import com.github.lynxchina.babypi.service.model.baike.BaikeService;
import com.github.lynxchina.babypi.service.model.geo.CityService;
import com.github.lynxchina.babypi.service.model.geo.LocationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author chris
 * @version 1/21/16-4:44 PM
 */
@Configuration
public class ServiceConfig {

    @Bean(name = "accountService")
    public HessianProxyFactoryBean accountService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/AccountService", AccountService.class);
    }

    @Bean(name = "tokenService")
    public HessianProxyFactoryBean tokenService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/TokenService", TokenService.class);
    }

    @Bean(name = "pluginStoreService")
    public HessianProxyFactoryBean pluginStoreService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/PluginStoreService", TokenService.class);
    }

    @Bean(name="baikeService")
    public HessianProxyFactoryBean baikeService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/BaikeService", BaikeService.class);
    }

    @Bean(name = "cityService")
    public HessianProxyFactoryBean cityService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/CityService", CityService.class);
    }

    @Bean(name = "locationService")
    public HessianProxyFactoryBean locationService() {
        return HessianServiceUtil.generateService("http://172.24.52.63:8080/LocationService", LocationService.class);
    }
}
