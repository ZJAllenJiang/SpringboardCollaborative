package org.tsc.model;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import org.tsc.model.builder.RecipientBuilder;

public class BuilderTest {

    @Test
    public void testBuilder() {
        Person person = RecipientBuilder.aRecipient()
                .withDescription("Test Description")
                .withFirstName("Test First Name")
                .build();
        Assert.notNull(person);
    }
}
