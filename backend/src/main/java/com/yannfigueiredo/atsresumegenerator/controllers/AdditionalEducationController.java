package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.Education;
import com.yannfigueiredo.atsresumegenerator.services.EducationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    EducationService educationService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<Education>> findAllByResumeId(@PathVariable Long resumeId) {
        List<Education> objList = this.educationService.findAllByResumeId(resumeId);

        return ResponseEntity.ok().body(objList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Education> findById(@PathVariable Long id) {
        Education obj = this.educationService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Education> create(@Valid @RequestBody Education obj) {
        Education education = this.educationService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(education.getId()).toUri();

        return ResponseEntity.created(uri).body(education);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Education> update(@PathVariable Long id, @Valid @RequestBody Education obj) {
        Education education = this.educationService.update(id, obj);

        return ResponseEntity.ok().body(education);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.educationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
