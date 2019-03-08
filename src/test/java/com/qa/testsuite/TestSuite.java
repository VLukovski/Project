package com.qa.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.qa.endpointtest.AccountEndpointTest;
import com.qa.endpointtest.BodyEndpointTest;
import com.qa.repositorytest.AccountRepoTest;
import com.qa.repositorytest.BodyRepoTest;
import com.qa.repositorytest.PhysicsTest;
import com.qa.servicetest.AccountServiceTest;
import com.qa.servicetest.BodyServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AccountEndpointTest.class, BodyEndpointTest.class, AccountServiceTest.class,
		BodyServiceTest.class, AccountRepoTest.class, BodyRepoTest.class, PhysicsTest.class })

public class TestSuite {
}