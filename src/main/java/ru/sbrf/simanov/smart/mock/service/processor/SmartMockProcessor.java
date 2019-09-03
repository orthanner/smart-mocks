package ru.sbrf.simanov.smart.mock.service.processor;

import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.Optional;

/**
 * created by simanov-an
 * Интерфейс для дополнительной обработки заглушки
 */
public interface SmartMockProcessor
{
    Optional<SmartMock> process(String requestBody, Optional<SmartMock> smartMock);
}
