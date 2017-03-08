package com.github.lynxchina.babypi.controller.account;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import com.github.lynxchina.babypi.service.model.account.AccountService;
import com.github.lynxchina.babypi.service.model.account.TokenService;
import com.github.lynxchina.babypi.service.model.account.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author chris
 * @version 1/8/16-3:51 PM
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class WAccountController {


    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private TokenService tokenService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String my(@CookieValue(name = "token", required = false) String token, Model model,
            HttpServletResponse response) {

        if (StringUtils.isNotBlank(token)) {
            try {
                User user = tokenService.validate(token);

                if (user == null) {
                    return "account/login";
                }
            } catch (Exception e) {

            }

            model.addAttribute("name", accountService.findByEmail("chris"))
                    .addAttribute("idx", BizConst.ACCOUNT_IDX);
            return "account/profile";
        }

        return "account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "pwd", required = false) String pwd, Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "ac23f4467djr782dxy92103000goo");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@CookieValue(name = "token") String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "ac23f4467djr782dxy92103000goo");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "home";
    }

    @RequestMapping(value = "/upload_avatar", method = RequestMethod.POST)
    @ResponseBody
    public String uploadAvatar(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
