package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    protected CrewMemberCriteria(Builder builder) {
        super(builder);
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class CrewMemberCriteriaBuilder extends Criteria.Builder<CrewMemberCriteriaBuilder> {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public CrewMemberCriteriaBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder rank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public CrewMemberCriteriaBuilder isReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        @Override
        public Criteria<CrewMember> build() {
            CrewMemberCriteria newCriteria = new CrewMemberCriteria(this);
            newCriteria.rank = this.rank;
            newCriteria.role = this.role;
            newCriteria.isReadyForNextMissions = this.isReadyForNextMissions;
            return newCriteria;
        }
    }
}
