package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.AdditionalInfo;
import com.yannfigueiredo.atsresumegenerator.services.AdditionalInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/additionalinfo")
public class AdditionalInfoController {
    @Autowired
    AdditionalInfoService additionalInfoService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<AdditionalInfo>> findAllByResumeId(@PathVariable Long resumeId) {
        List<AdditionalInfo> objList = this.additionalInfoService.findAllByResumeId(resumeId);

        return ResponseEntity.ok().body(objList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdditionalInfo> findById(@PathVariable Long id) {
        AdditionalInfo obj = this.additionalInfoService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<AdditionalInfo> create(@Valid @RequestBody AdditionalInfo obj) {
        AdditionalInfo additionalInfo = this.additionalInfoService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(additionalInfo.getId()).toUri();

        return ResponseEntity.created(uri).body(additionalInfo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdditionalInfo> update(@PathVariable Long id, @Valid @RequestBody AdditionalInfo obj) {
        AdditionalInfo additionalInfo = this.additionalInfoService.update(id, obj);

        return ResponseEntity.ok().body(additionalInfo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.additionalInfoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
