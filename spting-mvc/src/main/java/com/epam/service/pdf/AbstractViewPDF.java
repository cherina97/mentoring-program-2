package com.epam.service.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@Slf4j
public abstract class AbstractViewPDF extends AbstractView {

    public AbstractViewPDF() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("IE workaround: write into byte array first.");
        ByteArrayOutputStream baos = createTemporaryOutputStream();

        log.info("Apply preferences and build metadata.");
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        buildPdfMetadata(model, document, request);

        log.info("Build PDF document.");
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();

        log.info("Flush to HTTP response.");
        writeToResponse(response, baos);
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

//https://www.netjstech.com/2018/09/spring-mvc-pdf-generation-example.html
