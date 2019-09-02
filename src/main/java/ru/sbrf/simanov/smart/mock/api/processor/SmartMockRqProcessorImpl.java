package ru.sbrf.simanov.smart.mock.api.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(SmartMockRqProcessorImpl.class);

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
        Optional<SmartMockRsDto> result = smartMockService.findByRequestNameAndBody(
                generateMockRqDto.getRequestName(), generateMockRqDto.getRequestBody())
                .map(SmartMockConverter::smartMockRsDto);

        if (result.isPresent())
        {
            log.info(String.format("По запросу %s успешно сгенерирована заглушка %s",
                    generateMockRqDto.getRequestName(), result.get().getMockName()));
        }
        else
        {
            log.info(String.format("По запросу %s не удалось сгенерировать заглушку, тело запроса\n%s",
                    generateMockRqDto.getRequestName(), generateMockRqDto.getRequestBody()));
        }

        return result;
    }
}
