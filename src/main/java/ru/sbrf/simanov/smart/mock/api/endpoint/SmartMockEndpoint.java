package ru.sbrf.simanov.smart.mock.api.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.simanov.smart.mock.api.dto.GenerateMockRqDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockRsDto;
import ru.sbrf.simanov.smart.mock.api.dto.SmartMockSaveDto;
import ru.sbrf.simanov.smart.mock.api.processor.SmartMockRqProcessor;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;

/**
 * created by simanov-an
 */
@RestController
@RequestMapping(value = "/v1/smart-mock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Smart mock service information")
public class SmartMockEndpoint
{
    private final SmartMockRqProcessor smartMockRqProcessor;

    public SmartMockEndpoint(SmartMockRqProcessor smartMockRqProcessor)
    {
        this.smartMockRqProcessor = smartMockRqProcessor;
    }

    @GetMapping
    @ApiOperation(value = "View list")
    public ResponseEntity<List<SmartMockRsDto>> getList(@RequestParam String requestName)
    {
        return ResponseEntity.of(smartMockRqProcessor.getList(requestName));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View detail")
    public ResponseEntity<SmartMockRsDto> getById(@PathVariable("id") Long id)
    {
        return ResponseEntity.of(smartMockRqProcessor.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update")
    public ResponseEntity<SmartMockRsDto> update(@PathVariable("id") Long id, @RequestBody SmartMockSaveDto smartMockSaveDto)
    {
        return ResponseEntity.ok(smartMockRqProcessor.update(id, smartMockSaveDto));
    }

    @PostMapping
    @ApiOperation(value = "Create")
    public ResponseEntity<SmartMockRsDto> create(@RequestBody SmartMockSaveDto smartMockSaveDto)
    {
        return ResponseEntity.ok(smartMockRqProcessor.create(smartMockSaveDto));
    }

    @PostMapping("/generate")
    @ApiOperation(value = "Generate smart mock response")
    public ResponseEntity<SmartMockRsDto> generateResponse(@RequestBody GenerateMockRqDto generateMockRqDto)
    {
        return ResponseEntity.of(smartMockRqProcessor.generateResponse(generateMockRqDto));
    }

}
