package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.TscAdministrator;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin
@Tag(name = "Administrator Processing Service Api")
public class AdminController {

    @Autowired
    private CrudService crudService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Operation(summary = "Add Amin", description = "Add Amin into System")
    public ResponseSchema addAdmin(@RequestBody TscAdministrator tscAdministrator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(tscAdministrator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add tscAdministrator success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }


    @GetMapping("/queryById")
    @ResponseBody
    @Operation(summary = "Query Admin", description = "Query Admin from System")
    public TscAdministrator queryAmin(@RequestParam(value = "id") String id) {
        TscAdministrator tscAdministrator = crudService.get(id, TscAdministrator.class);
        return tscAdministrator;
    }


    @PostMapping(value = "/updateById")
    @ResponseBody
    @Operation(summary = "Update Asmin", description = "Update Admin into System")
    public ResponseSchema updateAdmin(@RequestBody TscAdministrator tscAdministrator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(tscAdministrator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Amin success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    @Operation(summary = "Delete TscAdministrator", description = "Delete TscAdministrator into System")
    public ResponseSchema deleteAdmin(@RequestBody TscAdministrator tscAdministrator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(tscAdministrator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Amin success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get Admins", description = "get Admins for service providing")
    @ResponseBody
    public List<TscAdministrator> getAdmins() {
        return crudService.getAll(TscAdministrator.class);
    }

}
