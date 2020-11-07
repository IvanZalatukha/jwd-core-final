package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    Map<Role, Short> crew;
    Long flightDistance;
    Boolean isReadyForNextMissions;

    protected SpaceshipCriteria(Builder builder) {
        super(builder);
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class SpaceshipCriteriaBuilder extends Criteria.Builder<SpaceshipCriteria.SpaceshipCriteriaBuilder> {
        Map<Role, Short> crew;
        Long flightDistance;
        Boolean isReadyForNextMissions;

        public SpaceshipCriteriaBuilder crew(Map<Role, Short> newCrew) {
            this.crew = newCrew;
            return this;
        }

        public SpaceshipCriteriaBuilder flightDistance(Long newFlightDistance) {
            this.flightDistance = newFlightDistance;
            return this;
        }

        public SpaceshipCriteriaBuilder isReadyForNextMissions(Boolean newIsReadyForNextMissions) {
            this.isReadyForNextMissions = newIsReadyForNextMissions;
            return this;
        }

        @Override
        public Criteria<Spaceship> build() {
            SpaceshipCriteria criteria = new SpaceshipCriteria(this);
            criteria.crew = this.crew;
            criteria.flightDistance = this.flightDistance;
            criteria.isReadyForNextMissions = this.isReadyForNextMissions;
            return criteria;
        }
    }
}
