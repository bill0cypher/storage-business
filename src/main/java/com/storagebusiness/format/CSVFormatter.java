package com.storagebusiness.format;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.storagebusiness.dto.DeviceDTO;
import lombok.SneakyThrows;

import java.io.Writer;
import java.util.List;

public class CSVFormatter implements Formatter {
    @Override
    @SneakyThrows
    public void format(List<DeviceDTO> devices, Writer writer) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(DeviceDTO.class).withHeader();
        mapper.writerFor(DeviceDTO[].class).with(schema).writeValue(writer, devices);
    }
}
