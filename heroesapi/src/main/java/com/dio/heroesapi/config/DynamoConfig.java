package com.dio.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.dio.heroesapi.constant.HeroesConstant;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

	@Value("$(amazon.dynamodb.endpoint)")
	private String amazonDynamoDBEndpoint;

	@Value("$(aws_access_key_id)")
	private String amazonAWSAccessKey;

	@Value("$(aws_secret_access_key)")
	private String amazonAWSSecretKey;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDBClientBuilder dynamoDBClientBuilder = AmazonDynamoDBClientBuilder.standard();

		if(!amazonDynamoDBEndpoint.isEmpty()) {
			AwsClientBuilder.EndpointConfiguration endpointConfiguration
				= new AwsClientBuilder.EndpointConfiguration(HeroesConstant.DYNAMODB_ENDPOINT, HeroesConstant.DYNAMODB_REGION);
			dynamoDBClientBuilder.withEndpointConfiguration(endpointConfiguration);
		}

		return dynamoDBClientBuilder.build();
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}
}
