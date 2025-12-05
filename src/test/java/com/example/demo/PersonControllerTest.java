package com.example.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void postPersonDelegatesToServiceAndReturnsResponse() throws Exception {
        Person expectedPerson = new Person(30, "Alice");
        when(personService.logPerson(expectedPerson)).thenReturn(expectedPerson);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"age\":30,\"name\":\"Alice\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"age\":30,\"name\":\"Alice\"}"));

        verify(personService).logPerson(expectedPerson);
    }
}
