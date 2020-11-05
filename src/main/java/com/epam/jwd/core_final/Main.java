package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Application.start();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }






    }
}