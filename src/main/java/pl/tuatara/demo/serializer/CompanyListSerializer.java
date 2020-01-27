package pl.tuatara.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.tuatara.demo.model.entity.Company;

import java.io.IOException;
import java.util.List;

public class CompanyListSerializer extends StdSerializer<List<Company>> {

    public CompanyListSerializer() {
        this(null);
    }

    public CompanyListSerializer(Class<List<Company>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Company> companies, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Company company : companies) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", company.getName());
            jsonGenerator.writeStringField("street", company.getStreet());
            jsonGenerator.writeStringField("city", company.getCity());
            jsonGenerator.writeStringField("country", company.getCountry());
            jsonGenerator.writeNumberField("latitude", company.getLatitude());
            jsonGenerator.writeNumberField("longitude", company.getLongitude());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

}