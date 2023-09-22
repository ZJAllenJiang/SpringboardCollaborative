package org.tsc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsc.model.Recipient;
import org.tsc.model.RecipientNeed;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.service.CrudService;
import org.tsc.service.RecipientsService;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecipientsServiceImpl implements RecipientsService {

    @Autowired
    CrudService crudService;


    @Override
    public List<RecipientTrainingDataSchema> getResipientsForTraining() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        List<RecipientTrainingDataSchema> trainingDataSchemas = new ArrayList<>();
        for (Recipient recipient :
                recipients) {
            RecipientTrainingDataSchema recipientTrainingDataSchema =
                    RecipientTrainingDataSchema.builder()
                    .name(recipient.getFirstName() == null ? "" : recipient.getFirstName()
                            + ", " + recipient.getLastName() == null ? "" : recipient.getLastName())
                    .sex(recipient.getGender())
                    .urgent(recipient.getUrgent()).build();

            List<RecipientNeed> needs = recipient.getAssistanceNeeded();
            if(needs != null && !needs.isEmpty()) {
                recipientTrainingDataSchema.setEmployment(needs.contains(RecipientNeed.EMPLOYMENT));
                recipientTrainingDataSchema.setMentalHealthSupport(needs.contains(RecipientNeed.MENTAL_HEALTH_SUPPORT));
                recipientTrainingDataSchema.setMeidicalCare(needs.contains(RecipientNeed.MEDICAL_CARE));
                recipientTrainingDataSchema.setLifeSkills(needs.contains(RecipientNeed.LIFE_SKILLS));
                recipientTrainingDataSchema.setElderly(needs.contains(RecipientNeed.ELDERLY));
                recipientTrainingDataSchema.setServiceConnections(needs.contains(RecipientNeed.SERVICE_CONNECTIONS));
                recipientTrainingDataSchema.setReentry(needs.contains(RecipientNeed.REENTRY));
                recipientTrainingDataSchema.setDisablity(needs.contains(RecipientNeed.DISABILITY));
                recipientTrainingDataSchema.setSocialSecurity(needs.contains(RecipientNeed.SOCIAL_SECURITY));
                recipientTrainingDataSchema.setHousingProgram(needs.contains(RecipientNeed.HOUSING_PROGRAM));
            }
            trainingDataSchemas.add(recipientTrainingDataSchema);
        }
        return trainingDataSchemas;
    }
}
