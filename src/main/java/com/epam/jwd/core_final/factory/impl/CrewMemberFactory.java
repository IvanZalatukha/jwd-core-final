package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    private static CrewMemberFactory instance;
    Long id = 0L;

    private CrewMemberFactory() {
    }

    public static synchronized CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(String[] strings) {
        Role role = Role.resolveRoleById(Integer.parseInt(strings[0]));
        String name = strings[1];
        Rank rank = Rank.resolveRankById(Integer.parseInt(strings[2]));
        CrewMember crewMember = new CrewMember(role, name, rank);
        crewMember.setId(++id);
        return crewMember;
    }
}
