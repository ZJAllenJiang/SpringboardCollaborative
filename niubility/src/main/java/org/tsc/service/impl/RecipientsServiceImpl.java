package org.tsc.service.impl;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tsc.model.*;
import org.tsc.schema.RecipientExcelFlattenSchema;
import org.tsc.schema.RecipientTrainingDataSchema;
import org.tsc.service.CrudService;
import org.tsc.service.FileProcessService;
import org.tsc.service.RecipientsService;
import org.tsc.util.ExcelUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
            RecipientTrainingDataSchema recipientTrainingDataSchema =
                    RecipientTrainingDataSchema.builder()
                            .name(recipient.getFirstName() == null ? "" : recipient.getFirstName()
                                    + ", " + recipient.getLastName() == null ? "" : recipient.getLastName())
                            .sex(recipient.getGender())
                            .urgent(recipient.getUrgent()).build();

            List<RecipientNeed> needs = recipient.getAssistanceNeeded();
            if (needs != null && !needs.isEmpty()) {
                recipientTrainingDataSchema.setEmployment(needs.contains(RecipientNeed.EMPLOYMENT));
                recipientTrainingDataSchema.setMentalHealthSupport(needs.contains(RecipientNeed.MENTAL_HEALTH_SUPPORT));
                recipientTrainingDataSchema.setMeidicalCare(needs.contains(RecipientNeed.MEDICAL_CARE));
                recipientTrainingDataSchema.setLifeSkills(needs.contains(RecipientNeed.LIFE_SKILLS));
                recipientTrainingDataSchema.setElderly(needs.contains(RecipientNeed.ELDERLY));
                recipientTrainingDataSchema.setServiceConnections(needs.contains(RecipientNeed.SERVICE_CONNECTIONS));
                recipientTrainingDataSchema.setReentry(needs.contains(RecipientNeed.REENTRY));
                recipientTrainingDataSchema.setDisability(needs.contains(RecipientNeed.DISABILITY));
                recipientTrainingDataSchema.setSocialSecurity(needs.contains(RecipientNeed.SOCIAL_SECURITY));
                recipientTrainingDataSchema.setHousingProgram(needs.contains(RecipientNeed.HOUSING_PROGRAM));
            }
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
//        String filename = multipartFile.getOriginalFilename();
//        if (!(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
//            throw new Exception("Wrong file format");
//        }
//        List<RecipientExcelFlattenSchema> recipientExcelFlattenSchemas = null;
//        InputStream inputStream = multipartFile.getInputStream();
//        if (filename.endsWith(".xlsx")) {
//            recipientExcelFlattenSchemas = readXlsx(inputStream);
//        } else {
//            recipientExcelFlattenSchemas = readXls(inputStream);
//        }

        List<RecipientExcelFlattenSchema> recipientExcelFlattenSchemas = ExcelUtil.excelFileToObject(multipartFile, RecipientExcelFlattenSchema.class);
        List<Recipient> recipients =
                recipientExcelFlattenSchemas.stream().map(schema -> schema.getRecipient()).collect(Collectors.toList());
        return recipients;

    }

    private List<RecipientExcelFlattenSchema> readXls(InputStream inputStream) throws IOException {
        HSSFWorkbook sheets = new HSSFWorkbook(inputStream);

        HSSFSheet sheetAt = sheets.getSheetAt(0);
        List<RecipientExcelFlattenSchema> flattenSchemas = new ArrayList<>();
        for (int rowNum = 3; rowNum < sheetAt.getLastRowNum(); rowNum++) {
            RecipientExcelFlattenSchema recipientExcelFlattenSchema = new RecipientExcelFlattenSchema();
            HSSFRow row = sheetAt.getRow(rowNum);

            if (row != null) {
                row.getCell(0).setCellType(CellType.STRING);
                row.getCell(1).setCellType(CellType.STRING);
                row.getCell(2).setCellType(CellType.STRING);
                row.getCell(3).setCellType(CellType.STRING);
                row.getCell(4).setCellType(CellType.NUMERIC);
                row.getCell(5).setCellType(CellType.STRING);
                row.getCell(6).setCellType(CellType.STRING);
                row.getCell(7).setCellType(CellType.STRING);
                row.getCell(8).setCellType(CellType.STRING);
                row.getCell(9).setCellType(CellType.BOOLEAN);
                row.getCell(10).setCellType(CellType.BOOLEAN);
                row.getCell(11).setCellType(CellType.BOOLEAN);
                row.getCell(12).setCellType(CellType.BOOLEAN);
                row.getCell(13).setCellType(CellType.BOOLEAN);
                row.getCell(14).setCellType(CellType.BOOLEAN);
                row.getCell(15).setCellType(CellType.BOOLEAN);
                row.getCell(16).setCellType(CellType.BOOLEAN);
                row.getCell(17).setCellType(CellType.BOOLEAN);
                row.getCell(18).setCellType(CellType.BOOLEAN);
                row.getCell(19).setCellType(CellType.STRING);
                row.getCell(20).setCellType(CellType.STRING);
                row.getCell(21).setCellType(CellType.STRING);
                row.getCell(22).setCellType(CellType.STRING);
                row.getCell(23).setCellType(CellType.BOOLEAN);
                row.getCell(24).setCellType(CellType.BOOLEAN);
                row.getCell(25).setCellType(CellType.NUMERIC);
                row.getCell(26).setCellType(CellType.BOOLEAN);
                row.getCell(27).setCellType(CellType.STRING);


                recipientExcelFlattenSchema.setRecipientId(row.getCell(0).getStringCellValue());
                recipientExcelFlattenSchema.setFirstName(row.getCell(1).getStringCellValue());
                recipientExcelFlattenSchema.setLastName(row.getCell(2).getStringCellValue());
                recipientExcelFlattenSchema.setDateOfBirth(row.getCell(3).getStringCellValue());
                recipientExcelFlattenSchema.setAge((int) row.getCell(4).getNumericCellValue());
                recipientExcelFlattenSchema.setGender(Gender.valueOf(row.getCell(5).getStringCellValue()));
                recipientExcelFlattenSchema.setRace(Race.valueOf(row.getCell(6).getStringCellValue()));
                recipientExcelFlattenSchema.setPhoneNumber(row.getCell(7).getStringCellValue());
                recipientExcelFlattenSchema.setCabinNum(row.getCell(8).getStringCellValue());
                recipientExcelFlattenSchema.setEMPLOYMENT(row.getCell(9).getBooleanCellValue());
                recipientExcelFlattenSchema.setMENTAL_HEALTH_SUPPORT(row.getCell(10).getBooleanCellValue());
                recipientExcelFlattenSchema.setMEDICAL_CARE(row.getCell(11).getBooleanCellValue());
                recipientExcelFlattenSchema.setLIFE_SKILLS(row.getCell(12).getBooleanCellValue());
                recipientExcelFlattenSchema.setELDERLY(row.getCell(13).getBooleanCellValue());
                recipientExcelFlattenSchema.setSERVICE_CONNECTIONS(row.getCell(14).getBooleanCellValue());
                recipientExcelFlattenSchema.setREENTRY(row.getCell(15).getBooleanCellValue());
                recipientExcelFlattenSchema.setDISABILITY(row.getCell(16).getBooleanCellValue());
                recipientExcelFlattenSchema.setSOCIAL_SECURITY(row.getCell(17).getBooleanCellValue());
                recipientExcelFlattenSchema.setHOUSING_PROGRAM(row.getCell(18).getBooleanCellValue());
                recipientExcelFlattenSchema.setDescription(row.getCell(19).getStringCellValue());
                recipientExcelFlattenSchema.setStatus(RecipientStatus.valueOf(row.getCell(20).getStringCellValue()));
                recipientExcelFlattenSchema.setAddictedSubstance(row.getCell(21).getStringCellValue());
                recipientExcelFlattenSchema.setSubstanceUseDisorderProvider(row.getCell(22).getStringCellValue());
                recipientExcelFlattenSchema.setActiveUse(row.getCell(23).getBooleanCellValue());
                recipientExcelFlattenSchema.setInOpiateTreatment(row.getCell(24).getBooleanCellValue());
                recipientExcelFlattenSchema.setUrgent((int) row.getCell(25).getNumericCellValue());
                recipientExcelFlattenSchema.setInMentalTreatment(row.getCell(26).getBooleanCellValue());
                recipientExcelFlattenSchema.setMentalHealthProvider(row.getCell(27).getStringCellValue());
            }
            flattenSchemas.add(recipientExcelFlattenSchema);
        }
        return flattenSchemas;
    }


    private List<RecipientExcelFlattenSchema> readXlsx(InputStream inputStream) throws IOException {
        XSSFWorkbook sheets1 = new XSSFWorkbook(inputStream);
        XSSFSheet sheetAt = sheets1.getSheetAt(0);
        List<RecipientExcelFlattenSchema> flattenSchemas = new ArrayList<>();
        for (int rowNum = 3; rowNum < sheetAt.getLastRowNum(); rowNum++) {
            RecipientExcelFlattenSchema recipientExcelFlattenSchema = new RecipientExcelFlattenSchema();
            XSSFRow row = sheetAt.getRow(rowNum);

            if (row != null) {
                row.getCell(0).setCellType(CellType.STRING);
                row.getCell(1).setCellType(CellType.STRING);
                row.getCell(2).setCellType(CellType.STRING);
                row.getCell(3).setCellType(CellType.STRING);
                row.getCell(4).setCellType(CellType.NUMERIC);
                row.getCell(5).setCellType(CellType.STRING);
                row.getCell(6).setCellType(CellType.STRING);
                row.getCell(7).setCellType(CellType.STRING);
                row.getCell(8).setCellType(CellType.STRING);
                row.getCell(9).setCellType(CellType.BOOLEAN);
                row.getCell(10).setCellType(CellType.BOOLEAN);
                row.getCell(11).setCellType(CellType.BOOLEAN);
                row.getCell(12).setCellType(CellType.BOOLEAN);
                row.getCell(13).setCellType(CellType.BOOLEAN);
                row.getCell(14).setCellType(CellType.BOOLEAN);
                row.getCell(15).setCellType(CellType.BOOLEAN);
                row.getCell(16).setCellType(CellType.BOOLEAN);
                row.getCell(17).setCellType(CellType.BOOLEAN);
                row.getCell(18).setCellType(CellType.BOOLEAN);
                row.getCell(19).setCellType(CellType.STRING);
                row.getCell(20).setCellType(CellType.STRING);
                row.getCell(21).setCellType(CellType.STRING);
                row.getCell(22).setCellType(CellType.STRING);
                row.getCell(23).setCellType(CellType.BOOLEAN);
                row.getCell(24).setCellType(CellType.BOOLEAN);
                row.getCell(25).setCellType(CellType.NUMERIC);
                row.getCell(26).setCellType(CellType.BOOLEAN);
                row.getCell(27).setCellType(CellType.STRING);


                recipientExcelFlattenSchema.setRecipientId(row.getCell(0).getStringCellValue());
                recipientExcelFlattenSchema.setFirstName(row.getCell(1).getStringCellValue());
                recipientExcelFlattenSchema.setLastName(row.getCell(2).getStringCellValue());
                recipientExcelFlattenSchema.setDateOfBirth(row.getCell(3).getStringCellValue());
                recipientExcelFlattenSchema.setAge((int) row.getCell(4).getNumericCellValue());
                recipientExcelFlattenSchema.setGender(Gender.valueOf(row.getCell(5).getStringCellValue()));
                recipientExcelFlattenSchema.setRace(Race.valueOf(row.getCell(6).getStringCellValue()));
                recipientExcelFlattenSchema.setPhoneNumber(row.getCell(7).getStringCellValue());
                recipientExcelFlattenSchema.setCabinNum(row.getCell(8).getStringCellValue());
                recipientExcelFlattenSchema.setEMPLOYMENT(row.getCell(9).getBooleanCellValue());
                recipientExcelFlattenSchema.setMENTAL_HEALTH_SUPPORT(row.getCell(10).getBooleanCellValue());
                recipientExcelFlattenSchema.setMEDICAL_CARE(row.getCell(11).getBooleanCellValue());
                recipientExcelFlattenSchema.setLIFE_SKILLS(row.getCell(12).getBooleanCellValue());
                recipientExcelFlattenSchema.setELDERLY(row.getCell(13).getBooleanCellValue());
                recipientExcelFlattenSchema.setSERVICE_CONNECTIONS(row.getCell(14).getBooleanCellValue());
                recipientExcelFlattenSchema.setREENTRY(row.getCell(15).getBooleanCellValue());
                recipientExcelFlattenSchema.setDISABILITY(row.getCell(16).getBooleanCellValue());
                recipientExcelFlattenSchema.setSOCIAL_SECURITY(row.getCell(17).getBooleanCellValue());
                recipientExcelFlattenSchema.setHOUSING_PROGRAM(row.getCell(18).getBooleanCellValue());
                recipientExcelFlattenSchema.setDescription(row.getCell(19).getStringCellValue());
                recipientExcelFlattenSchema.setStatus(RecipientStatus.valueOf(row.getCell(20).getStringCellValue()));
                recipientExcelFlattenSchema.setAddictedSubstance(row.getCell(21).getStringCellValue());
                recipientExcelFlattenSchema.setSubstanceUseDisorderProvider(row.getCell(22).getStringCellValue());
                recipientExcelFlattenSchema.setActiveUse(row.getCell(23).getBooleanCellValue());
                recipientExcelFlattenSchema.setInOpiateTreatment(row.getCell(24).getBooleanCellValue());
                recipientExcelFlattenSchema.setUrgent((int) row.getCell(25).getNumericCellValue());
                recipientExcelFlattenSchema.setInMentalTreatment(row.getCell(26).getBooleanCellValue());
                recipientExcelFlattenSchema.setMentalHealthProvider(row.getCell(27).getStringCellValue());
            }
            flattenSchemas.add(recipientExcelFlattenSchema);
        }
        return flattenSchemas;
    }

}
