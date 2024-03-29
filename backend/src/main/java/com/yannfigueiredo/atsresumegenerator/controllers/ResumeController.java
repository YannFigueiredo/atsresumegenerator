package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.Resume;
import com.yannfigueiredo.atsresumegenerator.services.ResumeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Resume> findById(@PathVariable Long id) {
        Resume obj = this.resumeService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Resume> create(@Valid @RequestBody Resume obj) {
        Resume resume = this.resumeService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(resume.getId()).toUri();

        return ResponseEntity.created(uri).body(resume);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Resume> update(@Valid @RequestBody Resume obj, @PathVariable Long id) {
        Resume resume = this.resumeService.update(id, obj);

        return ResponseEntity.ok().body(resume);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.resumeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
