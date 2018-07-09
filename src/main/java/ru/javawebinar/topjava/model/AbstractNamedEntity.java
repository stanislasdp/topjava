package ru.javawebinar.topjava.model;

public class AbstractNamedEntity extends AbstractBaseEntity {

    protected String name;

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s ('%s', '%s')", getClass().getName(), id, name);
    }
}
