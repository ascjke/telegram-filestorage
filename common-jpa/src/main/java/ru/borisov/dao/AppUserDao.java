package ru.borisov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.entity.AppUser;

public interface AppUserDao extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByTelegramUserId(Long id);
}
