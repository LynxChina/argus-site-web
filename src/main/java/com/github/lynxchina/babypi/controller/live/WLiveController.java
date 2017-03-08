package com.github.lynxchina.babypi.controller.live;

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
public class WLiveController {


    @RequestMapping(value = "/live", method = RequestMethod.GET)
    public String live(Model model) {
        model.addAttribute("idx", BizConst.LIVE_IDX);
        return "live/live";
    }
}
