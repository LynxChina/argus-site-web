package com.github.lynxchina.babypi.test;

import com.github.lynxchina.babypi.BabyPiWebApp;
import com.github.lynxchina.babypi.config.ServiceConfig;
import com.github.lynxchina.babypi.service.model.account.Account;
import com.github.lynxchina.babypi.service.model.account.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author chris
 * @version 1/21/16-4:47 PM
 */
@IntegrationTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BabyPiWebApp.class, ServiceConfig.class})
public class ServiceCallTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void shouldSayHello() {
        Account account = accountService.findByEmail("chris");
        System.err.println(account.email);
    }
}
