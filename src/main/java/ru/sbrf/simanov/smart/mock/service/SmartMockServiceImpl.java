package ru.sbrf.simanov.smart.mock.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;
import ru.sbrf.simanov.smart.mock.repository.SmartMockRepository;
import ru.sbrf.simanov.smart.mock.service.processor.SmartMockProcessor;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by simanov-an
 * Базовая реализация сервиса заглушек
 */
public class SmartMockServiceImpl implements SmartMockService
{
    private final SmartMockRepository smartMockRepository;
    private final List<SmartMockProcessor> processorList;

    public SmartMockServiceImpl(SmartMockRepository smartMockRepository, List<SmartMockProcessor> processorList)
    {
        this.smartMockRepository = smartMockRepository;
        this.processorList = processorList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SmartMock> findByRequestName(String requestName)
    {
        return smartMockRepository.findAllByRequestNameOrderByUpdateTimeDesc(requestName);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<SmartMock> findById(Long id)
    {
        return smartMockRepository.findById(id);
    }

    @Override
    public void deleteById(Long id)
    {
        smartMockRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SmartMock save(SmartMock smartMock)
    {
        checkEntity(smartMock);
        return smartMockRepository.saveAndFlush(smartMock);
    }

    @Override
    public SmartMock update(Long id, SmartMock smartMock)
    {
        Optional<SmartMock> existEntity = findById(id);
        if (!existEntity.isPresent())
            throw new IllegalArgumentException(String.format("Заглушка с ID=%s не найдена!", id));

        SmartMock existSmartMock = existEntity.get();
        existSmartMock.setUpdateTime(Calendar.getInstance());
        existSmartMock.setRegExpression(smartMock.getRegExpression());
        existSmartMock.setRequestName(smartMock.getRequestName());
        existSmartMock.setResponseBody(smartMock.getResponseBody());
        existSmartMock.setMockName(smartMock.getMockName());
        if (smartMock.getRollChance() != null)
            existSmartMock.setRollChance(smartMock.getRollChance());
        existSmartMock.setTimeoutChance(smartMock.getTimeoutChance());
        existSmartMock.setTimeout(smartMock.getTimeout());

        return save(existSmartMock);
    }

    @Override
    public SmartMock create(SmartMock smartMock)
    {
        smartMock.setUpdateTime(Calendar.getInstance());
        if (smartMock.getRollChance() == null)
            smartMock.setRollChance(100L);

        return save(smartMock);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody)
    {
        List<SmartMock> result = findByRequestName(requestName);
        if (CollectionUtils.isNotEmpty(result))
        {
            List<Pair<SmartMock, Double>> weights = result.stream()
                    .filter(smartMock -> smartMock.match(requestBody).isPresent())
                    .map(smartMock -> new Pair<>(smartMock, smartMock.getRollChance() / 100.d))
                    .collect(Collectors.toList());

            Optional<SmartMock> smartMockOptional = Optional.of(new EnumeratedDistribution<>(weights).sample());
            for (SmartMockProcessor processor : processorList)
            {
                smartMockOptional = processor.process(requestBody, smartMockOptional);
            }

            return smartMockOptional;
        }

        return Optional.empty();
    }

    private void checkEntity(SmartMock smartMock)
    {
        if (!smartMock.isValidRegExpression())
            throw new IllegalStateException(String.format("Не валидное регулярное выражение - %s", smartMock.getRegExpression()));
    }
}
