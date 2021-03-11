package com.dio.heroesapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@DynamoDBTable(tableName = "heroes_table")
public class Heroes {

	@Id
	@DynamoDBHashKey(attributeName = "id")
	private final String id;

	@DynamoDBAttribute(attributeName = "name")
	private final String name;

	@DynamoDBAttribute(attributeName = "universe")
	private final String universe;

	@DynamoDBAttribute(attributeName = "films")
	private final int films;
}
