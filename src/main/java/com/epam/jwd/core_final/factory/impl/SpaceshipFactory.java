package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private static SpaceshipFactory instance;
    Long id = 0L;

    private SpaceshipFactory() {
    }

    public static synchronized SpaceshipFactory getInstance() {
        if (instance == null) {
            instance = new SpaceshipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(String[] strings) {
        String spaceshipName = strings[0];
        Long flightDistance = Long.parseLong(strings[1]);
        Map<Role, Short> crew = new HashMap<>();
        String[] s = strings[2].substring(1, strings[2].length() - 1).split(",");
        Arrays.stream(s).forEach(s1 -> {
            Role roleKey = Role.resolveRoleById(Integer.parseInt(String.valueOf(s1.charAt(0))));
            Short shortValue = Short.parseShort(String.valueOf(s1.charAt(2)));
            crew.put(roleKey, shortValue);
        });
        Spaceship newSpaceship = new Spaceship(spaceshipName, crew, flightDistance);
        newSpaceship.setId(++id);
        return newSpaceship;
    }
}
//Challenger;201117;{1:5,2:9,3:3,4:3}