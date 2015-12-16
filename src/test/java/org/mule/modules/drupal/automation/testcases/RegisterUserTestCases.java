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

import static org.junit.Assert.*;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer userId = getTestRunMessageValue("userId");
        deleteUser(userId);
    }

}
