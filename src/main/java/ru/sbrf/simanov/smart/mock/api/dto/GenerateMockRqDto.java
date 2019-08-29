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
@ApiModel(value = "Request model for generate mock response")
public class GenerateMockRqDto
{
    @ApiModelProperty(value = "Name of request", required = true)
    private String requestName;

    @ApiModelProperty(value = "Request body", required = true)
    private String requestBody;
}
