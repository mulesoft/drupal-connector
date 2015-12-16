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
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;

public class ReadUserTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("readUserTestData");

        User user = getTestRunMessageValue("user");
        User createdUser = createUser(user);

        upsertOnTestRunMessage("user", createdUser);
        upsertOnTestRunMessage("userId", createdUser.getUid());
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testReadUser() {
        try {
            User user = getTestRunMessageValue("user");
            Integer userId = getTestRunMessageValue("userId");

            User retrievedUser = readUser(userId);
            assertEquals(userId, retrievedUser.getUid());
            assertEquals(user.getName(), retrievedUser.getName());
            assertEquals(user.getMail(), retrievedUser.getMail());
            assertTrue(StringUtils.isEmpty(retrievedUser.getPassword()));
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
