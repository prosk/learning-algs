package com.mycompany.mytests.inheritance.initorder;

public class IntegerHolder {
    private int num;
    public IntegerHolder(String name, int num) {
        System.out.println("Integer holder constructor [" + name + ", " + num + "]");
        this.num = num;
    }
}
