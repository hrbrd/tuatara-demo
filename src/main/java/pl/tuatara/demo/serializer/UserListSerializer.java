package pl.tuatara.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.tuatara.demo.model.entity.User;

import java.io.IOException;
import java.util.List;

public class UserListSerializer extends StdSerializer<List<User>> {

    public UserListSerializer() {
        this(null);
    }

    public UserListSerializer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (User user : users) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("username", user.getUsername());
            jsonGenerator.writeStringField("firstName", user.getFirstName());
            jsonGenerator.writeStringField("lastName", user.getLastName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

}