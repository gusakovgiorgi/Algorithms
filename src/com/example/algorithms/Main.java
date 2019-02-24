package com.example.algorithms;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ;

        A a = new B();
        System.out.println(a.toString());

    }
}

class A {
    @Override
    public String toString() {
        return "A{}";
    }
}

class B extends A {
    @Override
    public String toString() {
        return "B{}";
    }
}