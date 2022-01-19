package com.example.pdfproject.service;

import com.example.pdfproject.model.CategoriaProdotto;
import com.example.pdfproject.model.InsertData;
import com.example.pdfproject.model.Prodotto;
import com.example.pdfproject.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    ProdottoRepository prodottoRepository;

    public List<Prodotto> findAll(){
        return prodottoRepository.findAll(Sort.by("prezzo").ascending());
    }

    public Prodotto saveProdotto(String[] lista){
        Prodotto prodotto = new Prodotto();
        prodotto.setNomeProdotto(lista[0]);
        CategoriaProdotto categoria = CategoriaProdotto.valueOf(lista[1]);
        prodotto.setCategoriaProdotto(categoria);
        Double prezzo = Double.parseDouble(lista[2]);
        prodotto.setPrezzo(prezzo);
        LocalDate date = LocalDate.now();
        LocalDate dateOK = date.plusDays(1);
        prodotto.setUploadDate(dateOK);
        return prodottoRepository.save(prodotto);

    }
    
    public List<Prodotto> findByData(InsertData insert){
    	LocalDate date1 = insert.getUno();
    	LocalDate date2 = insert.getDue();
    	LocalDate datePlus1 = date1.plusDays(1);
    	LocalDate datePlus2 = date2.plusDays(1);
    	return prodottoRepository.findBetweenDates( datePlus1, datePlus2 );
    }
    
    public List<Prodotto> findByDatatest(InsertData insert){
    	LocalDate date1 = insert.getUno();
    	LocalDate date2 = insert.getDue();
    	LocalDate datePlus1 = date1.plusDays(1);
    	LocalDate datePlus2 = date2.plusDays(1);
    	return prodottoRepository.findBetweenDates( datePlus1, datePlus2 );
    }

    public List<Prodotto> findByPrezzo(Double[] numeri){
        return prodottoRepository.findByPrezzo(numeri[0],numeri[1]);
    }


}
