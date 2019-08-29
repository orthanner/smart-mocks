package ru.sbrf.simanov.smart.mock.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.Calendar;

/**
 * created by simanov-an
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Request model for create/update entity")
public class SmartMockSaveDto
{
    @ApiModelProperty(value = "Request name", required = true)
    private String requestName;

    @ApiModelProperty(value = "Regular expression", required = true)
    private String regExpression;

    @ApiModelProperty(value = "Response body", required = true)
    private String responseBody;

}
