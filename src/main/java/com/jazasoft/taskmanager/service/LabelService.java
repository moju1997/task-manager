package com.jazasoft.taskmanager.service;


import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    LabelRepository labelRepository;

    /*Save Task*/
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    /*Search All Tasks*/
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    /*Get One Task*/
    public Label findOne(Long id) {
        return labelRepository.findById(id).orElse(null);
    }

    /*Delete Task*/
    public void delete(Label label) {
        labelRepository.delete(label);
    }
}
