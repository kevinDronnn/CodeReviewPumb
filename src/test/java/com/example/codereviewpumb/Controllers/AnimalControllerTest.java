package com.example.codereviewpumb.Controllers;

import com.example.codereviewpumb.Entity.AnimalEntity;
import com.example.codereviewpumb.Mappers.AnimalMapper;
import com.example.codereviewpumb.Services.AnimalService;
import com.example.codereviewpumb.Services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AnimalController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnimalService animalService;

    @MockBean
    private FileService fileService;

    @MockBean
    AnimalMapper animalMapper;

    AnimalEntity animalEntity = null;

    @BeforeEach
    void setUp(){
        animalEntity = new AnimalEntity();
        animalEntity.setName("Jack");
        animalEntity.setType("cat");
        animalEntity.setSex("female");
        animalEntity.setWeight((byte) 0);
        animalEntity.setCost((byte) 12);
        animalEntity.setCategory((byte) 1);
    }


    @Test
    public void shouldReturnSortedListOfAnimalsByParameters() throws Exception {
        String type = "cat";
        String sex = "female";
        byte category = 1;
        String column = "cost";

        when(animalService.getAnimalsByParams(type, sex, category, column)).thenReturn(List.of(animalEntity));

        mockMvc.perform(get("/api/getAnimalsByOptions")
                        .param("type", type)
                        .param("sex", sex)
                        .param("category", String.valueOf(category))
                        .param("column", column)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(animalEntity))));

        verify(animalService, times(1)).getAnimalsByParams(type, sex, category, column);
    }

    @Test
    public void shouldAddAnimalsFromFileCsv() throws Exception {
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/animals.csv"));

        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", content);

        doNothing().when(fileService).readCsvFile(file);

        mockMvc.perform(multipart("/api/files/uploads")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        verify(fileService, times(1)).readCsvFile(file);
    }

    @Test
    public void shouldAddAnimalsFromFileXml() throws Exception {
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/animals.xml"));

        MockMultipartFile file = new MockMultipartFile("file", "animals.xml", "application/xml", content);

        doNothing().when(fileService).readXmlFile(file);

        mockMvc.perform(multipart("/api/files/uploads")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        verify(fileService, times(1)).readXmlFile(file);
    }



}