package ru.sbrf.simanov.smart.mock.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;
import ru.sbrf.simanov.smart.mock.repository.SmartMockRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 * Базовая реализация сервиса заглушек
 */
public class SmartMockServiceImpl implements SmartMockService
{
    private final SmartMockRepository smartMockRepository;

    public SmartMockServiceImpl(SmartMockRepository smartMockRepository)
    {
        this.smartMockRepository = smartMockRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<List<SmartMock>> findByRequestName(String requestName)
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

        return save(existSmartMock);
    }

    @Override
    public SmartMock create(SmartMock smartMock)
    {
        smartMock.setUpdateTime(Calendar.getInstance());

        return save(smartMock);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody)
    {
        return findByRequestName(requestName)
                .flatMap(smartMocks -> smartMocks.stream()
                    .filter(smartMock -> smartMock.match(requestBody).isPresent())
                    .findFirst());
    }

    private void checkEntity(SmartMock smartMock)
    {
        if (!smartMock.isValidRegExpression())
            throw new IllegalStateException(String.format("Не валидное регулярное выражение - %s", smartMock.getRegExpression()));
    }
}
