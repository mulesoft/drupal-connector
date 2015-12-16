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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer createdUserId = getTestRunMessageValue("createdUserId");
        deleteUser(createdUserId);

    }
}
