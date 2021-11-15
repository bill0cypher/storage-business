package com.storagebusiness.format;

import java.io.Writer;
import java.util.List;

public interface Formatter<T> {
    void formatTo(List<T> devices, Writer writer);
}
