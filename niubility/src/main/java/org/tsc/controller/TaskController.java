package org.tsc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tsc.model.TscTask;
import org.tsc.schema.ResponseSchema;
import org.tsc.service.CrudService;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
@CrossOrigin
@Tag(name = "Task Processing Service Api")
public class TaskController {

    @Autowired
    private CrudService crudService;

    @PostMapping(value = "/add")
    @ResponseBody
    @Operation(summary = "Add tscTask", description = "Add tscTask into System")
    public ResponseSchema addTscTask(@RequestBody TscTask tscTask) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.save(tscTask);
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
    @Operation(summary = "Query tscTask", description = "Query tscTask from System")
    public TscTask queryTscTask(@RequestParam(value = "id") String id) {
        TscTask tscTask = crudService.get(id, TscTask.class);
        return tscTask;
    }


    @PostMapping(value = "/update")
    @ResponseBody
    @Operation(summary = "Update tscTask", description = "Update tscTask into System")
    public ResponseSchema updateTscTask(@RequestBody TscTask tscTask) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.update(tscTask);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add tscTask success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    @Operation(summary = "Delete tscTask", description = "Delete tscTask into System")
    public ResponseSchema deleteTscTask(@RequestBody TscTask tscTask) {
        ResponseSchema responseSchema = ResponseSchema.builder().build();
        try {
            crudService.delete(tscTask);
            responseSchema.setStatus("200");
            responseSchema.setMessage("add tscTask success");
        } catch (Exception e) {
            responseSchema.setStatus("400");
            responseSchema.setMessage(e.getMessage());
        }

        return responseSchema;
    }

    @GetMapping(value = "/getAll")
    @Operation(summary = "Get tscTasks", description = "get tscTasks for service providing")
    @ResponseBody
    public List<TscTask> getTscTask() {
        return crudService.getAll(TscTask.class);
    }

}
