package ru.sbrf.simanov.smart.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;

/**
 * created by simanov-an
 * Репозиторий для запросов к хранилищу заглушек
 */
@Repository
public interface SmartMockRepository extends JpaRepository<SmartMock, Long>
{
    /**
     * Получить список заглушек
     * @param requestName название запроса заглушки
     * @return список заглушек
     */
    List<SmartMock> findAllByRequestNameOrderByUpdateTimeDesc(String requestName);
}
