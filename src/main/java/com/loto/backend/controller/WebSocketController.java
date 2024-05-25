package com.loto.backend.controller;

import com.loto.backend.entity.Room;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/joinRoom")
    @SendTo("Topic/room")
    public Room JoinRoomNotification(Room room) {
        return room;
    }
}
