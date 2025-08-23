package com.rest.marshall;

import com.rest.dto.Car;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;

public class HashMapWriter implements MessageBodyWriter<HashMap<Integer, Car>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return genericType.equals(HashMap.class);
    }

    @Override
    public void writeTo(HashMap<Integer, Car> integerCarHashMap, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {

        try {
            JAXBContext context = JAXBContext.newInstance(CarDBWrapper.class);

            Marshaller marshaller = context.createMarshaller(); // Pars data (in this case the HashMap) into XML

            CarDBWrapper wrapper = new CarDBWrapper();
            wrapper.setCars(integerCarHashMap);

            marshaller.marshal(wrapper,entityStream);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
