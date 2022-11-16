package com.example.springjpatest.rest;

import com.example.springjpatest.Application;
import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles(value = {"production", "test"})
public class BookIT {
    @Autowired
    private MockMvc mockMvc;
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int randomServerPort;

    @Test
    public void getsAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getsSingle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void returnsNotFoundForInvalidSingle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void addsNewBook() throws Exception {
        final String baseUrl = "http://localhost:" + randomServerPort + "/book";
        URI uri = new URI(baseUrl);
        BookEntityDto newBook = new BookEntityDto("IT_test_create", BookTypeEnum.BOOK_TYPE_BIOGRAPHY);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, newBook, String.class);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

}
