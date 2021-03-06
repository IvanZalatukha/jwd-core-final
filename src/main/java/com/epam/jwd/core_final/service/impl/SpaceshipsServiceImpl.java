package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipsServiceImpl implements SpaceshipService {
    private static SpaceshipsServiceImpl instance;

    private SpaceshipsServiceImpl() {
    }

    public static synchronized SpaceshipsServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipsServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return null;
    }

    @Override
    public Collection<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        Collection<Spaceship> list = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        return list.stream().filter(s -> s.getFlightDistance() > spaceshipCriteria.getFlightDistance()).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        return Optional.of(NassaContext
                .getInstance()
                .retrieveBaseEntityList(Spaceship.class)
                .stream().filter(s -> !s.getReadyForNextMissions())
                .findAny()
                .get());
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceship.setReadyForNextMissions(false);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship, FlightMission mission) throws RuntimeException {
        mission.setAssignedSpaceShift(spaceship);
    }

    @Override
    public Spaceship createSpaceship(String spaceshipString) throws RuntimeException {
        return SpaceshipFactory.getInstance().create(spaceshipString.split(";"));
    }
}
