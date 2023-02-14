package com.example.demo;

public class Printer<T> {
    T printerThing;

    public T getPrinterThing() {
        return printerThing;
    }

    public void setPrinterThing(T printerThing) {
        this.printerThing = printerThing;
    }

    public Printer(T printerThing) {
        this.printerThing = printerThing;
    }

    public void printIt(){
        System.out.println(printerThing);
    }
}
