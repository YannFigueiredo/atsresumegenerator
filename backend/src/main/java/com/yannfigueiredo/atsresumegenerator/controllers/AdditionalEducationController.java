package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalEducation;
import com.yannfigueiredo.atsresumegenerator.services.AdditionalEducationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/additionaleducation")
public class AdditionalEducationController {
    @Autowired
    AdditionalEducationService additionalEducationService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<AdditionalEducation>> findAllByResumeId(@PathVariable Long resumeId) {
        List<AdditionalEducation> objList = this.additionalEducationService.findAllByResumeId(resumeId);

        return ResponseEntity.ok().body(objList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdditionalEducation> findById(@PathVariable Long id) {
        AdditionalEducation obj = this.additionalEducationService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<AdditionalEducation> create(@Valid @RequestBody AdditionalEducation obj) {
        AdditionalEducation additionalEducation = this.additionalEducationService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(additionalEducation.getId()).toUri();

        return ResponseEntity.created(uri).body(additionalEducation);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdditionalEducation> update(@PathVariable Long id, @Valid @RequestBody AdditionalEducation obj) {
        AdditionalEducation additionalEducation = this.additionalEducationService.update(id, obj);

        return ResponseEntity.ok().body(additionalEducation);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.additionalEducationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
