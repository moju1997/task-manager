package com.jazasoft.taskmanager.restcontroller;


import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.service.LabelService;
import com.jazasoft.taskmanager.utils.ApiUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(ApiUrls.ROOT_URL_LABELS)
public class LabelRestController {

    @Autowired
    LabelService labelService;

    /*Save Label*/
    @PostMapping
    public ResponseEntity<?> saveLabel(@Valid @RequestBody Label label) {
        label=labelService.save(label);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(label.getId()).toUri();
        return ResponseEntity.created(location).body(label);
    }

    /*Get All Label*/
    @GetMapping
    public List<Label> getAll() {
        return labelService.findAll();
    }

    /*Get Label by Id*/
    @GetMapping(ApiUrls.URL_LABELS_LABEL)
    public ResponseEntity<Label> getLabelById(@PathVariable(value = "labelId") Long id) {
        Label label = labelService.findOne(id);
        if (label == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(label);
    }

    /*Updating Label*/
    @PutMapping(ApiUrls.URL_LABELS_LABEL)
    public ResponseEntity<Label> updateLabel(@PathVariable(value = "labelId") Long id, @Valid @RequestBody Label labelDetail) {
        Label label = labelService.findOne(id);
        if (label == null) {
            return ResponseEntity.notFound().build();
        }
        label.setName(labelDetail.getName());
        Label updateLabel = labelService.save(label);
        return ResponseEntity.ok().body(label);
    }

    /*Deleting Label*/
    @DeleteMapping(ApiUrls.URL_LABELS_LABEL)
    public ResponseEntity<Label> deleteLabel(@PathVariable(value = "labelId") Long id) {
        Label label = labelService.findOne(id);
        if (label == null) {
            return ResponseEntity.notFound().build();
        }
        labelService.delete(label);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
