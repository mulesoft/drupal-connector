/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}
