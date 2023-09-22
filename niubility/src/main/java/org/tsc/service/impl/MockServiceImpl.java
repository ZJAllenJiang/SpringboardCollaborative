package org.tsc.service.impl;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.tsc.model.*;
import org.tsc.service.MockService;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Primary
public class MockServiceImpl implements MockService {


    @Override
    public List<Navigator> getNavigators() {
        Navigator navigator = new Navigator();
        navigator.setNavigatorId(UUID.randomUUID().toString());
        navigator.setFirstName("Mocked First Name");
        navigator.setLastName("Mocked Last Name");
        navigator.setAge(20);
        navigator.setDateOfBirth(DateFormat.getDateInstance().format(System.currentTimeMillis()));
        navigator.setRace(Race.BLACK);
        navigator.setPhoneNumber("123456");
        navigator.setGender(Gender.FEMALE);
        return List.of(navigator);
    }

    @Override
    public List<Recipient> getReceipients() {
        List<Recipient> recipients = new ArrayList<>();
        Recipient mentalAbnormalRecipient = new Recipient();
        mentalAbnormalRecipient.setInMentalTreatment(true);
        mentalAbnormalRecipient.setFirstName("Mocked First Name");
        mentalAbnormalRecipient.setLastName("Mocked Last Name");
        mentalAbnormalRecipient.setAge(20);
        mentalAbnormalRecipient.setDateOfBirth(DateFormat.getDateInstance().format(System.currentTimeMillis()));
        mentalAbnormalRecipient.setRace(Race.BLACK);
        mentalAbnormalRecipient.setPhoneNumber("123456");
        mentalAbnormalRecipient.setGender(Gender.FEMALE);
        mentalAbnormalRecipient.setDescription("I have a bad headache");
        mentalAbnormalRecipient.setAssistanceNeeded(List.of(RecipientNeed.MEDICAL_CARE));
        mentalAbnormalRecipient.setRecipientId(UUID.randomUUID().toString());

        Recipient opiateAddictedRecipient = new Recipient();
        opiateAddictedRecipient.setActiveUse(true);
        opiateAddictedRecipient.setAddictedSubstance("Cocaine");

        OpiateHistory opiateHistory = new OpiateHistory();
        opiateHistory.setSubstance("cocaine");
        opiateHistory.setStartDate(LocalDate.now());
        opiateHistory.setEndDate(LocalDate.now());
        opiateAddictedRecipient.setOpiateHistoryList(List.of(opiateHistory));
        opiateAddictedRecipient.setStatus(RecipientStatus.RECOVERED);
        opiateAddictedRecipient.setInOpiateTreatment(true);
        opiateAddictedRecipient.setFirstName("Mocked First Name");
        opiateAddictedRecipient.setLastName("Mocked Last Name");
        opiateAddictedRecipient.setAge(20);
        opiateAddictedRecipient.setDateOfBirth(DateFormat.getDateInstance().format(System.currentTimeMillis()));
        opiateAddictedRecipient.setRace(Race.BLACK);
        opiateAddictedRecipient.setPhoneNumber("123456");
        opiateAddictedRecipient.setGender(Gender.FEMALE);
        opiateAddictedRecipient.setDescription("I addict for lots of years");
        return List.of(mentalAbnormalRecipient, opiateAddictedRecipient);
    }

    @Override
    public List<Organization> getOrganizations() {
        Organization organization = new Organization();
        organization.setOrgId(UUID.randomUUID().toString());
        organization.setOrgName("TSC");
        organization.setAddress("Telahuad");
        organization.setContactNumber("1234235");
        organization.setType("Organization");
        organization.setResponsibility("hospital");
        organization.setDescription("i' m a hospital");
        return List.of(organization);
    }

    @Override
    public List<TscTask> getTasks() {
        TscAdministrator tscAdministrator = new TscAdministrator();
        tscAdministrator.setAdminId(UUID.randomUUID().toString());
        tscAdministrator.setFirstName("Mocked First Name");
        tscAdministrator.setLastName("Mocked Last Name");
        tscAdministrator.setAge(20);
        tscAdministrator.setDateOfBirth(DateFormat.getDateInstance().format(System.currentTimeMillis()));
        tscAdministrator.setRace(Race.BLACK);
        tscAdministrator.setPhoneNumber("123456");
        tscAdministrator.setGender(Gender.FEMALE);
        Navigator navigator = getNavigators().get(0);

        TscTask tscTask = new TscTask();
        tscTask.setTaskId(UUID.randomUUID().toString());
        tscTask.setTscAdministratorId(tscAdministrator.getAdminId());
        tscTask.setNavigatorId(navigator.getNavigatorId());
        tscTask.setTaskProgressEnum(TaskProgressEnum.IN_PROGRESS);
        return List.of(tscTask);
    }


}
