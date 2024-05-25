package com.loto.backend.service.interfaces;

import com.loto.backend.entity.Room;
import com.loto.backend.response.JoinRoomResponse;

public interface IRoomService {
    Room createRoom(int userID);
    int getRoomUserCount(String roomID);
    JoinRoomResponse joinRoom(int userID, String roomID);
}
