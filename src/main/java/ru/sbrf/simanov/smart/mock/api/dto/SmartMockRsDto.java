package ru.sbrf.simanov.smart.mock.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

/**
 * created by simanov-an
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Response model")
public class SmartMockRsDto
{
    @ApiModelProperty(value = "Id", required = true)
    private Long id;

    @ApiModelProperty(value = "Last update time", required = true)
    private Calendar updateTime;

    @ApiModelProperty(value = "Mock name", required = true)
    private String mockName;

    @ApiModelProperty(value = "Request name", required = true)
    private String requestName;

    @ApiModelProperty(value = "Regular expression", required = true)
    private String regExpression;

    @ApiModelProperty(value = "Chance choose this mock from 1% to 100%", required = true)
    private Long rollChance;

    @ApiModelProperty(value = "Response body", required = true)
    private String responseBody;

}
