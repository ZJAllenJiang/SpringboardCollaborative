package org.tsc.controller;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tsc.model.Navigator;
import org.tsc.model.Organization;
import org.tsc.model.Recipient;
import org.tsc.model.TscTask;
import org.tsc.service.MockService;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/dnn")
@Tag(name = "Tsc Application Api")
public class SpringboardCollaborativeController {

    @Autowired
    DynamoDbTemplate dynamoDbTemplate;

    @Autowired
    private MockService mockService;

    Region region = Region.US_EAST_1;

    DynamoDbClient ddb = DynamoDbClient.builder()
            // .credentialsProvider(credentialsProvider)
            .region(region)
            .build();


    @GetMapping("")
    @Operation(summary = "get Dbinfo")
    public List<String> getDBInfo() {
        ListTablesResponse response = null;
        ListTablesRequest request = ListTablesRequest.builder().build();
        response = ddb.listTables(request);

        return response.tableNames();
    }

    @GetMapping(value = "/navigators")
    @Operation(summary = "Get Navigators", description = "get navigators for task assigning")
    @ResponseBody
    public List<Navigator> getNavigators() {
        return mockService.getNavigators();
    }

    @GetMapping(value = "/recipients")
    @Operation(summary = "Get Recipients", description = "get recipients for task assigning")
    @ResponseBody
    public List<Recipient> getReceipients() {
        return mockService.getReceipients();
    }

    @GetMapping(value = "/organizations")
    @Operation(summary = "Get Organizations", description = "get organizations for service providing")
    @ResponseBody
    public List<Organization> getOrganizations() {
        return mockService.getOrganizations();
    }

    @GetMapping(value = "/tasks")
    @Operation(summary = "Get Tasks", description = "get tasks for task assigning")
    @ResponseBody
    public List<TscTask> getTasks() {
        return mockService.getTasks();
    }

}
