package ru.sbrf.simanov.smart.mock.api.processor;


import ru.sbrf.simanov.smart.mock.api.dto.GenerateMockRqDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public interface SmartMockRqProcessor
{
    Optional<List<SmartMockRsDto>> getList(String requestName);

    Optional<SmartMockRsDto> getById(Long id);

    SmartMockRsDto update(Long id, SmartMockSaveDto smartMockSaveDto);

    SmartMockRsDto create(SmartMockSaveDto smartMockSaveDto);

    Optional<SmartMockRsDto> generateResponse(GenerateMockRqDto generateMockRqDto);

}
