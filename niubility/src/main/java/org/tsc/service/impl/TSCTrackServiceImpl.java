package org.tsc.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsc.model.Recipient;
import org.tsc.model.RecipientStatus;
import org.tsc.service.CrudService;
import org.tsc.service.TSCTrackService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TSCTrackServiceImpl implements TSCTrackService {

    @Autowired
    private CrudService crudService;

    @Override
    public Map<String, String> opiateStatistics() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        String opiateUsageHistory = recipients.stream().map(r -> {
            String joined = StringUtils.join(r.getOpiateHistoryList(), ",");
            return r.getFirstName() + " " + r.getLastName() + " " + joined;
        }).collect(Collectors.joining("|"));
        long numOfActivelyUse = recipients.stream().filter(Recipient::isActiveUse).count();
        long numOfRecovery = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.RECOVERED)).count();
        long numOfInOpiateTreatment = recipients.stream().filter(Recipient::isInOpiateTreatment).count();
        long numOfSubstanceTreatment = recipients.stream().filter(r ->
                        r.isInOpiateTreatment() && StringUtils.isNotBlank(r.getAddictedSubstance()))
                .count();
        long numOfInMentalTreatment = recipients.stream().filter(Recipient::isInMentalTreatment).count();
        long numOfDualTreatment = numOfInOpiateTreatment + numOfInMentalTreatment;

        Map<String, String> map = new HashMap<>();
        map.put("History of opiate usage during their lifetime", opiateUsageHistory);
        map.put("# of people actively using opiates", String.valueOf(numOfActivelyUse));
        map.put("# of people in recovery from opiate usage", String.valueOf(numOfRecovery));
        map.put("# of people in treatment for opiate usage", String.valueOf(numOfInOpiateTreatment));
        map.put("# of people in treatment for usage of substances other than opiate", String.valueOf(numOfSubstanceTreatment));
        map.put("# of people in mental health treatment", String.valueOf(numOfInMentalTreatment));
        map.put("# of people receiving treatment for Dual Diagnosis", String.valueOf(numOfDualTreatment));

        return map;
    }

    @Override
    public List<Map<String, String>> caseManagementStatistics() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        List<Map<String, String>> list = recipients.stream().map(r -> {
            Map<String, String> map = new HashMap<>();
            map.put("Cabin #", r.getCabinNum());
            map.put("First Name", r.getFirstName());
            map.put("Last Name", r.getLastName());
            map.put("Mental Health Provider", r.getMentalHealthProvider());
            map.put("Substance Use Disorder Provider", r.getSubstanceUseDisorderProvider());
            map.put("Case Management Status", r.getStatus().toString());
            map.put("Need", StringUtils.join(r.getAssistanceNeeded(), ","));
            map.put("Phone #", r.getPhoneNumber());
            return map;
        }).collect(Collectors.toList());

        return list;
    }

    @Override
    public Map<String, String> programStatistics() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        long numFoundPermanentHousing = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.FOUND_PERMANENT_HOUSING)).count();
        long numIncarcerated = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.INCARCERATED)).count();
        long numEntrusted = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.ENTRUSTED_TO_ASSERTIVE_COMMUNITY)).count();
        long numPassedAway = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.PASSED_AWAY)).count();
        long numLongTerm = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.LONG_TERM_SUBSTANCE_TREATMENT)).count();
        long numMoved = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.MOVEMENT)).count();
        long numExited = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.EXIT_FOR_RULE_VIOLATIONS)).count();
        long numConnectedToMedical = recipients.stream().filter(r -> r.getStatus().equals(RecipientStatus.CONNECTED_TO_MEDICAL_PROVIDERS)).count();

        Map<String, String> map = new HashMap<>();
        map.put("# of people served", String.valueOf(recipients.size()));
        map.put("# of people found permanent housing", String.valueOf(numFoundPermanentHousing));
        map.put("# of people incarcerated", String.valueOf(numIncarcerated));
        map.put("# of people entrusted to Assertive Community Treatment Teams", String.valueOf(numEntrusted));
        map.put("# of people passed away", String.valueOf(numPassedAway));
        map.put("# of people went to longer-term treatment", String.valueOf(numLongTerm));
        map.put("# of people moved", String.valueOf(numMoved));
        map.put("# of people exited from program for violation", String.valueOf(numExited));
        map.put("# of people connected to medical providers", String.valueOf(numConnectedToMedical));

        return map;
    }

    @Override
    public Map<String, String> demographicStatistics() {
        List<Recipient> recipients = crudService.getAll(Recipient.class);

        String genderBreakdown = recipients.stream().collect(Collectors.groupingBy(Recipient::getGender)).toString();
        String racialBreakdown = recipients.stream().collect(Collectors.groupingBy(Recipient::getRace)).toString();
        String ageBreakdown = recipients.stream().collect(Collectors.groupingBy(Recipient::getAge)).toString();

        Map<String, String> map = new HashMap<>();
        map.put("Gender breakdown", genderBreakdown);
        map.put("Racial breakdown", racialBreakdown);
        map.put("Age breakdown", ageBreakdown);

        return map;
    }
}
