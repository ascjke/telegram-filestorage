package ru.borisov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.entity.BinaryContent;

public interface BinaryContentDAO extends JpaRepository<BinaryContent, Long> {
}
