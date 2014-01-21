/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

public class RegisterUserTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("registerUserTestData");
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testRegisterUser() {
		try {
			User user = getTestRunMessageValue("userRef");
			User createdUser = runFlowAndGetPayload("register-user");
			
			assertTrue(createdUser.getUid() >= 0);
			assertEquals(createdUser.getName(), user.getName());
			assertEquals(createdUser.getMail(), user.getMail());

			upsertOnTestRunMessage("userId", createdUser.getUid());
			
			User retrievedUser = readUser(createdUser.getUid());
			assertNotNull(retrievedUser);
			assertEquals(retrievedUser.getUid(), createdUser.getUid());
			assertEquals(retrievedUser.getName(), createdUser.getName());
			assertEquals(retrievedUser.getMail(), createdUser.getMail());
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer userId = getTestRunMessageValue("userId");
		deleteUser(userId);
	}

}
