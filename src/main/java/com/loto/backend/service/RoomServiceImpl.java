package com.loto.backend.service;

import com.loto.backend.entity.Room;
import com.loto.backend.entity.RoomUser;
import com.loto.backend.entity.User;
import com.loto.backend.repository.IRoomRepository;
import com.loto.backend.repository.IRoomUserRepository;
import com.loto.backend.repository.IUserRepository;
import com.loto.backend.response.JoinRoomResponse;
import com.loto.backend.service.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomRepository roomRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IRoomUserRepository roomUserRepo;

    private static final int ID_LENGTH = 4;

    @Override
    public Room createRoom(int userID) {
        Room room = new Room();
        String roomID;

        do{
            roomID = generateRandomID();
        } while (roomRepo.findByRoomID(roomID) != null);

        room.setRoomID(roomID);
        room.setCountUser(1);

        Room savedRoom = roomRepo.save(room);

//        Add user into room
        try {
            User user = userRepo.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));

            RoomUser roomUser = new RoomUser();
            roomUser.setUser(user);
            roomUser.setRoom(savedRoom);
            savedRoom.getRoomUsers().add(roomUser);
            roomRepo.save(savedRoom);
        } catch (Exception e){
            e.printStackTrace();
        }

        return savedRoom;
    }

    public String generateRandomID() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        return uuid.substring(0, ID_LENGTH);
    }

    public int getRoomUserCount(String roomID) {
        Room room = roomRepo.findByRoomID(roomID);
//        RoomUser roomUser =
        if(room != null) {
            return room.getCountUser();
        }
        return 0;
    }

    public JoinRoomResponse joinRoom(int userID, String roomID) {
        JoinRoomResponse response = new JoinRoomResponse();
        Room room = roomRepo.findByRoomID(roomID);
        if(room == null) {
            response.setMessage("Room not found");
            response.setRoomFoundStatus(0);
            return response;
        }

        User user = userRepo.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));

        //Check if user already join room
        boolean userAlreadyJoined = roomUserRepo.existsByRoomIdAndUserId(room.getId(), userID);

        if(!userAlreadyJoined) {
            RoomUser roomUser = new RoomUser();
            roomUser.setUser(user);
            roomUser.setRoom(room);
            room.getRoomUsers().add(roomUser);

            response.setRoom(room);
            response.setRoomFoundStatus(1);
            room.setCountUser(room.getCountUser() + 1);
            roomRepo.save(room);
        }
        else {response.setMessage("You are already join this room");}
        return response;
    }
}
