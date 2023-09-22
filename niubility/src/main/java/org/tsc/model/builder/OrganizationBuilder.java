package org.tsc.model.builder;

import org.tsc.model.Organization;

import java.util.UUID;

public class OrganizationBuilder {

    private String orgId = UUID.randomUUID().toString();
    private String orgName;
    private String type;
    private String contactNumber;
    private String address;
    private String responsibility;
    private String description;

    private OrganizationBuilder() {}

    public static OrganizationBuilder aOrg() {
        return new OrganizationBuilder();
    }

    public OrganizationBuilder withOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public OrganizationBuilder withOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public OrganizationBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public OrganizationBuilder withContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public OrganizationBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public OrganizationBuilder withResponsibility(String responsibility) {
        this.responsibility = responsibility;
        return this;
    }

    public OrganizationBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Organization build() {
        Organization organization = new Organization();
        organization.setOrgId(orgId);
        organization.setOrgName(orgName);
        organization.setType(type);
        organization.setContactNumber(contactNumber);
        organization.setAddress(address);
        organization.setResponsibility(responsibility);
        organization.setDescription(description);
        return organization;
    }
}
