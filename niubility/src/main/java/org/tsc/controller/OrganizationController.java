package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.Organization;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;

import java.util.List;

@RestController
@RequestMapping("/org")
@CrossOrigin
public class OrganizationController {

    @Autowired
    private CrudService crudService;
    
    @PostMapping(value = "/addOrganization")
    @ResponseBody
    @Operation(summary = "Add Organization", description = "Add Organization into System")
    public ResponseSchema addOrganization(@RequestBody Organization organization) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(organization);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Organization success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping("/queryOrganization")
    @ResponseBody
    @Operation(summary = "Query Organization", description = "Query Organization from System")
    public Organization queryOrganization(@RequestParam(value = "id") String id) {
        Organization Organization = crudService.get(id, Organization.class);
        return Organization;
    }

    @PostMapping(value = "/updateOrganization")
    @ResponseBody
    @Operation(summary = "Update Organization", description = "Update Organization into System")
    public ResponseSchema updateOrganization(@RequestBody Organization organization) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(organization);
            responseSchema.setStatus("200");
            responseSchema.setMessage("update Organization success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/deleteOrganization")
    @ResponseBody
    @Operation(summary = "Delete Organization", description = "Delete Organization into System")
    public ResponseSchema deleteOrganization(@RequestBody Organization organization) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(organization);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Organization success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/organizations")
    @Operation(summary = "Get Organizations", description = "get organizations for service providing")
    @ResponseBody
    public List<Organization> getOrganizations() {
        return crudService.getAll(Organization.class);
    }

}
