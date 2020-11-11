package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionStatus;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

    private MissionServiceImpl() {
    }

    public static synchronized MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;
        return NassaContext
                .getInstance()
                .retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(s -> s.getMissionStatus() == MissionStatus.IN_PROGRESS)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;
        return Optional.of(NassaContext
                .getInstance()
                .retrieveBaseEntityList(FlightMission.class)
                .stream().filter(s -> s.getMissionStatus() == MissionStatus.IN_PROGRESS)
                .findAny()
                .get());
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        flightMission.setMissionStatus(MissionStatus.IN_PROGRESS);
        return flightMission;
    }

    @Override
    public FlightMission createMission(String flightMissionString) {
        return FlightMissionFactory.getInstance().create(flightMissionString.split(";"));
    }

}
