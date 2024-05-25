package com.loto.backend.response;

import com.loto.backend.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomResponse {
    private String message;
    private Room room;
    private int roomFoundStatus;
}
