package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(1);

        try {
            Application.start();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }







    }
}