package com.spike.PatientService.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address{
    private String address1;
    private String address2;
    private String city;
    private String zipCode;
    private String state;
    private String type;
}
