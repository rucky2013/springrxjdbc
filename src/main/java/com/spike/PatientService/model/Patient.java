package com.spike.PatientService.model;

import com.github.davidmoten.rx.jdbc.annotations.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface Patient {

    @Column("id")
    int id();
    @Column("first_name")
    String firstName();
    @Column("last_name")
    String lastName();
}
