package org.tsc.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.Navigator;
import org.tsc.model.Recipient;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;
import org.tsc.service.NavigatorService;

import java.util.List;

@RestController
@RequestMapping(value = "/navigator")
@CrossOrigin
@Tag(name = "Navigator Processing Service Api")
public class NavigatorController {

    @Autowired
    private CrudService crudService;

    @Autowired
    private NavigatorService navigatorService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Operation(summary = "Add Navigator", description = "Add Navigator into System")
    public ResponseSchema addNavigator(@RequestBody Navigator navigator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(navigator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Navigator success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping("/queryById")
    @ResponseBody
    @Operation(summary = "Query Navigator", description = "Query Navigator from System")
    public Navigator queryNavigator(@RequestParam(value = "id") String id) {
        Navigator navigator = crudService.get(id, Navigator.class);
        return navigator;
    }


    @PostMapping(value = "/update")
    @ResponseBody
    @Operation(summary = "Update Navigator", description = "Update Navigator into System")
    public ResponseSchema updateNavigator(@RequestBody Navigator navigator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(navigator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add Navigator success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    @Operation(summary = "Delete Navigator", description = "Delete Navigator into System")
    public ResponseSchema deleteNavigator(@RequestBody Navigator navigator) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(navigator);
            responseSchema.setStatus("200");
            responseSchema.setMessage("delete Navigator success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get Navigators", description = "get Navigators for service providing")
    @ResponseBody
    public List<Navigator> getNavigators() {
        return crudService.getAll(Navigator.class);
    }

    @GetMapping(value = "/getRecommendNav")
    @Operation(summary = "Get Recommend Navigators", description = "get Recommend Navigators for service providing")
    @ResponseBody
    public List<Navigator> getRecommendNav(@RequestParam("recipientId") String recipientId) {
        return navigatorService.getRecommendedNavigators(crudService.get(recipientId, Recipient.class));
    }

}
