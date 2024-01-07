package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{

    private String referenceNumber ;
    private long amount ;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode ;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus ;
}
