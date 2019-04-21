package com.jazasoft.taskmanager.repository;


import com.jazasoft.taskmanager.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}
