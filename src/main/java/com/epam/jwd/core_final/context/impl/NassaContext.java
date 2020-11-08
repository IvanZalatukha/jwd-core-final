package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.strategy.ContextInputStrategy;
import com.epam.jwd.core_final.context.strategy.HorizontalInputStrategy;
import com.epam.jwd.core_final.context.strategy.VerticalInputStrategy;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipsServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext instance;

    private NassaContext() {
    }
    public static synchronized NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName())) {
            return (Collection<T>) crewMembers;
        } else if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        }else if (tClass.getName().equals(FlightMission.class.getName())) {
            return (Collection<T>) flightMissions;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        try {
            PropertyReaderUtil.loadProperties();
            populateCollections();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        throw new InvalidStateException();


    }

    private void populateCollections() throws  IOException {
        ContextInputStrategy contextInputStrategy = new ContextInputStrategy();
        contextInputStrategy.setInputStrategy(new HorizontalInputStrategy());

        List<String> crewMembersStringList = contextInputStrategy.executeStrategy("src/main/resources/input/crew");
            for (String str: crewMembersStringList) {
                crewMembers.add(CrewServiceImpl.getInstance().createCrewMember(str));
            }

        contextInputStrategy.setInputStrategy(new VerticalInputStrategy());

        List<String> spaceshipStringList = contextInputStrategy.executeStrategy("src/main/resources/input/spaceships");
        for (String str: spaceshipStringList) {
            spaceships.add(SpaceshipsServiceImpl.getInstance().createSpaceship(str));
        }

        List<String> missionsStringList = contextInputStrategy.executeStrategy("src/main/resources/input/missions");
        for (String str: missionsStringList) {
            flightMissions.add(MissionServiceImpl.getInstance().createMission(str));
        }


    }



}
