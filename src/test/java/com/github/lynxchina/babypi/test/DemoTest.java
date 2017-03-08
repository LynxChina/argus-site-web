package com.github.lynxchina.babypi.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chris.liu
 * @version 8/1/16-2:51 PM
 */
public class DemoTest {


    public static void main(String[] args) {
        long dt = 1470301200000l;

        System.out.println(new Date().getTime());


        DateFormat df = new SimpleDateFormat("MM-dd h");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dt));
        System.out.println(String.format("%s%s", calendar.get(Calendar.HOUR_OF_DAY) < 12 ? "上午" : "下午",
                calendar.get(Calendar.HOUR)));
    }
}
