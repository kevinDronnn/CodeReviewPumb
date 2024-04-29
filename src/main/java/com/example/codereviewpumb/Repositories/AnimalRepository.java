package com.example.codereviewpumb.Repositories;

import com.example.codereviewpumb.Entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {
    @Query("SELECT animal FROM AnimalEntity animal " +
            "WHERE (:type IS NULL OR :type = '' OR animal.type = :type) " +
            "AND (:category IS NULL OR :category = 0 OR animal.category = :category) " +
            "AND (:sex IS NULL OR :sex = '' OR animal.sex = :sex) " +
            "ORDER BY " +
            "CASE " +
            "   WHEN :sort = 'id' THEN animal.id " +
            "   WHEN :sort = 'category' THEN animal.category " +
            "   WHEN :sort = 'cost' THEN animal.cost " +
            "   WHEN :sort = 'name' THEN animal.name " +
            "   WHEN :sort = 'sex' THEN animal.sex " +
            "   WHEN :sort = 'type' THEN animal.type " +
            "   WHEN :sort = 'weight' THEN animal.weight " +
            "   ELSE animal.id " +
            "END ASC")
    List<AnimalEntity> getAnimalByFilter(
            @Param("type") String type,
            @Param("sex") String sex,
            @Param("category") Byte category,
            @Param("sort") String column);

}
