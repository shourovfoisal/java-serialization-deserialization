package com.shourov.serialization_and_deserialization.service;

import com.shourov.serialization_and_deserialization.dao.SerializedUserDao;
import com.shourov.serialization_and_deserialization.dto.User;
import com.shourov.serialization_and_deserialization.model.SerializedUser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerializedUserService {

    private final SerializedUserDao serializedUserDao;

    public SerializedUserService(SerializedUserDao serializedUserDao) {
        this.serializedUserDao = serializedUserDao;
    }

    private byte[] convertUserToByteArray(User user) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(user);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private User convertByteArrayToUser(byte[] serializedUser) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedUser);

            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (User) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSerializedUser(User user) {
        byte[] serializedUserData = convertUserToByteArray(user);
        serializedUserDao.save(new SerializedUser(serializedUserData));
    }

    public List<User> getDeserializedUserList() {
        return serializedUserDao
                .findAll()
                .stream()
                .map(serializedUser -> convertByteArrayToUser(serializedUser.getSerializedUser()))
                .collect(Collectors.toList());
    }
}
