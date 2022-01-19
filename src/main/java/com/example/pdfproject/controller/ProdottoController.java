package com.example.pdfproject.controller;

import com.example.pdfproject.model.InsertData;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdottoController {
    @Autowired
    private ProdottoService service;
    
    @PostMapping("/data")
	public void findByDate(@RequestBody InsertData insert,  HttpServletResponse response)throws DocumentException, IOException{
    	 response.setContentType("application/pdf"); 
    	 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
         String currentDateTime = dateFormatter.format(new Date());
         
         String headerKey = "Content-Disposition";
         String headerValue = "attachment; filename=prodotto_" + currentDateTime + ".pdf";
         response.setHeader(headerKey, headerValue);
         
         List<Prodotto> listProdotto = service.findByData(insert);
         
         ProdottoPdfExport exporter = new ProdottoPdfExport(listProdotto);
         String s = " per data";
         exporter.export(response, s);
    }
    
    @PostMapping("/prezzo")
    public void findByPrezzo(@RequestBody Double[] numeri, HttpServletResponse response)throws DocumentException, IOException{
    	 response.setContentType("application/pdf"); 
    	 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
         String currentDateTime = dateFormatter.format(new Date());
         
         String headerKey = "Content-Disposition";
         String headerValue = "attachment; filename=prodotto_" + currentDateTime + ".pdf";
         response.setHeader(headerKey, headerValue);
         
         List<Prodotto> listProdotto = service.findByPrezzo(numeri);
         
         ProdottoPdfExport exporter = new ProdottoPdfExport(listProdotto);
         String s = " per prezzo";
         exporter.export(response, s);
    }

    @GetMapping("/download/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=prodotto_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Prodotto> listProdotto = service.findAll();

        ProdottoPdfExport exporter = new ProdottoPdfExport(listProdotto);
        String s = " totale";
        exporter.export(response, s);

    }

    @PostMapping("/save")
    public Prodotto saveProdotto(@RequestBody String[] lista){
        return service.saveProdotto(lista);
    }


}
