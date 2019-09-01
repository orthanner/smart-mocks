package ru.sbrf.simanov.smart.mock.service;

import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public interface SmartMockService
{
    /**
     * Получить список заглушек по названию запроса
     * @param requestName название запроса
     * @return список заглушек по названию запроса
     */
    List<SmartMock> findByRequestName(String requestName);

    /**
     * Получить заглушку по ID
     * @param id идентификатор
     * @return заглушка
     */
    Optional<SmartMock> findById(Long id);

    /**
     * Удалить заглушку
     * @param id идентификатор
     */
    void deleteById(Long id);

    /**
     * Сохранить заглушку
     * @param smartMock заглушка
     * @return заглушка
     */
    SmartMock save(SmartMock smartMock);

    /**
     * Обновить заглушку
     * @param id идентификатор
     * @param smartMock заглушка
     * @return заглушка
     */
    SmartMock update(Long id, SmartMock smartMock);

    /**
     * Создать заглушку
     * @param smartMock заглушка
     * @return заглушка
     */
    SmartMock create(SmartMock smartMock);

    /**
     * Получить заглушку
     * @param requestName название запроса заглушки
     * @param requestBody тело запроса для анализа
     * @return заглушка
     */
    Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody);

}
