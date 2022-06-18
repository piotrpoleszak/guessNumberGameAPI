package com.poleszak.GuessGame.acceptance.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AcceptanceTestBase {

    @Autowired
    protected MockMvc mvc;

    protected final String PATH = "http://localhost:8080/api";

    protected String readJsonFromFile(String path, String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path + fileName)));
    }
}
