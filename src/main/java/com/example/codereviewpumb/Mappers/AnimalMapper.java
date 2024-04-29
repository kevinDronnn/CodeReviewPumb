package com.example.codereviewpumb.Mappers;

import com.example.codereviewpumb.Dto.AnimalDto;
import com.example.codereviewpumb.Entity.AnimalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "sex", source = "sex")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "cost", source = "cost")
    @Mapping(target = "category", source = "category")
    AnimalEntity animalDtoToAnimalEntity(AnimalDto animalDto);


    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "sex", source = "sex")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "cost", source = "cost")
    @Mapping(target = "category", source = "category")
    List<AnimalDto> animalEntityToAnimalDto(List<AnimalEntity> animalEntity);
}
