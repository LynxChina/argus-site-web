package com.github.lynxchina.babypi.controller.fun;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chris
 * @version 1/14/16-11:41 AM
 */
@Controller
public class WFunController {


    @RequestMapping(value = "/fun", method = RequestMethod.GET)
    public String fun(Model model) {
        model.addAttribute("name", "");
        model.addAttribute("idx", BizConst.FUN_IDX);
        return "fun/fun";
    }

}
