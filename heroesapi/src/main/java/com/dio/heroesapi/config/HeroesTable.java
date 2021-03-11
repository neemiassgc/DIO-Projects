package com.dio.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.dio.heroesapi.constant.HeroesConstant;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {

	public static void main (String[] args) {
		AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
			.withEndpointConfiguration(
				new AwsClientBuilder.EndpointConfiguration(HeroesConstant.DYNAMODB_ENDPOINT, HeroesConstant.DYNAMODB_REGION)
			).build();

		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDBClient);

		String tableName = "heroes_table";

		try {
			Table table = dynamoDB.createTable(
				tableName,
				List.of(new KeySchemaElement("id", KeyType.HASH)),
				List.of(new AttributeDefinition("id", ScalarAttributeType.S)),
				new ProvisionedThroughput()
			);

			table.waitForActive();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
