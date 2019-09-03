package ru.sbrf.simanov.smart.mock.service.processor;

import org.apache.commons.lang3.RandomUtils;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.Optional;

/**
 * created by simanov-an
 */
public class TimeoutSmartMockProcessor implements SmartMockProcessor
{
    @Override
    public Optional<SmartMock> process(String requestBody, Optional<SmartMock> smartMockOptional)
    {
        if (smartMockOptional.isPresent())
        {
            SmartMock smartMock = smartMockOptional.get();
            if (isTimeoutSimulationEnabled(smartMock))
            {
                try
                {
                    Thread.sleep(smartMock.getTimeout() * 1000L);
                }
                catch (InterruptedException e)
                {
                    // ignore
                }
            }
        }

        return smartMockOptional;
    }

    private boolean isTimeoutSimulationEnabled(SmartMock smartMock)
    {
        if (smartMock.isTimeoutSimulationEnabled())
        {
            long timeoutChance = RandomUtils.nextLong(0, 100);
            return timeoutChance <= smartMock.getTimeoutChance();
        }

        return false;
    }
}
