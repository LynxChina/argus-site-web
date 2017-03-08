package com.github.lynxchina.babypi.controller.outing;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author chris
 * @version 1/14/16-11:43 AM
 */
@Controller
public class WOutingController {


    @RequestMapping(value = "/outing", method = RequestMethod.GET)
    public String outing(Model model) {
        model.addAttribute("idx", BizConst.OUTING_IDX);
        return "outing/outing";
    }
}
