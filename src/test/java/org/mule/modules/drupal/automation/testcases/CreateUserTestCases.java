package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

public class CreateUserTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createUserTestData");
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testCreateUser() {
		try {
			User user = getTestRunMessageValue("user");
			User createdUser = createUser(user);
			
			Integer userId = createdUser.getUid();
			upsertOnTestRunMessage("userId", userId);

			assertTrue(userId > -1);
			assertEquals(createdUser.getName(), user.getName());
			assertEquals(createdUser.getMail(), user.getMail());
			assertEquals(createdUser.getPassword(), user.getPassword());
			
			User retrievedUser = readUser(userId);
			assertTrue(retrievedUser != null);
			assertEquals(retrievedUser.getUid(), userId);
			assertEquals(createdUser.getName(), retrievedUser.getName());
			assertEquals(createdUser.getMail(), retrievedUser.getMail());
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
