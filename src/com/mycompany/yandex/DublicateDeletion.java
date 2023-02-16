package com.mycompany.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DublicateDeletion {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(r.readLine());
        int curr, prev = 0;
        for (int i = 0; i < cnt; i++) {
            curr = Integer.parseInt(r.readLine());
            if (i == 0) {
                System.out.println(curr);
                prev = curr;
                continue;
            }
            if (curr != prev) {
                System.out.println(curr);
            }
            prev = curr;
        }
     }
}
