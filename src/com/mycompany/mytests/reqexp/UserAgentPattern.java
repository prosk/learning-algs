package com.mycompany.mytests.reqexp;

public class UserAgentPattern {
    public static void main(String[] args) {
        String[] testValues = {"AtonApp/3.0.0_60 (iOS 18.0)", "AtonApp/3.0.1_40836 (Android 12)",
            "SalesApp/3.1.0_1 (iOS 17.2)", "SalesApp/4.0.0_1 (iOS 15.4)"};
        for(String val: testValues) {
            boolean isValid = val.matches("^SalesApp.*$");
            System.out.println(val + " is " + isValid);
        }
    }
}
