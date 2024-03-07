package ru.stepup.entities;

import java.util.Optional;

public class User {
    private Optional<Long> id;

    private String username;

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username) {
        this.id = Optional.of(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }
}
