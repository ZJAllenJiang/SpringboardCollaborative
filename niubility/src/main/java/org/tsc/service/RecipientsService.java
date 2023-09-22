package org.tsc.service;

import org.tsc.schema.RecipientTrainingDataSchema;

import java.util.List;

public interface RecipientsService {

    List<RecipientTrainingDataSchema> getResipientsForTraining();

}
