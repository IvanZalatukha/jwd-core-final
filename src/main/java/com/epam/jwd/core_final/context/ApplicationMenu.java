package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();
    Scanner scanner = new Scanner(System.in);

    default Object printAvailableOptions() {
        return null;
    }

    default Object handleUserInput(Object o) {
        if (o.equals(1)) {
            NassaContext.getInstance()
                    .retrieveBaseEntityList(FlightMission.class)
                    .forEach(System.out::println);
            System.out.println("0. Return to the previous menu");
            int s = Integer.parseInt(scanner.nextLine());
            FlightMission flightMission = chooseMission(s);
            System.out.println("You have chosen a mission to " + flightMission.getName());
            chooseMissionOption(flightMission);
        } else if (o.equals(2)) {
            createYourOwnMission();
        }
        return null;
    }

    private FlightMission chooseMission(int s) {

        if (s == 0) {
            final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance;
            Application.afterContextInit(applicationContextSupplier::get);
        }
        FlightMission flightMission = null;
        Collection<FlightMission> list = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
        for (FlightMission fm : list) {
            if (fm.getId() == s) {
                flightMission = fm;
            }
        }
        return flightMission;
    }

    private void chooseMissionOption(FlightMission flightMission) {
        while (true) {
            System.out.println("1. Assign a spaceship that can fly " + flightMission.getDistance() + " kilometers");
            System.out.println("2. Assign crew");
            System.out.println("3. Send mission to: " + flightMission.getName());
            System.out.println("0. Return to the main menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                switch (selectedNumber) {
                    case 1:
                        chooseMissionOptionTwo(flightMission);
                        break;
                    case 2:
                        System.out.println("Please select the spaceship first");
                        break;
                    case 3:
                        System.out.println("Please select the spaceship and crew first");
                    default:
                        System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }

        }
    }

    private Spaceship chooseSpaceshipOption(FlightMission flightMission) {
        Collection<Spaceship> list = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        return list
                .stream()
                .filter(s -> s.getFlightDistance() > flightMission.getDistance())
                .findAny()
                .get();

    }

    private List<CrewMember> chooseCrewOption(Spaceship spaceship, FlightMission flightMission) {
        Collection<CrewMember> crewMemberCollection = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        List<CrewMember> list = new ArrayList<>();
        for (Map.Entry<Role, Short> c : spaceship.getCrew().entrySet()) {
            List<CrewMember> sortedList = crewMemberCollection.stream().filter(s -> s.getRole() == c.getKey()).collect(Collectors.toList());
            for (int i = 0; i < c.getValue(); i++) {
                sortedList.get(i).setReadyForNextMissions(false);
                list.add(sortedList.get(i));
            }
        }
        return list;
    }

    private void chooseMissionOptionTwo(FlightMission flightMission) {
        flightMission.setAssignedSpaceShift(chooseSpaceshipOption(flightMission));
        while (true) {
            System.out.println("Your spaceship is: " + chooseSpaceshipOption(flightMission));
            System.out.println("2. Assign crew");
            System.out.println("3. Send mission to: " + flightMission.getName());
            System.out.println("0. Return to the previous menu");

            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                switch (selectedNumber) {
                    case 2:
                        flightMission.setAssignedCrew(chooseCrewOption(chooseSpaceshipOption(flightMission), flightMission));
                        chooseMissionOptionThree(flightMission);
                        break;
                    case 3:
                        System.out.println("Please select the spaceship and crew first");
                        break;
                    default:
                        System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }

        }
    }

    private void chooseMissionOptionThree(FlightMission flightMission) {
        while (true) {
            System.out.println("Your spaceship is: " + chooseSpaceshipOption(flightMission));
            System.out.println("Your crew is: " + flightMission.getAssignedCrew());
            System.out.println("3. Send mission to: " + flightMission.getName());
            System.out.println("0. Return to the previous menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                if (selectedNumber == 3) {
                    sendMission(flightMission);
                } else {
                    System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }

        }
    }

    private void sendMission(FlightMission flightMission) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime endMission = LocalDateTime.now().plusMinutes(2);
        String endMissionString = dateTime.plusMinutes(2).format(DateTimeFormatter.ofPattern(ApplicationProperties.getDateTimeFormat()));
        flightMission.setStartDate(dateTime);
        flightMission.setEndDate(endMission);
        flightMission.setMissionStatus(MissionStatus.IN_PROGRESS);
        while (true) {
            System.out.println("You have successfully sent a mission to " + flightMission.getName());
            System.out.println("Mission end date set for " + endMissionString);
            System.out.println("The mission will reach " + flightMission.getName() + " in " +
                    (10 * endMission.getSecond() - LocalDateTime.now().getSecond()) + " seconds");
            System.out.println("1. Upload mission information to file");
            System.out.println("0. Return to the previous menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                if (selectedNumber == 1) {
                    uploadMissionInformation(flightMission);
                } else {
                    System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }
        }
    }
    private void uploadMissionInformation (FlightMission flightMission) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/output"), flightMission);
        LocalDateTime endMission = LocalDateTime.now().plusMinutes(2);

        while (true) {
            System.out.println("The mission will reach " + flightMission.getName() + " in " +
                    (10 * endMission.getSecond() - LocalDateTime.now().getSecond()) + " seconds");
            System.out.println("You have successfully uploaded the mission information to a file");
            System.out.println("0. Return to the main menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                if (selectedNumber == 1) {
                    uploadMissionInformation(flightMission);
                } else {
                    System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }
        }
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance;
        Application.afterContextInit(applicationContextSupplier::get);
        System.exit(0);
    }
    private void createYourOwnMission() {
        while (true) {
            System.out.println("1. Enter the name of the planet");
            System.out.println("2. Enter distance");
            System.out.println("0. Return to the main menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                switch (selectedNumber) {
                    case 1:
                        System.out.println("Enter the name of the planet");
                        FlightMission flightMission = new FlightMission(scanner.nextLine(),null);
                        enterTheDistance(flightMission);
                        break;
                    case 2:
                        System.out.println("Please enter the distance first");
                        break;
                    default:
                        System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }
        }
    }
    private void enterTheDistance(FlightMission flightMission){
        while (true) {
            System.out.println("You go to the planet " + flightMission.getName());
            System.out.println("2. Enter distance");
            System.out.println("0. Return to the main menu");
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                if (selectedNumber == 2) {
                    System.out.println("Enter the distance");
                    flightMission.setDistance(Long.parseLong(scanner.nextLine()));
                    chooseMissionOption(flightMission);
                } else {
                    System.out.println("\n" + "There is no such option, please choose from the offered options:" + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the ones below:" + "\n");
            }
        }

    }





}