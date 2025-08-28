package com.rest.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeXMLAdapter extends XmlAdapter<String, LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return v !=null && !v.isEmpty() ? LocalDateTime.parse(v, formatter) : null;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v !=null ? v.format(formatter) : null;
    }
}
