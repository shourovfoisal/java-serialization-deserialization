package com.shourov.serialization_and_deserialization.controller;

import com.shourov.serialization_and_deserialization.dto.User;
import com.shourov.serialization_and_deserialization.service.SerializedUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SerializationController {

    private final SerializedUserService serializedUserService;

    public SerializationController(SerializedUserService serializedUserService) {
        this.serializedUserService = serializedUserService;
    }

    @PostMapping("/serialize")
    public String saveSerializedUser(@RequestBody final User user) {
        serializedUserService.saveSerializedUser(user);
        return "User data is saved.";
    }

    @GetMapping("/deserialize")
    public List<User> getDeserializedUserList() {
        return serializedUserService.getDeserializedUserList();
    }
}
