package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    private String name;
    private Integer id;

    protected Criteria(Builder<?> builder) {
        this.name = builder.name;
        this.id = builder.id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String name;
        private Integer id;

        public T name(String newName) {
            this.name = newName;
            return (T) this;
        }

        public T id(Integer newId) {
            this.id = newId;
            return (T) this;
        }

        public abstract Criteria<? extends BaseEntity> build();
    }

}
