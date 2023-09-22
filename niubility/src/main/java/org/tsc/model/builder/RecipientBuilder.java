package org.tsc.model.builder;

import org.tsc.model.Person;
import org.tsc.model.Recipient;
import org.tsc.model.RecipientNeed;

import java.util.List;

public class RecipientBuilder extends PersonBuilder{ 

    private String recipientId;
    private List<RecipientNeed> assistanceNeeded;
    private String description;
    
    private RecipientBuilder() {}
    
    public static RecipientBuilder aRecipient() {
        return new RecipientBuilder();
    }

    public RecipientBuilder withRecipientId(String recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public RecipientBuilder withAssistanceNeeded(List<RecipientNeed> assistanceNeeded) {
        this.assistanceNeeded = assistanceNeeded;
        return this;
    }

    public RecipientBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Person build() {
        Recipient recipient = new Recipient();
        buildPerson(recipient);
        recipient.setRecipientId(recipientId);
        recipient.setAssistanceNeeded(assistanceNeeded);
        recipient.setDescription(description);
        return recipient;
    }
}
