package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static void start() throws InvalidStateException {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance; // todo
        final NassaContext nassaContext = NassaContext.getInstance();

        nassaContext.init();
        afterContextInit(applicationContextSupplier::get);
    }

    private static void afterContextInit(ApplicationMenu applicationMenu) {

    }
}

//    ApplicationMenu menu = Application.start();
//menu.printAvailableOptions();
//
//        ApplicationMenu menu1 = () -> {
//        NassaContext nassaContext = new NassaContext();
//        nassaContext.init();
//        return nassaContext;
//        };