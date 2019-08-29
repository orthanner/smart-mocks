package ru.sbrf.simanov.smart.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;

/**
 * created by simanov-an
 */
@Repository
public interface SmartMockRepository extends JpaRepository<SmartMock, Long>
{
    List<SmartMock> findByRequestNameOrderByUpdateTimeDesc(String requestName);
}
