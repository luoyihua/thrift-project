package com.yihua;

import java.util.Calendar;
import java.util.Date;

/**
 * @author luoyihua
 * @description TODO
 * @date 2019/11/15 下午9:21
 **/
public class StringTest {

    private static int batch = 10000000;

    public static void main(String[] args) {
        String s = null;
        long begin_time1 = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < batch; i++) {
            isEmpty1(s);
        }
        long end_time1 = Calendar.getInstance().getTimeInMillis();
        System.out.println(end_time1 - begin_time1);

        long begin_time2 = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < batch; i++) {
            isEmpty2(s);
        }
        long end_time2 = Calendar.getInstance().getTimeInMillis();
        System.out.println(end_time2 - begin_time2);

        long begin_time3 = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < batch; i++) {
            isEmpty3(s);
        }
        long end_time3 = Calendar.getInstance().getTimeInMillis();
        System.out.println(end_time3 - begin_time3);

        long begin_time4 = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < batch; i++) {
            isEmpty4(s);
        }
        long end_time4 = Calendar.getInstance().getTimeInMillis();
        System.out.println(end_time4 - begin_time4);
    }

    private static boolean isEmpty1(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        return false;
    }

    private static boolean isEmpty2(String s) {
        if (s == null || s == "") {
            return true;
        }
        return false;
    }

    private static boolean isEmpty3(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return false;
    }

    private static boolean isEmpty4(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }
}
