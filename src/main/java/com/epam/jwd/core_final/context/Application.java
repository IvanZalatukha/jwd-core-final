package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.util.*;
import java.util.function.Supplier;

public interface Application {

    static void start() {

        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance; // todo
        final NassaContext nassaContext = NassaContext.getInstance();

        nassaContext.init();
        afterContextInit(applicationContextSupplier::get);

    }

    static void afterContextInit(ApplicationMenu applicationMenu) {
//        timer();
        System.out.println("\n" + "Hello, select an option:" + "\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Select mission");
            System.out.println("2. Create your own mission");
            System.out.println("0. Exit");
            try {
                int s = Integer.parseInt(scanner.nextLine());
                if (s == 0) {
                    break;
                }
                switch (s) {
                    case 1:
                        applicationMenu.handleUserInput(1);
                        break;
                    case 2:
                        applicationMenu.handleUserInput(2);
                        break;
                    default:
                        System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }
        }
    }

//    private static void timer() {
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                NassaContext.getInstance().init();
//            }
//        };
//        timer.schedule(timerTask, ApplicationProperties.getFileRefreshRate());
//    }
}
