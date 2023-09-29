package com.enslipchaussettes.api.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {
	private static TestPostgreSQLContainer container;

	private TestPostgreSQLContainer() {
		super("postgres:16");
	}
	
	public static TestPostgreSQLContainer getInstance() {	
		if (container == null) {
			container = new TestPostgreSQLContainer();
		}
		return container;
	}
	
	
}
