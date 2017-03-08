package com.github.lynxchina.babypi.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chris.liu
 * @version 3/22/16-5:30 PM
 */
@Controller
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class HotPatchManagerController {

    @RequestMapping(value = "/hotpatch", method = RequestMethod.GET)
    public String my(Model model, HttpServletResponse response) {

        return "admin/hot_patch_manager";
    }
}
