package com.example.pdfproject.controller;

import com.example.pdfproject.model.Prodotto;
import com.example.pdfproject.service.ProdottoService;
import com.example.pdfproject.util.ProdottoPdfExport;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdottoController {
    @Autowired
    private ProdottoService service;

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=prodotto_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Prodotto> listProdotto = service.findAll();

        ProdottoPdfExport exporter = new ProdottoPdfExport(listProdotto);
        exporter.export(response);

    }

    @PostMapping("/save")
    public Prodotto saveProdotto(@RequestBody String[] lista){
        return service.saveProdotto(lista);
    }

    @PostMapping("/prezzo")
    public List<Prodotto> findByPrezzo(@RequestBody Double[] numeri){
        return service.findByPrezzo(numeri);
    }
}
