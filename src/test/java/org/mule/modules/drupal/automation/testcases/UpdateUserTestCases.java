/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

public class UpdateUserTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("updateUserTestData");
		
		User user = getTestRunMessageValue("user");
		User createdUser = createUser(user);
		
		upsertOnTestRunMessage("createdUser", createdUser);
		upsertOnTestRunMessage("createdUserId", createdUser.getUid());
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testUpdateUser() {
		try {
			Integer createdUserId = getTestRunMessageValue("createdUserId");
			
			User newValues = getTestRunMessageValue("updatedUser");
			newValues.setUid(createdUserId);
			upsertOnTestRunMessage("userRef", newValues);
			
			User updatedUser = runFlowAndGetPayload("update-user");
			assertEquals(updatedUser.getUid(), createdUserId);
			assertEquals(updatedUser.getName(), newValues.getName());
			assertEquals(updatedUser.getMail(), newValues.getMail());
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer createdUserId = getTestRunMessageValue("createdUserId");
		deleteUser(createdUserId);
		
	}
}
