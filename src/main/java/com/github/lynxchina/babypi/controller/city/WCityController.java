package com.github.lynxchina.babypi.controller.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lynxchina.babypi.service.model.geo.City;
import com.github.lynxchina.babypi.service.model.geo.CityService;
import com.github.lynxchina.babypi.service.model.geo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chris
 * @version 1/14/16-11:40 AM
 */
@Controller
public class WCityController {


    @Autowired
    private CityService cityService;

    private static ObjectMapper mapper = new ObjectMapper();

    private static List<ProvinceVO> PROVINCES = new ArrayList<>();

    @PostConstruct
    private void init() {
        Map<String, Province> provinces = cityService.getAllCity();
        for (Province province : provinces.values()) {
            ProvinceVO pvo = new ProvinceVO();
            pvo.pid = province.pid;
            pvo.id = province.id;
            pvo.name = province.name;
            pvo.fullName = province.fullName;
            pvo.type = province.type;
            pvo.idx = province.idx;
            pvo.code = province.code;
            pvo.cityRows = new ArrayList<>();

            if (province.citys == null) {
                continue;
            }

            for (City city : province.citys) {
                int curRow = pvo.cityRows.size();
                List<City> lstRow;

                if (curRow == 0) {
                    lstRow = new ArrayList<>();
                    pvo.cityRows.add(lstRow);
                    lstRow.add(city);
                } else {
                    lstRow = pvo.cityRows.get(curRow - 1);
                    if (lstRow == null) {
                        lstRow = new ArrayList<>();
                        pvo.cityRows.add(lstRow);
                        lstRow.add(city);
                    } else {
                        if (lstRow.size() < 10) {
                            lstRow.add(city);
                        } else {
                            lstRow = new ArrayList<>();
                            pvo.cityRows.add(lstRow);
                            lstRow.add(city);
                        }
                    }
                }
            }

            PROVINCES.add(pvo);
        }
    }

    @RequestMapping(value = "/all_city", method = RequestMethod.GET)
    public String allCity(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
            Model model) {
        City city = new City();
        city.name = name;
        city.id = "s388";
        city.idx = "S";
        model.addAttribute("def_city", city);
        model.addAttribute("hot_cities", cityService.getHotCities());
        model.addAttribute("all_city", PROVINCES);
        return "all_city";
    }

    @RequestMapping(value = "/select_city", method = RequestMethod.POST)
    public String selectCity(@RequestParam(value = "name") String name, HttpServletResponse response) {
        City city = new City();
        city.name = name;
        city.id = "s388";
        city.idx = "S";
        try {
            Cookie cookie = new Cookie("selectedCity", URLEncoder.encode(name, "UTF-8"));
            cookie.setMaxAge(600);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "home";
    }

}
