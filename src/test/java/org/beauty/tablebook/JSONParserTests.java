package org.beauty.tablebook;

import org.beauty.tablebook.utils.JSONParserToSaveTables;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONParserTests {


    @Test
    void testJsonParser(){
        try {
            String json = Files.readString(Path.of("test.json"));
            JSONParserToSaveTables.parse(json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
