/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

public class DeleteUserTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteUserTestData");
		
		User user = getTestRunMessageValue("user");
		User createdUser = createUser(user);
		
		upsertOnTestRunMessage("user", createdUser);
		upsertOnTestRunMessage("userId", createdUser.getUid());
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testDeleteUser() {
		try {
			Integer userId = getTestRunMessageValue("userId");
			boolean result = deleteUser(userId);
			assertTrue(result);
			
			List<User> users = indexUsers();
			for (User user : users) {
				assertTrue(user.getUid() != userId);
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
}
