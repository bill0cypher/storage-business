package com.storagebusiness.format;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.storagebusiness.dto.DeviceDTO;
import lombok.SneakyThrows;

import java.io.Writer;
import java.util.List;

public class CSVFormatter<T> implements Formatter<T> {
    @Override
    @SneakyThrows
    public void formatTo(List<T> devices, Writer writer) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(DeviceDTO.class).withHeader();
        mapper.writerFor(new TypeReference<T>() {}).with(schema)
                .writeValues(writer)
                .writeAll(devices.toArray(new Object[0]));
    }
}
