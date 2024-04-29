package com.example.codereviewpumb.Services;

import com.example.codereviewpumb.Dto.AnimalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class FileServiceTest {

    private FileService fileService = new FileService();

    AnimalDto animalDto = null;

    @BeforeEach
    void setUp(){
        animalDto = new AnimalDto();
        animalDto.setName("Jack");
        animalDto.setType("cat");
        animalDto.setSex("female");
        animalDto.setWeight((byte) 0);
        animalDto.setCost((byte) 12);
        animalDto.setCategory((byte) 0);
    }

    @Test
    public void shouldReturnDtoFromArrayRow(){
        String[] row = {"Jack", "cat", "female", "0", "12"};
        assertEquals(animalDto, fileService.mapToDto(row));
    }

    @Test
    void shouldCheckIsValidRowMustReturnTrue() {
        String[] row = {"Jack", "cat", "female", "0", "12"};
        assertTrue(fileService.isValidRow(row));
    }

    @Test
    void shouldCheckIsValidRowMustReturnFalse() {
        String[] row = {"Jack", "cat", "female", "0"};
        assertFalse(fileService.isValidRow(row));
    }

    @Test
    void shouldSetCategory() {
        assertEquals(1, fileService.findCategoryByCost((byte) 0));
    }
}