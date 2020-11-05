package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.strategy.ContextInputStrategy;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName())) {
            return (Collection<T>) crewMembers;
        } else if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
//        throw new InvalidStateException();
        //vizivaet strategiu parsa faila
        //s4itali zapis`
        //vizvali method servisa(servis.create) peredali nyjnie argymenti
        //method servis vizval s etimi argumentami fabriky
        //factory sozdal obekt
        //factory vernyl obekt servisy i td
        //esli obekt norm, pomewaem v collecction

    }
}
