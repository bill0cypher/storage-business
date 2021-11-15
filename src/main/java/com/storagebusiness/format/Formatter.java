package com.storagebusiness.format;

import javax.servlet.ServletInputStream;
import java.io.Writer;
import java.util.List;

public interface Formatter<T> {
    void formatTo(List<T> devices, Writer writer);
    List<T> formatFrom(ServletInputStream inputStream, Class<T> deviceDTOClass);
}
