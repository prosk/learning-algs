package com.mycompany.mytests.inheritance.initorder;

public class StringHolder {
    private String s;

    public StringHolder(String s) {
        System.out.println("String holder constructor [" + s + "]");
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
