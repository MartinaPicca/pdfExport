package com.example.pdfproject.repository;

import com.example.pdfproject.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProdottoRepository extends JpaRepository <Prodotto, Integer> {

    @Query(value="SELECT p FROM Prodotto p WHERE p.uploadDate BETWEEN ?1 AND ?2 ORDER BY p.uploadDate")
    List<Prodotto> findBetweenDates(LocalDate inizio, LocalDate fine);

    @Query(value="SELECT p FROM Prodotto p WHERE p.prezzo BETWEEN ?1 AND ?2 ORDER BY p.prezzo")
    List<Prodotto> findByPrezzo(Double primoPrezzo, Double secondoPrezzo);
}
