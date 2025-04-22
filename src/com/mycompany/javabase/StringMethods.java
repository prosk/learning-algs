package com.mycompany.javabase;

import java.util.Arrays;

public class StringMethods {

    public static void main(String[] args) {
        String str = "asdfqwer";

        // *******************************************************************
        // substring
        // *******************************************************************
        System.out.println("*******************************************************************");
        System.out.println("METHOD substring");
        System.out.println(str.substring(2));
        //String wrong1 = str.substring(90); //StringIndexOutOfBoundsException
        //System.out.println(wrong1);
        System.out.println(str.substring(1, 4));
        System.out.println("*" + str.substring(4, 5) + "*");
        // если начальный и конечный индексы совпадают то ответ пустая строка
        System.out.println("*" + str.substring(4, 4) + "*");

        // если beginIndex = длине строки то ответ пустая строка
        System.out.println("*" + "emptiness".substring(9) + "*");

        System.out.println(str.substring(4, 8));
        // System.out.println(str.substring(4, 9)); // exception
        System.out.println("*******************************************************************");

        // *******************************************************************
        // toUpperCase   toLowerCase
        // *******************************************************************
        System.out.println("*******************************************************************");
        System.out.println("METHOD toUpperCase  toLowerCase");
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println("*******************************************************************");

        // *******************************************************************
        // toUpperCase   toCharArray
        // *******************************************************************
        System.out.println("*******************************************************************");
        System.out.println("METHOD toCharArray  String.valueOf(arr, offset, count)  get string sorted");
        char[] strArr = str.toCharArray();
        String substrFromArr = String.valueOf(strArr, 1, 3);
        System.out.println(substrFromArr);
        // get string with sorted characters
        Arrays.sort(strArr);
        String sorted = new String(strArr);
        System.out.println(sorted);
        System.out.println("*******************************************************************");

        // *******************************************************************
        // character operations
        // *******************************************************************
        System.out.println("*******************************************************************");
        System.out.println("METHOD character operations");
        int offset1 = 'a' - 'a';
        int offset2 = 'd' - 'a';
        int offset3 = '0' - '0';
        int offset4 = '2' - '0';
        System.out.println("offset1 = " + offset1);
        System.out.println("offset2 = " + offset2);
        System.out.println("offset3 = " + offset3);
        System.out.println("offset4 = " + offset4);
        // ASCII
        // цифры 0123456789 начинаются с 48 и идут последовательно до 48+10-1 = 57
        // большие буквы с A = 65 до 65 + 26 - 1 = 90 = Z
        // маленькие буквы с a = 97 до 97 + 26 - 1 = 122 = z
        offset1 = 'a' - 97;
        offset2 = 'd' - 97;
        offset3 = '0' - 48;
        offset4 = '2' - 48;
        System.out.println("offset1 = " + offset1);
        System.out.println("offset2 = " + offset2);
        System.out.println("offset3 = " + offset3);
        System.out.println("offset4 = " + offset4);
    }
}
