package com.github.lynxchina.babypi.controller.education;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chris
 * @version 1/14/16-2:44 PM
 */
@Controller
public class WEducationController {


    @RequestMapping(value = "/education", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("idx", BizConst.EDUCATION_IDX);
        return "education/education";
    }
}
