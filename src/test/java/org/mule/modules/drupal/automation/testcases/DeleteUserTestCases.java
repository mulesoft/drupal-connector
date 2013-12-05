package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.drupal.client.DrupalException;
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
			deleteUser(userId);
			
			// Should throw an exception
			User retrievedUser = readUser(userId);

			fail("An exception should have been thrown when retrieving the user. "
					+ "The user was found, when they should have been deleted."
					+ "ID of the deleted user: "+retrievedUser.getUid());
		}
		catch (MessagingException e) {
			if (e.getCause() instanceof DrupalException) {
				DrupalException cause = (DrupalException) e.getCause();
				assertTrue(cause.getMessage().contains("406") && cause.getMessage().contains("Not Acceptable"));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
}