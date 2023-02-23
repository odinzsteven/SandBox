package com.odinzsteven.springsandbox.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ImmutableUser implements User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final Long id;

    @Column(unique = true)
    private final String name;

    @Version
    private final Long version;

    public ImmutableUser(Long id, String name, Long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public static ImmutableUser create(String name) {
        return new ImmutableUser(null, name, 0L);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableUser that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "MutableUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
