package org.tsc;

import io.awspring.cloud.dynamodb.DynamoDbOperations;
import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tsc.config.CustomDynamoDbTableNameResolver;
import org.tsc.model.Organization;
import org.tsc.model.*;
import org.tsc.model.builder.OrganizationBuilder;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NiubilityApplication {

	Region region = Region.US_EAST_1;

	DynamoDbClient ddb = DynamoDbClient.builder()
			// .credentialsProvider(credentialsProvider)
			.region(region)
			.build();

	public static void main(String[] args) {
		SpringApplication.run(NiubilityApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(DynamoDbOperations dynamoDbOperations,
										DynamoDbEnhancedClient dynamoDbEnhancedClient) {
		return args -> {
			ListTablesResponse response = null;
			ListTablesRequest request = ListTablesRequest.builder().build();
			response = ddb.listTables(request);
			List<String> tableNames = response.tableNames();

			if (!tableNames.contains("RECIPIENT")) {
				dynamoDbEnhancedClient.table("RECIPIENT", TableSchema.fromBean(Recipient.class)).createTable();
			}

			if (!tableNames.contains("ORGANIZATION")) {
				dynamoDbEnhancedClient.table("ORGANIZATION", TableSchema.fromBean(Organization.class)).createTable();
			}

			if(!tableNames.contains("NAVIGATOR")) {
				dynamoDbEnhancedClient.table("NAVIGATOR", TableSchema.fromBean(Navigator.class)).createTable();
			}

			if(!tableNames.contains("TSC_ADMINISTRATOR")) {
				dynamoDbEnhancedClient.table("TSC_ADMINISTRATOR", TableSchema.fromBean(TscAdministrator.class)).createTable();
			}

			if(!tableNames.contains("TSC_TASK")) {
				dynamoDbEnhancedClient.table("TSC_TASK", TableSchema.fromBean(TscTask.class)).createTable();
			}


			initOrganizations().forEach(dynamoDbOperations::save);
		};
	}

	@Bean
	public DynamoDbTableNameResolver dynamoDbTableNameResolver() {
		return new CustomDynamoDbTableNameResolver();
	}


	private List<Organization> initOrganizations() {
		Organization fscaa = OrganizationBuilder.aOrg()
				.withOrgName("FSCAA")
				.withResponsibility("wrap-around case management")
				.withDescription("connects individuals to resources and organizations that help them," +
						"including helping them apply for government assistance programs.")
				.build();
		Organization laRedHealthCenter = OrganizationBuilder.aOrg()
				.withOrgName("La Red Health Center")
				.withResponsibility("medical, dental, and mental health counseling")
				.build();
		Organization marigold = OrganizationBuilder.aOrg()
				.withOrgName("Marigold")
				.withResponsibility("online anonymous peer support")
				.withDescription("connecting them with peers who have gone through or are going through similar struggles" +
						"so they know they are not alone and can receive judgement-free support")
				.build();
		Organization netCenters = OrganizationBuilder.aOrg()
				.withOrgName("NET Centers")
				.withResponsibility("Medical, Food Stamps, Free phones")
				.build();
		Organization bvs = OrganizationBuilder.aOrg()
				.withOrgName("Brandywine Valley SPCA")
				.withResponsibility("Veterinary care")
				.build();
		Organization bay = OrganizationBuilder.aOrg()
				.withOrgName("Bone and Yarn")
				.withResponsibility("Pet food")
				.build();
		Organization dcu = OrganizationBuilder.aOrg()
				.withOrgName("Del-One Credit Union")
				.withResponsibility("savings accounts, credit counseling services")
				.build();
		Organization joyfullyConnectedFoundation = OrganizationBuilder.aOrg()
				.withOrgName("Joyfully Connected Foundation")
				.withDescription("helps people who have been isolated connect with others, " +
						"make friends and find social support")
				.build();
		Organization theDsamhBridgeClinic = OrganizationBuilder.aOrg()
				.withOrgName("The DSAMH Bridge Clinic")
				.withDescription("substance abuse and mental health clinic")
				.build();
		Organization promise = OrganizationBuilder.aOrg()
				.withOrgName("PROMISE")
				.withDescription("mental health services")
				.build();
		Organization actTeam = OrganizationBuilder.aOrg()
				.withOrgName("ACT Team")
				.withDescription("holistic recovery services (physical health, substance abuse, mental health, etc.")
				.build();
		Organization bccs = OrganizationBuilder.aOrg()
				.withOrgName("Brandywine Counseling and Community Services")
				.withDescription("addiction treatment programs")
				.build();
		Organization georgetownPolice = OrganizationBuilder.aOrg()
				.withOrgName("Georgetown Police")
				.build();

		return Arrays.asList(fscaa, laRedHealthCenter, marigold, netCenters, bvs, bay, dcu, joyfullyConnectedFoundation,
				theDsamhBridgeClinic, promise, actTeam, bccs, georgetownPolice);

	}
}
