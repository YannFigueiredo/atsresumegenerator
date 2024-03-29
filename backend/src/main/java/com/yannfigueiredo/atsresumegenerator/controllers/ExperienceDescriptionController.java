package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.ExperienceDescription;
import com.yannfigueiredo.atsresumegenerator.services.ExperienceDescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/experiencedescription")
public class ExperienceDescriptionController {
    @Autowired
    ExperienceDescriptionService experienceDescriptionService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<ExperienceDescription>> findAllByExperienceId(@PathVariable Long experienceId) {
        List<ExperienceDescription> experienceDescriptionList = this.experienceDescriptionService.
                findAllByExperienceId(experienceId);

        return ResponseEntity.ok().body(experienceDescriptionList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExperienceDescription> findById(@PathVariable Long id) {
        ExperienceDescription experienceDescription = this.experienceDescriptionService.findById(id);

        return ResponseEntity.ok().body(experienceDescription);
    }

    @PostMapping
    public ResponseEntity<ExperienceDescription> create(@Valid @RequestBody ExperienceDescription obj) {
        ExperienceDescription experienceDescription = this.experienceDescriptionService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(experienceDescription);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExperienceDescription> update(@PathVariable Long id, @Valid @RequestBody ExperienceDescription obj) {
        ExperienceDescription experienceDescription = this.experienceDescriptionService.update(id, obj);

        return ResponseEntity.ok().body(experienceDescription);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.experienceDescriptionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
