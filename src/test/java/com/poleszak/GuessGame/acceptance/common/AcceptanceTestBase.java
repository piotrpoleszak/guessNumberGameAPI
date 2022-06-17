package com.poleszak.GuessGame.acceptance.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class AcceptanceTestBase {

    @Autowired
    protected MockMvc mvc;

    protected final String PATH = "http://localhost:8080/api";
}
