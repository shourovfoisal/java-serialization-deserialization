package com.shourov.serialization_and_deserialization.model;

import jakarta.persistence.*;

@Entity
public class SerializedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] serializedUser;

    public SerializedUser() {}

    public SerializedUser(byte[] serializedUser) {
        this.serializedUser = serializedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getSerializedUser() {
        return serializedUser;
    }

    public void setSerializedUser(byte[] serializedUser) {
        this.serializedUser = serializedUser;
    }
}
