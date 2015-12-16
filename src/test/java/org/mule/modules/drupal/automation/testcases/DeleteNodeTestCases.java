/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DeleteNodeTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteNodeTestData");
        String title = getTestRunMessageValue("title");
        String content = getTestRunMessageValue("content");
        String type = getTestRunMessageValue("type");

        Node node = createNode(title, content, type);

        // set nodeId for delete testcase
        upsertOnTestRunMessage("nodeId", node.getNid());
    }

    @SuppressWarnings("unchecked")
    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testDeleteNode() {
        try {
            Integer nodeId = Integer.parseInt(getTestRunMessageValue("nodeId").toString());
            boolean result = deleteNode(nodeId);
            assertTrue(result);

            List<Node> nodes = indexNodes();
            for (Node node : nodes) {
                assertTrue(node.getNid() != nodeId);
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }
}
