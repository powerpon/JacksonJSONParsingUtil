package org.example.json.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/***
 * JsonParserUtil class consists of methods that work with JSON objects. Mainly conversions from or to JSON objects.
 */
public class JsonParserUtil {

    private static ObjectMapper objectMapper = getObjectMapper();

    /***
     * Generates and configures object of type ObjectMapper.
     * @return ObjectMapper object
     */
    private static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapperInnit = new ObjectMapper();
        objectMapperInnit.registerModule(new JavaTimeModule());
        objectMapperInnit.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapperInnit;
    }

    /***
     * Converts JSON String object to JsonNode object.
     * @param jsonSrc - JSON String object to be converted
     * @return JsonNode object
     * @throws JsonProcessingException
     */
    public static JsonNode parseJsonString(String jsonSrc) throws JsonProcessingException {
        return objectMapper.readTree(jsonSrc);
    }

    /***
     * Converts JsonNode object into DTO of passed Class.
     * @param jsonNode - JsonNode object to be converted
     * @param convertedClass - Class of DTO to be produced
     * @return DTO
     * @param <T> - Class of DTO to be returned
     * @throws JsonProcessingException
     */
    public static <T> T jsonToDto(JsonNode jsonNode, Class<T> convertedClass) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, convertedClass);
    }

    /***
     * Converts DTO into JsonNode object.
     * @param obj - DTO to be converted
     * @return JsonNode object
     */
    public static JsonNode dtoToJson(Object obj){
        return objectMapper.valueToTree(obj);
    }

    /***
     * Converts JsonNode object into JSON String object, and "prettifies" the String object to resemble JSON object.
     * @param jsonNode - JsonNode object to be converted
     * @return JSON String object
     * @throws JsonProcessingException
     */
    public static String jsonNodeToString(JsonNode jsonNode) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(jsonNode);
    }

}
