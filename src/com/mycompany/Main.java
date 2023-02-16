package com.mycompany;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static void main(String[] args) {
        String regex = "^[\\u0401\\u0451\\u0410-\\u044f\\s]+$";
        System.out.println("фывфыва".matches(regex));
        System.out.println("ЬИТЬИТЬИТ ьтваифьывтифыв йцуйцлуордлцуркдйцжодув фывфыв".matches(regex));
        System.out.println("Сиги змундовна Ал а н ицикали     мия Ферокаторавна".matches(regex));
        System.out.println("ЬИТЬИТЬИТ ьтваифьывтифыв йцуйцлуордлцуркдйцжодув фывфыв".matches(regex));
        System.out.println(" Сигизмундовна Аланицикалимия Ферокаторавна".matches(regex));

        System.out.println("фывфыва123123123".matches(regex));
        System.out.println("AjhgdhsdjgfЬИТЬИТЬИТ ьтваифьывтифыв йцуйцлуордлцуркдйцжодув фывфыв".matches(regex));
        System.out.println("jfhskjdfhwer".matches(regex));
        System.out.println("6734682734".matches(regex));
        System.out.println("лофрывлофрывлоыфр$&".matches(regex));

        //System.out.println(validate(null));
        //System.out.println(validate(""));
        //System.out.println(validate("asdasd"));
        //System.out.println(validate("a@b.ru"));

 /*       String regex = "^(\\d){4,5}$";

        // positive test cases, should all be "true"
        System.out.println("00".matches(regex));
        System.out.println("1".matches(regex));
        System.out.println("1234".matches(regex));
        System.out.println("12345".matches(regex));
        System.out.println("123456789".matches(regex));

       // negative test cases, should all be "false"
        System.out.println(" ".matches(regex));
        System.out.println("".matches(regex));
        System.out.println("foo".matches(regex));
        System.out.println("aa123bb".matches(regex));*/
    }
}
