package com.example.crud;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        return this.repository.findById(id);
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

    @GetMapping("/find/{title}")
    public Lesson getByTitle(@PathVariable String title) {
        return this.repository.findByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> getBetweenDates(@RequestParam String date1, @RequestParam String date2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd", Locale.US);
        Date ld1 = formatter.parse(date1);
        Date ld2 = formatter.parse(date2);
//        Alternate method for formatting
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//        Date ld1 = Date.from(LocalDate.parse(date1).atStartOfDay(defaultZoneId).toInstant());
//        Date ld2 = Date.from(LocalDate.parse(date2).atStartOfDay(defaultZoneId).toInstant());
        return this.repository.findBetweenDates(ld1, ld2);
    }
}
