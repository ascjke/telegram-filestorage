package ru.borisov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borisov.entity.RawData;

public interface RawDataDAO extends JpaRepository<RawData, Long> {

}
