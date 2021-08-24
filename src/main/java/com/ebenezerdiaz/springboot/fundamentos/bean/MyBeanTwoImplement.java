package com.ebenezerdiaz.springboot.fundamentos.bean;

public class MyBeanTwoImplement implements MyBean{

    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion propia del bean dos!!");
    }
}
