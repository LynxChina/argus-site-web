package com.github.lynxchina.babypi.controller.baike;

import com.github.lynxchina.babypi.controller.misc.BizConst;
import com.github.lynxchina.babypi.service.model.baike.BaikeService;
import com.github.lynxchina.babypi.service.model.baike.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chris
 * @version 1/14/16-2:45 PM
 */
@Controller
@RequestMapping(value = "/baike", produces = "text/plain; charset=UTF-8")
public class WBaikeController {

    @Autowired
    private BaikeService baikeService;

    private Map<Category, List<Category>> Categories = new HashMap<>();

    @PostConstruct
    private void init() {
        List<Category> categories = baikeService.getAllCategory();

        for (Category category : categories) {
            if (category.pid == null) {
                Categories.put(category, new ArrayList<>());
            } else {
                for (Category parentCategory : Categories.keySet()) {
                    if (parentCategory.id.equals(category.pid)) {
                        Categories.get(parentCategory).add(category);
                        break;
                    }
                }
            }
        }
    }


    /**
     * 百科首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String baike(Model model) {
        model.addAttribute("idx", BizConst.BAIKE_IDX);
        model.addAttribute("categories", Categories);
        return "baike/baike_index";
    }

    /**
     * 分类文章列表
     *
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{cid}")
    public String list(@PathVariable("cid") String cid,
            @RequestParam(name = "cur_page", required = false) Integer curPage, Model model) {
        List<Integer> pages = new ArrayList<>();
        pages.add(1);
        pages.add(2);
        pages.add(3);
        pages.add(4);
        pages.add(5);
        pages.add(6);
        if (curPage == null) {
            curPage = 1;
        }
        model.addAttribute("test", 1);
        model.addAttribute("idx", BizConst.BAIKE_IDX);
        model.addAttribute("curPage", curPage);
        model.addAttribute("pages", pages);
        model.addAttribute("articles", baikeService.getArticles(cid, 0));
        return "baike/baike_article_list";
    }

    /**
     * 文章详情
     *
     * @param aid
     * @param model
     * @return
     */
    @RequestMapping(value = "/article/{aid}")
    public String detail(@PathVariable("aid") String aid, Model model) {
        model.addAttribute("idx", BizConst.BAIKE_IDX);
        return "baike/baike_article_detail";
    }

    public static void main(String[] args) {
        InputStream in = null;
        try {
            Process pro = Runtime.getRuntime().exec(new String[]{"sh",
                    "/Users/chris/Workspace/android-dex/package.sh", "select admin from M_ADMIN",
                    "/home/test/result.txt"});
            pro.waitFor();
            in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String result = read.readLine();
            System.out.println("INFO:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
