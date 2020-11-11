package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    private CrewServiceImpl() {
    }

    public static synchronized CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;
        crewMemberCriteria.getReadyForNextMissions();
        return NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(s -> s.getReadyForNextMissions() == crewMemberCriteria.getReadyForNextMissions())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;

        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMember.setReadyForNextMissions(false);
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(List<CrewMember> crewMembers, FlightMission flightMission) throws RuntimeException {
    flightMission.setAssignedCrew(crewMembers);
    }

    @Override
    public CrewMember createCrewMember(String crewMemberString) throws RuntimeException {
        return CrewMemberFactory.getInstance().create(crewMemberString.split(","));
    }
}
