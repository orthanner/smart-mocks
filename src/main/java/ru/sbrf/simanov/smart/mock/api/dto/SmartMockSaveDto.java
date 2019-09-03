package ru.sbrf.simanov.smart.mock.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * created by simanov-an
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Request model for create/update entity")
public class SmartMockSaveDto
{
    @ApiModelProperty(value = "Mock name", required = true)
    private String mockName;

    @ApiModelProperty(value = "Request name", required = true)
    private String requestName;

    @ApiModelProperty(value = "Regular expression", required = true)
    private String regExpression;

    @ApiModelProperty(value = "Chance choose this mock from 1% to 100%, default 100%")
    private Long rollChance;

    @ApiModelProperty(value = "Chance to wait response from 1% to 100%")
    private Long timeoutChance;

    @ApiModelProperty(value = "Timeout wait in seconds")
    private Long timeout;

    @ApiModelProperty(value = "Response body", required = true)
    private String responseBody;

}
