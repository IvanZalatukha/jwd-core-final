package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;

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
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(String spaceshipString) throws RuntimeException {
        return SpaceshipFactory.getInstance().create(spaceshipString.split(";"));
    }
}
