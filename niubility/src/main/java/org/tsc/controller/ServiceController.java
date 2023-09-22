package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.Service;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;

import java.util.List;

@RestController
@RequestMapping("/Service")
public class ServiceController {

    @Autowired
    private CrudService crudService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Operation(summary = "Add Service", description = "Add Service into System")
    public ResponseSchema addService(@RequestBody Service Service) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(Service);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Service success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping("/query")
    @ResponseBody
    @Operation(summary = "Query Service", description = "Query Service from System")
    public Service queryService(@RequestParam(value = "id") String id) {
        Service Service = crudService.get(id, Service.class);
        return Service;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @Operation(summary = "Update Service", description = "Update Service into System")
    public ResponseSchema updateService(@RequestBody Service Service) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(Service);
            responseSchema.setStatus("200");
            responseSchema.setMessage("update Service success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    @Operation(summary = "Delete Service", description = "Delete Service into System")
    public ResponseSchema deleteService(@RequestBody Service Service) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(Service);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Service success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get Services", description = "get Services for service providing")
    @ResponseBody
    public List<Service> getServices() {
        return crudService.getAll(Service.class);
    }
    
}
