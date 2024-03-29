package com.yannfigueiredo.atsresumegenerator.controllers;

import com.yannfigueiredo.atsresumegenerator.models.OtherLink;
import com.yannfigueiredo.atsresumegenerator.services.OtherLinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/otherlink")
public class OtherLinkController {
    @Autowired
    OtherLinkService otherLinkService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<List<OtherLink>> findAllByResumeId(@PathVariable Long resumeId) {
        List<OtherLink> objList = this.otherLinkService.findAllByResumeId(resumeId);

        return ResponseEntity.ok().body(objList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OtherLink> findById(@PathVariable Long id) {
        OtherLink obj = this.otherLinkService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OtherLink> create(@Valid @RequestBody OtherLink obj) {
        OtherLink otherLink = this.otherLinkService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(otherLink.getId()).toUri();

        return ResponseEntity.created(uri).body(otherLink);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OtherLink> update(@PathVariable Long id, @Valid @RequestBody OtherLink obj) {
        OtherLink otherLink = this.otherLinkService.update(id, obj);

        return ResponseEntity.ok().body(otherLink);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.otherLinkService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
