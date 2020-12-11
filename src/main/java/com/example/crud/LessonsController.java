package com.example.crud;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getByID(@PathVariable Long id) {
        Optional<Lesson> lesson = this.repository.findById(id);
        return lesson;
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Optional<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        Optional<Lesson> oldLesson = this.repository.findById(id);
        oldLesson.ifPresent(value -> {
            value.setTitle(lesson.getTitle());
            value.setDeliveredOn(lesson.getDeliveredOn());
            this.repository.save(value);
        });
        return oldLesson;
    }
}
