package json.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.json.parser.dto.AuthorDTO;
import org.example.json.parser.dto.BookDTO;
import org.example.json.parser.dto.TestDTO;
import org.example.json.parser.JsonParserUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserUtilTest {

    private final String jsonString = "{ \"name\": \"Testing JSON\", \"date\": \"2019-02-12\" }";

    private final String jsonAuthorBookString = "{\n" +
                                                    " \"name\": \"Stefan\",\n" +
                                                    " \"books\": [\n" +
                                                        " {\n" +
                                                            " \"title\": \"random titile\",\n" +
                                                            " \"inPrint\": true,\n" +
                                                            " \"publishDate\": \"2019-01-01\"\n" +
                                                        " },\n" +
                                                        " {\n" +
                                                            " \"title\": \"not a random titile\",\n" +
                                                            " \"inPrint\": false,\n" +
                                                            " \"publishDate\": \"2019-12-22\"\n" +
                                                        " }\n" +
                                                    " ]\n" +
                                                "}";
    @Test
    void parseJsonString() throws JsonProcessingException {
        JsonNode jsonNode = JsonParserUtil.parseJsonString(jsonString);
        assertEquals(jsonNode.get("name").asText(), "Testing JSON");
        assertEquals(jsonNode.get("date").asText(), "2019-02-12");
    }

    @Test
    void jsonToDto() throws JsonProcessingException {
        JsonNode jsonNode = JsonParserUtil.parseJsonString(jsonString);
        TestDTO testDTO = JsonParserUtil.jsonToDto(jsonNode, TestDTO.class);
        System.out.println("Name: " + testDTO.getName());
        System.out.println("Date: " + testDTO.getDate());
    }

    @Test
    void dtoToJson() {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("New Name");
        testDTO.setDate(LocalDate.parse("2000-10-13"));
        JsonNode jsonNode = JsonParserUtil.dtoToJson(testDTO);
        StringBuilder stringDate = new StringBuilder();
        for(JsonNode node : jsonNode.get("date")) {
            stringDate.append(node.asText());
            stringDate.append("-");
        }
        stringDate.deleteCharAt(stringDate.length() - 1);
        LocalDate date = LocalDate.parse(stringDate.toString());
        System.out.println(date);
    }

    @Test
    void jsonNodeToString() throws JsonProcessingException {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("New Name");
        testDTO.setDate(LocalDate.parse("2000-10-13"));
        JsonNode jsonNode = JsonParserUtil.dtoToJson(testDTO);
        String newJsonString = JsonParserUtil.jsonNodeToString(jsonNode);
        System.out.println(newJsonString);
    }

    @Test
    void authorBookScenario() throws JsonProcessingException {
        JsonNode jsonNode = JsonParserUtil.parseJsonString(jsonAuthorBookString);
        AuthorDTO authorDTO = JsonParserUtil.jsonToDto(jsonNode, AuthorDTO.class);
        JsonNode jsonNode2 = JsonParserUtil.dtoToJson(authorDTO);
        String newJsonString = JsonParserUtil.jsonNodeToString(jsonNode2);
        System.out.println(newJsonString);
    }

}