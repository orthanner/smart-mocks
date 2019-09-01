package ru.sbrf.simanov.smart.mock.api.converter;

import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

/**
 * created by simanov-an
 * Утилитный класс для конвертации DTO
 */
public class SmartMockConverter
{
    /**
     * Конвертировать в DTO
     * @param smartMock объект логики приложения
     * @return dto
     */
    public static SmartMockRsDto smartMockRsDto(SmartMock smartMock)
    {
        return new SmartMockRsDto(
                smartMock.getId(),
                smartMock.getUpdateTime(),
                smartMock.getMockName(),
                smartMock.getRequestName(),
                smartMock.getRegExpression(),
                smartMock.getRollChance(),
                smartMock.getResponseBody()
        );
    }

    /**
     * Конвертировать в объект логики приложения
     * @param smartMockSaveDto dto create/update
     * @return объект логики приложения
     */
    public static SmartMock smartMock(SmartMockSaveDto smartMockSaveDto)
    {
        return new SmartMock(
                null,
                null,
                smartMockSaveDto.getMockName(),
                smartMockSaveDto.getRequestName(),
                smartMockSaveDto.getRegExpression(),
                smartMockSaveDto.getRollChance(),
                smartMockSaveDto.getResponseBody());
    }
}
