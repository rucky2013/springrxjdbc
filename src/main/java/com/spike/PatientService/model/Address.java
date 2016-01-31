package com.spike.PatientService.model;

import com.github.davidmoten.rx.jdbc.annotations.Column;

public interface Address {

    @Column("address1")
    String address1();

    @Column("address2")
    String address2();

    @Column("city")
    String city();

    @Column("state")
    String state();

    @Column("zip_code")
    String zipCode();

    @Column("type")
    String type();
}
