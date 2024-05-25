package com.loto.backend.repository;

import com.loto.backend.entity.RoomUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IRoomUserRepository {
//    boolean existsByRoomIDAndUserID(String roomID, Integer userID);
    @PersistenceContext
    private EntityManager entityManager;

    public boolean existsByRoomIdAndUserId(int roomId, int userId) {
        String sql = "SELECT COUNT(ru) FROM RoomUser ru WHERE ru.room.id = :roomId AND ru.user.id = :userId";
        Query query = entityManager.createQuery(sql);
        query.setParameter("roomId", roomId);
        query.setParameter("userId", userId);

        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
}
