package com.example.pdfproject.util;

import com.example.pdfproject.model.Prodotto;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ProdottoPdfExport {
    private List<Prodotto> listProdotto;

    public ProdottoPdfExport(List<Prodotto> listProdotto) {
        this.listProdotto = listProdotto;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Prodotto Id", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nome Prodotto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Categoria Prodotto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prezzo", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Prodotto prodotto : listProdotto) {
            table.addCell(String.valueOf(prodotto.getId()));
            table.addCell(prodotto.getNomeProdotto());
            table.addCell(prodotto.getCategoriaProdotto().name());
            table.addCell(String.valueOf(prodotto.getPrezzo()));
            table.addCell(String.valueOf(prodotto.getUploadDate()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Lista dei Prodotti", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
