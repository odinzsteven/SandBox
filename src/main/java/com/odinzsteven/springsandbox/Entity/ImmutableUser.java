package com.odinzsteven.springsandbox.Entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

import java.util.Objects;

@Entity
@Immutable
@Table(name = "immutable_user")
public final class ImmutableUser implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(unique = true)
    private final String name;

    @Version
    private final Long version;

    public ImmutableUser() {
        this.id = null;
        this.name = null;
        this.version = null;
    }

    public ImmutableUser(Long id, String name, Long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public ImmutableUser(MutableUser mutableUser) {
        this.id = mutableUser.getId();
        this.name = mutableUser.getName();
        this.version = mutableUser.getVersion();
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
        return "ImmutableUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
