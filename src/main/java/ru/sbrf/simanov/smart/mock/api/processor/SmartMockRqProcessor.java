package ru.sbrf.simanov.smart.mock.api.processor;


import ru.sbrf.simanov.smart.mock.api.dto.GenerateMockRqDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 * Интерфейс для обработки запросов
 */
public interface SmartMockRqProcessor
{
    /**
     * Получить список заглушек по названию запроса
     * @param requestName название запроса
     * @return список заглушек по названию запроса
     */
    List<SmartMockRsDto> getList(String requestName);

    /**
     * Получить заглушку по ID
     * @param id идентификатор
     * @return заглушка
     */
    Optional<SmartMockRsDto> getById(Long id);

    /**
     * Удалить заглушку
     * @param id идентификатор
     */
    void deleteById(Long id);

    /**
     * Обновить заглушку
     * @param id идентификатор
     * @param smartMockSaveDto dto
     * @return dto ответ
     */
    SmartMockRsDto update(Long id, SmartMockSaveDto smartMockSaveDto);

    /**
     * Создать заглушку
     * @param smartMockSaveDto dto
     * @return dto ответ
     */
    SmartMockRsDto create(SmartMockSaveDto smartMockSaveDto);

    /**
     * Сгенерировать заглушку по запросу
     * @param generateMockRqDto dto
     * @return dto ответ
     */
    Optional<SmartMockRsDto> generateResponse(GenerateMockRqDto generateMockRqDto);

}
