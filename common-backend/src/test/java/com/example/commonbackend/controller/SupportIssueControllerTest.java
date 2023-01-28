package com.example.commonbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class SupportIssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    void getSupportIssues() throws Exception {
        // @GetMapping("/supportissues")
        // TODO: Implement this
    }

    @Test
    void getSupportIssuesForCustomer() throws Exception {
        //@GetMapping("/supportissues/{customerId}")
        // @PathVariable String customerId
        // TODO: Implement this
    }

    @Test
    void updateSupportIssue() throws Exception {
        // @PutMapping("/supportissue")
        //  @RequestBody SupportIssue issue
        // TODO: Implement this
    }

    @Test
    void addSupportIssue() throws Exception {
        // @PostMapping("/supportissue/{customerId}")
        // @PathVariable String customerId,
        //    @RequestBody SupportIssue issue
        // TODO: Implement this
    }

    @Test
    void deleteSupportIssue() throws Exception {
        // @DeleteMapping("/supportissue/{customerId}")
        // @PathVariable String customerId,
        // TODO: Implement this
    }

}
