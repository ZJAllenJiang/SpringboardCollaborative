package org.tsc.schema;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tsc.model.Gender;

@Builder
@Getter
@Setter
public class RecipientTrainingDataSchema {
    private String name;
    private Gender sex;
    private  int urgent;
    private boolean employment;
    private  boolean mentalHealthSupport;
    private boolean meidicalCare;
    private boolean lifeSkills;
    private boolean elderly;
    private  boolean serviceConnections;
    private boolean reentry;
    private boolean disablity;
    private boolean socialSecurity;
    private boolean housingProgram;
}
