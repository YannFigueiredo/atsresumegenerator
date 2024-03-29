package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.Experience;
import com.yannfigueiredo.atsresumegenerator.services.ExperienceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<Experience>> findAllByResumeId(@PathVariable Long resumeId) {
        List<Experience> objList = this.experienceService.findAllByResumeId(resumeId);

        return ResponseEntity.ok().body(objList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Experience> findById(@PathVariable Long id) {
        Experience obj = this.experienceService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Experience> create(@Valid @RequestBody Experience obj) {
        Experience experience = this.experienceService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(experience);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Experience> update(@PathVariable Long id, @Valid @RequestBody Experience obj) {
        Experience experience = this.experienceService.update(id, obj);

        return ResponseEntity.ok().body(experience);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.experienceService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
