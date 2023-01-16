package com.example.commonbackend;

import com.example.commonbackend.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class OrderTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ))
                .build();
    }

    @Test
    void getOrders() throws Exception {
        MvcResult result = mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andDo(document("orders-get"))
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Order> actual = mapper
                .readValue(result
                                .getResponse()
                                .getContentAsString(),
                        new TypeReference<>() {
                        });
    }

    @Test
    void postOrder() throws Exception {
        this.mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"customerID\": \"VINET\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andDo(document("order-post",
                        requestPartBody("uri"),
                        requestFields(
                                fieldWithPath("customerID")
                                        .description("The customer's ID."),
                                fieldWithPath("id").optional().type(JsonFieldType.STRING)
                                        .description("(optional) The order date."),
                                fieldWithPath("shippedDate").optional().type(JsonFieldType.STRING)
                                        .description("(optional) The shipping date.")),
                        responseFields(fieldWithPath("customerID")
                                        .description("The customer's ID"),
                                fieldWithPath("id")
                                        .description("Order ID"),
                                fieldWithPath("orderDate")
                                        .description("Order date of order."),
                                fieldWithPath("shippedDate")
                                        .description("Shipped date of order."))
                ));
    }

    @Test
    void addOrderWithProductID() throws Exception {
        // summary: "POST addOrder/{customerId}/{productId}"
        this.mockMvc.perform(post("/addOrder/{customerId}/{productId}", "VINET", 2))
                .andExpect(status().isCreated())
                .andDo(document("order-post-withId",
                        pathParameters(
                                parameterWithName("customerId")
                                        .description("The customer ID."),
                                parameterWithName("productId")
                                        .description("The product to add to the order.")
                        )));
    }

    @Test
    void deleteOrder() throws Exception {
        // summary: "DELETE deleteOrder/{orderId}"
        this.mockMvc.perform(delete("/deleteOrder/{orderID}", 10254))
                .andExpect(status().isOk())
                .andDo(document("order-delete",
                        pathParameters(
                                parameterWithName("orderID")
                                        .description("The ID of the order.")
                        )));
    }

    @Test
    void getOrderRows() throws Exception {
        //summary: "GET order/{orderID}"
        this.mockMvc.perform(get("/order/{orderID}", 10253))
                .andExpect(status().isOk())
                .andDo(document("order-get-rows",
                        pathParameters(
                                parameterWithName("orderID")
                                        .description("The ID of the order.")
                        )));
    }


}
