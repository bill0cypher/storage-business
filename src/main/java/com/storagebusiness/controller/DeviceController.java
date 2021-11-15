package com.storagebusiness.controller;

import com.storagebusiness.dto.DeviceDTO;
import com.storagebusiness.format.CSVFormatter;
import com.storagebusiness.format.JSONFormatter;
import com.storagebusiness.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/devices")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping(path = "/by/{id}")
    public ResponseEntity<Mono<DeviceDTO>> findDeviceByModel(@PathVariable("id") String serialNumber) {
        log.info("REST request to find device by model: {}", serialNumber);
        return ResponseEntity.ok(deviceService.findById(serialNumber));
    }

    @GetMapping(path = "/by/{model}/csv")
    @SneakyThrows
    public void exportDevicesCSV(HttpServletResponse response, @PathVariable("model") String model) {
        log.info("REST request to get devices {} in CSV format", model);
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=devices.csv");
        deviceService.exportAs(model, response.getWriter(), new CSVFormatter<>());
    }

    @GetMapping(path = "/by/{model}/json")
    @SneakyThrows
    public void exportDevicesJSON(HttpServletResponse response, @PathVariable("model") String model) {
        log.info("REST request to get devices {} in JSON format.", model);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.addHeader("Content-Disposition", "attachment; filename=devices.json");
        deviceService.exportAs(model, response.getWriter(), new JSONFormatter<>());
    }

    @PostMapping(path = "/by/{model}/csv", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @SneakyThrows
    public void importDevicesCSV(HttpServletRequest request) {
        log.info("REST request to extract devices from CSV.");
        deviceService.importAs(request.getInputStream(), new CSVFormatter<>());
    }
}
