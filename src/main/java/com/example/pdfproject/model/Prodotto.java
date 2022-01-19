package com.example.pdfproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String nomeProdotto;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

    @Column
    private double prezzo;

    @Column
    private LocalDate uploadDate;
}

