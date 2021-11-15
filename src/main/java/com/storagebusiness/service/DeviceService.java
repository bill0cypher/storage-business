package com.storagebusiness.service;

import com.storagebusiness.dto.DeviceDTO;
import com.storagebusiness.format.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final WebClient webClient;
    private final ReportService reportService;

    public Mono<DeviceDTO> findById(String serialNumber) {
        return webClient.get().uri(String.format("/devices/by/%s", serialNumber)).exchangeToMono(response -> response.bodyToMono(DeviceDTO.class));
    }

    public Flux<List<DeviceDTO>> findByModel(String model) {
        return webClient.get().uri(String.format("/devices/all/by/%s", model)).exchangeToFlux(clientResponse ->
                clientResponse.bodyToMono(DeviceDTO[].class).flatMapMany(Flux::fromArray).collectList().flux());
    }

    public void exportAs(String model, Writer writer, Formatter<DeviceDTO> formatter) {
        findByModel(model).subscribe(devices -> formatter.formatTo(devices, writer));
    }
}
