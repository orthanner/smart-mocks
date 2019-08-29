package ru.sbrf.simanov.smart.mock.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * created by simanov-an
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Response model")
public class SmartMockRsDto
{
    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Last update time")
    private Calendar updateTime;

    @ApiModelProperty(value = "Request name", required = true)
    private String requestName;

    @ApiModelProperty(value = "Regular expression", required = true)
    private String regExpression;

    @ApiModelProperty(value = "Response body", required = true)
    private String responseBody;

}
