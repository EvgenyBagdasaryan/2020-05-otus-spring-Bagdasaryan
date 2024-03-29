package ru.otus.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
