package ru.borisov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.entity.AppPhoto;

public interface AppPhotoDAO extends JpaRepository<AppPhoto, Long> {
}
