package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    private static FlightMissionFactory instance;
    Long id = 0L;
    private FlightMissionFactory() {
    }

    public static synchronized FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(String[] strings) {
        FlightMission flightMission = new FlightMission(strings[0], Long.parseLong(strings[1]));
        flightMission.setId(++id);
        return flightMission;
    }
}
