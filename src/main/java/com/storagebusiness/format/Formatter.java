package com.storagebusiness.format;

import com.storagebusiness.dto.DeviceDTO;

import java.io.Writer;
import java.util.List;

public interface Formatter {
    void format(List<DeviceDTO> devices, Writer writer);
}
