package com.storagebusiness.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.Writer;
import java.util.List;

public class JSONFormatter<T> implements Formatter<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public void formatTo(List<T> devices, Writer writer) {
        objectMapper.writeValue(writer, devices.toArray(new Object[0]));
    }
}
