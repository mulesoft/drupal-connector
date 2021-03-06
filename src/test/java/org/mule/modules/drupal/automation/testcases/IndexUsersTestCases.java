/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    @Category({SmokeTests.class, RegressionTests.class})
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
                    if (user.getUid().intValue() == userId) {
                        found = true;
                        break;
                    }
                }
                assertTrue(found);
            }
        } catch (Exception e) {
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
