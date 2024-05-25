package com.loto.backend.controller;

import com.loto.backend.entity.Room;
import com.loto.backend.response.JoinRoomResponse;
import com.loto.backend.service.RoomServiceImpl;
import com.loto.backend.service.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @PostMapping("create/{userID}")
    public Room createRoom(@PathVariable int userID) {
        return roomService.createRoom(userID);
    }

    @PostMapping("{roomID}/count")
    public int getRoomUserCount(@PathVariable String roomID) {
        return roomService.getRoomUserCount(roomID);
    }

    @PostMapping("join")
    public JoinRoomResponse joinRoom(@RequestParam int userID, @RequestParam String roomID) {
        return roomService.joinRoom(userID, roomID);
    }

}
