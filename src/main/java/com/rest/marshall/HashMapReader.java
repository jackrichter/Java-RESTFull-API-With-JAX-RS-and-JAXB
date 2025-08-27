package com.rest.marshall;

import com.rest.dto.Car;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;

public class HashMapReader implements MessageBodyReader<HashMap<Integer, Car>> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return genericType.equals(HashMap.class);
    }

    @Override
    public HashMap<Integer, Car> readFrom(Class<HashMap<Integer, Car>> type, Type genericType, Annotation[] annotations,
                                          MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
                                          InputStream entityStream) throws IOException, WebApplicationException {
        try {
            JAXBContext context = JAXBContext.newInstance(CarDBWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (HashMap<Integer, Car>) unmarshaller.unmarshal(entityStream);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
