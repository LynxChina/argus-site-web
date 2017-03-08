package com.github.lynxchina.babypi.controller.dress;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chris
 * @version 1/14/16-11:42 AM
 */
@Controller
public class WDressController {

    @RequestMapping(value = "/dress", method = RequestMethod.GET)
    public String dress(Model model) {
        model.addAttribute("idx", BizConst.DRESS_IDX);
        return "dress/dress";
    }
}
