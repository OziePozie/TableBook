package org.beauty.tablebook.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParserToSaveTables {

    public static List<Integer> parse(String json) {
        String jsonString = json;
        List<Integer> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode groups = rootNode.path("objects");

        for (JsonNode group : groups) {
            if (group.has("type") && "Group".equals(group.get("type").asText())) {
                JsonNode textNode = group.path("objects").get(1);
                if (textNode != null && textNode.has("type") && "IText".equals(textNode.get("type").asText())) {
                    String textValue = textNode.get("text").asText();
                    list.add(Integer.parseInt(textValue));

//                    System.out.println("Text value: " + textValue);
                }
            }
        }
        return list;
    }

}
