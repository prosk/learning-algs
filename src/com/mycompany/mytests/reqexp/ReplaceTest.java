package com.mycompany.mytests.reqexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTest {
    public static void main(String[] args) {
        Pattern pp = Pattern.compile("%(.*?)%");
        String inputString = "sdfsdfsdf %aaa% qweqweqwe";
        Matcher matcher = pp.matcher(inputString);
        if (matcher.find()) {
            String res = matcher.group(1);
            System.out.println(res);
        }
    }
}
