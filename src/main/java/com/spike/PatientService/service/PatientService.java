package com.spike.PatientService.service;

import com.spike.PatientService.model.Address;
import com.spike.PatientService.model.Order;
import com.spike.PatientService.model.Patient;
import com.spike.PatientService.model.Refill;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    public List<Patient> getAllPatients(){
        List<Refill> refillList1 = Arrays.asList(new Refill("p1"), new Refill("p2"));
        Address address1 = new Address("add1", "add2", "city", "zip1", "state1");
        Order order1 = new Order(1, address1, refillList1);
        Patient patient1 = new Patient(1, "fname1", "lname1", order1);
        Order order2 = new Order(2, address1, refillList1);
        Patient patient2 = new Patient(2, "fname2", "lname2", order2);
        return Arrays.asList(patient1, patient2);
    }

}
