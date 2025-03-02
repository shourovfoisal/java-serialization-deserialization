package com.shourov.serialization_and_deserialization.dao;

import com.shourov.serialization_and_deserialization.model.SerializedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerializedUserDao extends JpaRepository<SerializedUser, Long> {}
