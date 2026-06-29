package com.fantime.fantime_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fantime.fantime_backend.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // user테이블에서 email값이 일치하는 것 찾기
}
