package org.tsc.service;

import org.springframework.web.multipart.MultipartFile;
import org.tsc.schema.RecipientTrainingDataSchema;

import java.util.List;

public interface RecipientsService {

    List<RecipientTrainingDataSchema> getResipientsForTraining();

    void importRecipientsFromExcel(MultipartFile file) throws Exception;

    MultipartFile downloadRecipientsToExcel(String filname) throws Exception;

}
