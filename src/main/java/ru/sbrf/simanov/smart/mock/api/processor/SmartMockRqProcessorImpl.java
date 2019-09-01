package ru.sbrf.simanov.smart.mock.api.processor;

import ru.sbrf.simanov.smart.mock.api.converter.SmartMockConverter;
import ru.sbrf.simanov.smart.mock.api.dto.GenerateMockRqDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;
import ru.sbrf.simanov.smart.mock.service.SmartMockService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by simanov-an
 */
public class SmartMockRqProcessorImpl implements SmartMockRqProcessor
{
    private final SmartMockService smartMockService;

    public SmartMockRqProcessorImpl(SmartMockService smartMockService)
    {
        this.smartMockService = smartMockService;
    }

    @Override
    public List<SmartMockRsDto> getList(String requestName)
    {
        return smartMockService.findByRequestName(requestName)
                .stream()
                .map(SmartMockConverter::smartMockRsDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SmartMockRsDto> getById(Long id)
    {
        return smartMockService.findById(id).map(SmartMockConverter::smartMockRsDto);
    }

    @Override
    public void deleteById(Long id)
    {
        smartMockService.deleteById(id);
    }

    @Override
    public SmartMockRsDto update(Long id, SmartMockSaveDto smartMockSaveDto)
    {
        return SmartMockConverter.smartMockRsDto(
                smartMockService.update(id, SmartMockConverter.smartMock(smartMockSaveDto)));
    }

    @Override
    public SmartMockRsDto create(SmartMockSaveDto smartMockSaveDto)
    {
        return SmartMockConverter.smartMockRsDto(
                smartMockService.create(SmartMockConverter.smartMock(smartMockSaveDto)));
    }

    @Override
    public Optional<SmartMockRsDto> generateResponse(GenerateMockRqDto generateMockRqDto)
    {
        return smartMockService.findByRequestNameAndBody(generateMockRqDto.getRequestName(), generateMockRqDto.getRequestBody())
                .map(SmartMockConverter::smartMockRsDto);
    }
}
