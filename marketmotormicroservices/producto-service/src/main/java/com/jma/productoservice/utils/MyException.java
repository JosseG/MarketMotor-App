package com.jma.productoservice.utils;

import java.io.PrintWriter;

public class MyException extends  Exception{


    String message = "";

    public MyException(String message) {

        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return "Error " +message;
    }


    @Override
    public String toString() {
        return "Error "  +message;

    }
}
