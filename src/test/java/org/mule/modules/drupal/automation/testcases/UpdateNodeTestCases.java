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
import org.mule.modules.drupal.model.Node;

import static org.junit.Assert.assertEquals;

public class UpdateNodeTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("updateNodeTestData");
        String title = getTestRunMessageValue("title");
        String content = getTestRunMessageValue("content");
        String type = getTestRunMessageValue("type");

        Node node = createNode(title, content, type);

        // set nodeId for delete testcase
        upsertOnTestRunMessage("nodeId", node.getNid());
    }

    @Category({RegressionTests.class})
    @Test
    public void testUpdateNode() throws Exception {
        String titleForUpdate = getTestRunMessageValue("titleForUpdate");

        Node node = runFlowAndGetPayload("update-node");

        Node updatedNode = readNode(node.getNid());

        assertEquals(updatedNode.getNid(), node.getNid());
        assertEquals(titleForUpdate, updatedNode.getTitle());
    }


    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("nodeId");
        deleteNode(nodeId);
    }

}
