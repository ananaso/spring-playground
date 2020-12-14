package com.example.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);

    @Query(value = "select l from Lesson l where deliveredOn between ?1 and ?2")
    List<Lesson> findBetweenDates(Date date1, Date date2);
}
