package ru.sbrf.simanov.smart.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * created by simanov-an
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SMART_MOCKS")
public class SmartMock implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_ID")
    @SequenceGenerator(name = "SEQ_SM_ID", sequenceName = "SEQ_SM_ID", allocationSize = 10)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "UPDATE_TIME", nullable = false)
    private Calendar updateTime;

    @Column(name = "MOCK_NAME", nullable = false)
    private String mockName;

    @Column(name = "REQUEST_NAME", nullable = false)
    private String requestName;

    @Column(name = "REG_EXPRESSION", nullable = false)
    private String regExpression;

    @Column(name = "ROLL_CHANCE", nullable = false)
    private Long rollChance;

    @Column(name = "TIMEOUT_CHANCE")
    private Long timeoutChance;

    @Column(name = "TIMEOUT")
    private Long timeout;

    @Column(name = "RESPONSE_BODY", nullable = false)
    private String responseBody;

    public boolean isValidRegExpression()
    {
        try
        {
            getRegExpPattern();
            return true;
        }
        catch (Exception e)
        {
            // ignore
        }

        return false;
    }

    public Pattern getRegExpPattern()
    {
        return Pattern.compile(getRegExpression());
    }

    public Optional<String> match(String request)
    {
        if (getRegExpPattern().matcher(request).find())
        {
            return Optional.of(getResponseBody());
        }

        return Optional.empty();
    }

    public boolean isTimeoutSimulationEnabled()
    {
        return getTimeoutChance() != null && getTimeout() != null;
    }

}
