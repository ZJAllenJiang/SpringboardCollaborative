package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tsc.model.Recipient;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;
import org.tsc.service.FileProcessService;
import org.tsc.service.RecipientsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/recipient")
public class RecipientController {

    @Autowired
    private CrudService crudService;

    @Autowired
    private RecipientsService recipientsService;

    @Autowired
    private FileProcessService fileProcessService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Operation(summary = "Add Recipient", description = "Add Recipient into System")
    public ResponseSchema addRecipient(@RequestBody Recipient recipient) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(recipient);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Recipient success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @PostMapping(value = "/addAll")
    @Operation(summary = "Add Recipients", description = "add Recipients for service providing")
    @ResponseBody
    public ResponseSchema addRecipients(@RequestBody  List<Recipient> recipients) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.saveAll(recipients);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Recipients success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @PostMapping(value = "/importByExcel", consumes ="multipart/form-data")
    @Operation(summary = "Add Recipients", description = "add Recipients for service providing")
    @ResponseBody
    public ResponseSchema importByExcel(@RequestBody()  MultipartFile multipartFile) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            recipientsService.importRecipientsFromExcel(multipartFile);
            responseSchema.setStatus("200");
            responseSchema.setMessage("importByExcel success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseSchema;
    }

    @PostMapping(value = "/downloadRecipientsExcel")
    @Operation(summary = "downloadRecipientsExcel Recipients", description = "downloadRecipientsExcel Recipients for service providing")
    @ResponseBody
    public ResponseEntity<Resource> downloadRecipientsExcel(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        InputStream instream = null;
        try {
            MultipartFile multipartFile = recipientsService.downloadRecipientsToExcel(fileName);
            instream = multipartFile.getInputStream();
            response.reset();
            response.addHeader(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] barr = new byte[1024];
            int len = 0;
            while ((len = instream.read(barr)) > 0 ) {
                outputStream.write(barr, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @GetMapping("/query")
    @ResponseBody
    @Operation(summary = "Query Recipient", description = "Query Recipient from System")
    public Recipient queryRecipient(@RequestParam(value = "id") String id) {
        Recipient Recipient = crudService.get(id, Recipient.class);
        return Recipient;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Operation(summary = "Update Recipient", description = "Update Recipient into System")
    public ResponseSchema updateRecipient(@RequestBody Recipient Recipient) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(Recipient);
            responseSchema.setStatus("200");
            responseSchema.setMessage("update Recipient success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    @Operation(summary = "Delete Recipient", description = "Delete Recipient into System")
    public ResponseSchema deleteRecipient(@RequestBody Recipient Recipient) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(Recipient);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Recipient success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get Recipients", description = "get Recipients for service providing")
    @ResponseBody
    public List<Recipient> getRecipients() {
        return crudService.getAll(Recipient.class);
    }

    @GetMapping(value = "/getResipientsForTraining")
    @Operation(summary = "Get RecipientTrainingDataSchemas", description = "get RecipientTrainingDataSchema for service providing")
    @ResponseBody
    public List<RecipientTrainingDataSchema> getResipientsForTraining() {
        return recipientsService.getResipientsForTraining();
    }


}
