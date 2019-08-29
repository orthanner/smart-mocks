package ru.sbrf.simanov.smart.mock.api.converter;

import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

/**
 * created by simanov-an
 */
public class SmartMockConverter
{
    public static SmartMockRsDto smartMockRsDto(SmartMock smartMock)
    {
        return new SmartMockRsDto(
                smartMock.getId(),
                smartMock.getUpdateTime(),
                smartMock.getRequestName(),
                smartMock.getRegExpression(),
                smartMock.getResponseBody()
        );
    }

    public static SmartMock smartMock(SmartMockSaveDto smartMockSaveDto)
    {
        return new SmartMock(
                null,
                null,
                smartMockSaveDto.getRequestName(),
                smartMockSaveDto.getRegExpression(),
                smartMockSaveDto.getResponseBody());
    }
}
