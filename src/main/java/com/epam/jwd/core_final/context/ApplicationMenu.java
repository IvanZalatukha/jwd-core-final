package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default Object printAvailableOptions() {
        return null;
    }

    default Object handleUserInput(Object o) {
        if (o.equals(1)) {
            NassaContext.getInstance()
                    .retrieveBaseEntityList(FlightMission.class)
                    .forEach(System.out::println);
            System.out.println("0. Return to the previous menu");
            FlightMission flightMission = chooseMission();
            System.out.println("You have chosen a mission to " + flightMission.getName());
            chooseMissionOption(flightMission);
        } else if (o.equals(2)) {

        }
        return null;
    }

    private FlightMission chooseMission() {
        Scanner scanner = new Scanner(System.in);
        int s = Integer.parseInt(scanner.nextLine());
        if (s == 0) {
            return null;
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
            Scanner scanner = new Scanner(System.in);
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
                list.add(sortedList.get(i));
                }
            }
            return list;
        }
    private void chooseMissionOptionTwo(FlightMission flightMission) {
        while (true) {
            System.out.println("Your spaceship is: " + chooseSpaceshipOption(flightMission));
            System.out.println("2. Assign crew");
            System.out.println("3. Send mission to: " + flightMission.getName());
            System.out.println("0. Return to the previous menu");
            Scanner scanner = new Scanner(System.in);
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
            Scanner scanner = new Scanner(System.in);
            try {
                int selectedNumber = Integer.parseInt(scanner.nextLine());
                if (selectedNumber == 0) {
                    break;
                }
                switch (selectedNumber) {
                    case 2:
                        flightMission.setAssignedCrew(chooseCrewOption(chooseSpaceshipOption(flightMission), flightMission));
                        System.out.println("Your crew is: " + flightMission.getAssignedCrew());
                        break;
                    case 3:
                    sendMission(flightMission);
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

    private void sendMission(FlightMission flightMission) {
//        LocalDateTime time = new LocalDateTime()
//        flightMission.setStartDate();
    }
}
