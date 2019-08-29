package ru.sbrf.simanov.smart.mock.service;

import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public interface SmartMockService
{
    Optional<List<SmartMock>> findByRequestName(String requestName);

    Optional<SmartMock> findById(Long id);

    void deleteById(Long id);

    SmartMock save(SmartMock smartMock);

    SmartMock update(Long id, SmartMock smartMock);

    SmartMock create(SmartMock smartMock);

    Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody);

}
