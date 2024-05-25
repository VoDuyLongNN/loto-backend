package com.loto.backend.repository;

import com.loto.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomID(String roomID);
}
