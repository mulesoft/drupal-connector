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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

public class IndexUsersTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("indexUsersTestData");
		
		List<User> users = getTestRunMessageValue("users");
		List<Integer> userIds = new ArrayList<Integer>();
		for (User user : users) {
			User createdUser = createUser(user);
			userIds.add(createdUser.getUid());
		}
		upsertOnTestRunMessage("userIds", userIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testIndexUsers() {
		try {
			List<Integer> userIds = getTestRunMessageValue("userIds");
			List<User> retrievedUsers = runFlowAndGetPayload("index-users");
			
			/*
			 * Retrieved users is always going to be equal to or larger than the amount of users
			 * that were created in the @Before method. There has to be at least a user already
			 * present with which you have to login with.
			 */
			assertTrue(retrievedUsers.size() >= userIds.size());

			/*
			 * Assert that the created users in the @Before method are retrieved by the API request.
			 */
			for (Integer userId : userIds) {
				boolean found = false;
				for (User user : retrievedUsers) {
					if (user.getUid() == userId) {
						found = true;
						break;
					}
				}
				assertTrue(found);
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		List<Integer> userIds = getTestRunMessageValue("userIds");
		for (Integer userId : userIds) {
			deleteUser(userId);
		}
	}
	
}
