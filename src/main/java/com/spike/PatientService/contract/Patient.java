package com.spike.PatientService.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private Order order;
}
