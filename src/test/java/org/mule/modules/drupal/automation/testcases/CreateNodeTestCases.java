/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class CreateNodeTestCases extends DrupalTestParent {

    @SuppressWarnings("unchecked")
    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testCreateNode() {
        try {
            initializeTestRunMessage("createNodeTestData");
            String title = getTestRunMessageValue("title");
            String content = getTestRunMessageValue("content");
            String type = getTestRunMessageValue("type");

            Node node = createNode(title, content, type);

            //set nodeId for tearDown
            upsertOnTestRunMessage("nodeId", node.getNid());

            assertNotNull(node.getNid());
            assertEquals(node.getTitle(), title);

            List<java.util.Map> nodeCustomField = node.getBody().getUnd();
            //get the first map from the list and get the value element from it.
            assertEquals(nodeCustomField.get(0).get("value"), content);

        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer nodeId = Integer.parseInt(getTestRunMessageValue("nodeId").toString());
        deleteNode(nodeId);
    }

}
