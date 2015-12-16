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
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IndexNodesTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("indexNodesTestData");

        List<Node> nodes = getTestRunMessageValue("nodes");

        List<Node> createdNodes = new ArrayList<Node>();
        List<Integer> createdNodeIds = new ArrayList<Integer>();

        for (Node node : nodes) {
            Node createdNode = createNode(node);
            createdNodes.add(createdNode);
            createdNodeIds.add(createdNode.getNid());
        }

        upsertOnTestRunMessage("createdNodes", createdNodes);
        upsertOnTestRunMessage("createdNodeIds", createdNodeIds);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testIndexNodes() {
        try {
            List<Integer> createdNodeIds = getTestRunMessageValue("createdNodeIds");

            List<Node> retrievedNodes = runFlowAndGetPayload("index-nodes");

            assertEquals(createdNodeIds.size(), retrievedNodes.size());
            for (Node retrievedNode : retrievedNodes) {
                assertTrue(createdNodeIds.contains(retrievedNode.getNid()));
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        List<Integer> createdNodeIds = getTestRunMessageValue("createdNodeIds");
        for (Integer nodeId : createdNodeIds) {
            deleteNode(nodeId);
        }
    }

}
