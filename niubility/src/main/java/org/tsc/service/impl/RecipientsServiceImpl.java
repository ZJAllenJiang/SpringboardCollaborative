package org.tsc.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tsc.model.Recipient;
import org.tsc.schema.RecipientExcelFlattenSchema;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.service.CrudService;
import org.tsc.service.FileProcessService;
import org.tsc.service.RecipientsService;
import org.tsc.util.ExcelUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RecipientsServiceImpl implements RecipientsService {

    @Autowired
    CrudService crudService;

    @Autowired
    FileProcessService fileProcessService;

    @Override
    public List<RecipientTrainingDataSchema> getResipientsForTraining() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        List<RecipientTrainingDataSchema> trainingDataSchemas = new ArrayList<>();
        for (Recipient recipient :
                recipients) {
            RecipientTrainingDataSchema recipientTrainingDataSchema = recipient.getTrainingSchema();
            trainingDataSchemas.add(recipientTrainingDataSchema);
        }
        return trainingDataSchemas;
    }

    @Override
    public void importRecipientsFromExcel(MultipartFile file) throws Exception {
        String key = file.getName();
        List<Recipient> recipients = getRecipientsFromExcelFile(file);
        crudService.saveAll(recipients);
        fileProcessService.upload(file);
    }

    @Override
    public MultipartFile downloadRecipientsToExcel(String filname) throws Exception {
        List<Recipient> recipients = crudService.getAll(Recipient.class);
        List<RecipientExcelFlattenSchema> recipientExcelFlattenSchemas = recipients.stream().map(recipient -> recipient.getFlattenSchema()).collect(Collectors.toList());
        HSSFWorkbook workbook = ExcelUtil.toExcel(recipientExcelFlattenSchemas);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] barray = bos.toByteArray();
        InputStream is = new ByteArrayInputStream(barray);
        MultipartFile multipartFile = new MockMultipartFile(filname,filname,"text/plain",is);
        return multipartFile;
    }


    private List<Recipient> getRecipientsFromExcelFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null && multipartFile.getSize() == 0) {
            throw new Exception("The file is null");
        }
        List<RecipientExcelFlattenSchema> recipientExcelFlattenSchemas = ExcelUtil.excelFileToObject(multipartFile, RecipientExcelFlattenSchema.class);
        List<Recipient> recipients =
                recipientExcelFlattenSchemas.stream().map(schema -> schema.getRecipient()).collect(Collectors.toList());
        return recipients;

    }

}
