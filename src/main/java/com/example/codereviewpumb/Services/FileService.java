package com.example.codereviewpumb.Services;

import com.example.codereviewpumb.Dto.AnimalDto;
import com.example.codereviewpumb.Entity.AnimalEntity;
import com.example.codereviewpumb.Mappers.AnimalMapper;
import com.example.codereviewpumb.Repositories.AnimalRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class FileService {


    @Autowired
    AnimalRepository repository;

    public void readCsvFile(MultipartFile file) {

        try (InputStreamReader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> data = csvReader.readAll();

            for (int i = 1; i < data.size(); i++) {
                String[] row = data.get(i);
                if (isValidRow(row)) {
                    AnimalDto animalDto = mapToDto(row);
                    animalDto.setCategory(findCategoryByCost(Byte.parseByte(row[4])));
                    System.out.println(animalDto);
                    AnimalEntity animalEntity = AnimalMapper.INSTANCE.animalDtoToAnimalEntity(animalDto);
                    repository.save(animalEntity);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }


    }

    public void readXmlFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            XmlMapper xmlMapper = new XmlMapper();
            AnimalDto[] animalArray = xmlMapper.readValue(inputStream, AnimalDto[].class);

            for (AnimalDto animalDto : animalArray) {

                String[] row = {
                        animalDto.getName(),
                        animalDto.getType(),
                        String.valueOf(animalDto.getSex()),
                        String.valueOf(animalDto.getWeight()),
                        String.valueOf(animalDto.getCost())
                };

                if (isValidRow(row)) {
                    animalDto.setCategory(findCategoryByCost(animalDto.getCost()));
                    AnimalEntity animalEntity = AnimalMapper.INSTANCE.animalDtoToAnimalEntity(animalDto);
                    repository.save(animalEntity);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean isValidRow(String[] row) {
        return row != null && row.length == 5
                && row[0] != null && !row[0].isEmpty()
                && row[1] != null && !row[1].isEmpty()
                && row[2] != null && !row[2].isEmpty()
                && row[3] != null && !row[3].isEmpty()
                && row[4] != null && !row[4].isEmpty();
    }

    protected AnimalDto mapToDto(String[] row) {
        return new AnimalDto(row[0], row[1], row[2], Byte.parseByte(row[3]), Byte.parseByte(row[4]));
    }

    protected byte findCategoryByCost(byte cost) {
        if (cost >= 0 && cost <= 20) {
            return 1;
        } else if (cost >= 21 && cost <= 40) {
            return 2;
        } else if (cost >= 41 && cost <= 60) {
            return 3;
        } else {
            return 4;
        }
    }
}
