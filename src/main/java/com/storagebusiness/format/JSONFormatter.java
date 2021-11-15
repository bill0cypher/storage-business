package com.storagebusiness.format;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.ServletInputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONFormatter<T> implements Formatter<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public void formatTo(List<T> devices, Writer writer) {
        objectMapper.writeValue(writer, devices.toArray(new Object[0]));
    }

    @Override
    @SneakyThrows
    public List<T> formatFrom(ServletInputStream inputStream, Class<T> deviceDTOClass) {
        List<T> res = new ArrayList<>();
        objectMapper.readValues(objectMapper.createParser(inputStream.readAllBytes()), deviceDTOClass).forEachRemaining(res::add);
        return res;
    }
}
