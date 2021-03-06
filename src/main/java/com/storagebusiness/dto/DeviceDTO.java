package com.storagebusiness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceDTO {

    private String serialNumber;
    private String model;
    private String description;
}
