package project.weather_stock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.weather_stock_api.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUserName(String userName);
}
