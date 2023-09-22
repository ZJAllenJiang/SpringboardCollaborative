package org.tsc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tsc.model.Navigator;
import org.tsc.model.Recipient;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.service.CrudService;
import org.tsc.service.NavigatorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigatorServiceImpl implements NavigatorService {

    @Autowired
    private CrudService crudService;

    @Override
    public List<Navigator> getRecommendedNavigators(Recipient recipient) {
        RestTemplate restTemplate = new RestTemplate();
        String navigatorRecommedUrl = "https://mawqz64mpt.us-east-1.awsapprunner.com/getPrediction/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecipientTrainingDataSchema> requestEntity = new HttpEntity<RecipientTrainingDataSchema>(recipient.getTrainingSchema(), headers);
        ResponseEntity<List> responseEntity = restTemplate.postForEntity(navigatorRecommedUrl , requestEntity, List.class);

        List<Navigator> navigators = new ArrayList<>();
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            for (Object naviId:
                    responseEntity.getBody()) {
                navigators.add(crudService.get((String) naviId, Navigator.class));
            }
        }
        return navigators;
    }
}
