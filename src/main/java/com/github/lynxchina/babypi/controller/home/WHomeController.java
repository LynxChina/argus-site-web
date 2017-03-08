package com.github.lynxchina.babypi.controller.home;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import com.github.lynxchina.babypi.service.model.account.AccountService;
import com.github.lynxchina.babypi.service.model.geo.City;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author chris
 * @version 1/5/16-10:27 AM
 */
@Controller
public class WHomeController {


    @Autowired(required = false)
    private AccountService accountService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(@CookieValue(name = "selectedCity", required = false) String selectedCity, Model model,
            HttpServletResponse response) {

        City city = new City();
        city.name = StringUtils.isNotBlank(selectedCity) ? selectedCity : "上海";
        city.id = "s388";
        city.idx = "S";
        try {
            Cookie cookie = new Cookie("selectedCity", URLEncoder.encode(city.name, "UTF-8"));
            cookie.setMaxAge(600);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        model.addAttribute("name", accountService.findByEmail("chris"))
                .addAttribute("idx", BizConst.HOME_IDX);

        return "home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "account/admin";
    }

    private String getPrincipal() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
