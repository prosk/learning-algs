package com.mycompany.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FormatSQL {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        String line;
        while((line = r.readLine()) != null)
        {
            sb.append(line);
            sb.append(' ');
        }

        String input = sb.toString().toLowerCase().trim();

        String arr[] = input.split("\\s");

        System.out.println(input);
        int i = 0;
        String curr, prev = "", res = "", lastAddedToRes = "";
        boolean skipToCloseBrackets = false;
        for(i = 0; i < arr.length; i++) {
            curr = arr[i];
            if (i > 0) {
                prev = arr[i - 1];
            }
            if (curr == null || "".equals(curr)) {
                continue;
            }
            if(!")".equals(curr) && skipToCloseBrackets) {
                continue;
            }

        }
    }
}
