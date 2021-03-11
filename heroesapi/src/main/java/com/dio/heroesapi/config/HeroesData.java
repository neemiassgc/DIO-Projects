package com.dio.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.dio.heroesapi.constant.HeroesConstant;

public class HeroesData {

	public static void main (String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withEndpointConfiguration(
				new AwsClientBuilder.EndpointConfiguration(HeroesConstant.DYNAMODB_ENDPOINT, HeroesConstant.DYNAMODB_REGION)
			).build();

		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("heroes_table");
		Item hero = new Item()
			.withPrimaryKey("id", 1)
			.withString("name", "Hulk")
			.withString("universe", "Marvel")
			.withNumber("films", 5);

		PutItemOutcome putItemOutcome = table.putItem(hero);
	}
}
