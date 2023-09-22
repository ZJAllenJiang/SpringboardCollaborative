package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.Recipient;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;
import org.tsc.service.RecipientsService;

import java.util.List;

@RestController
@RequestMapping("/recipient")
public class RecipientController {

    @Autowired
    private CrudService crudService;

    @Autowired
    private RecipientsService recipientsService;

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
