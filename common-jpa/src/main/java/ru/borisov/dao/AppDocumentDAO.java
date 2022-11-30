package ru.borisov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.entity.AppDocument;

public interface AppDocumentDAO extends JpaRepository<AppDocument, Long> {
}
