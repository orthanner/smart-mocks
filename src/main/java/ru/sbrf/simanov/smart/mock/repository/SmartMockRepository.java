package ru.sbrf.simanov.smart.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
@Repository
public interface SmartMockRepository extends JpaRepository<SmartMock, Long>
{
    Optional<List<SmartMock>> findAllByRequestNameOrderByUpdateTimeDesc(String requestName);
}
