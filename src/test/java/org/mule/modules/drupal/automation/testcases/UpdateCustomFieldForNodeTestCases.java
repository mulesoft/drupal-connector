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

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UpdateCustomFieldForNodeTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("updateCustomFieldForNodeTestData");
        String title = getTestRunMessageValue("title");
        String content = getTestRunMessageValue("content");
        String type = getTestRunMessageValue("type");

        Node node = createNode(title, content, type);

        // set nodeId for read testcase
        upsertOnTestRunMessage("nodeId", node.getNid());
    }

    @Category({RegressionTests.class})
    @Test
    public void testUpdateCustomFieldForNode() {
        try {
            runFlowAndGetPayload("update-custom-field-for-node");

            Integer nodeId = getTestRunMessageValue("nodeId");
            Node node = readNode(nodeId);

            List<Map> nodeCustomField = node.getBody().getUnd();

            String customFieldValue = (String) nodeCustomField.get(0).get("value");
            String customProperty = (String) getTestRunMessageValue("customproperty");

            assertEquals(customFieldValue, customProperty);
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }


    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("nodeId");
        deleteNode(nodeId);
    }
}
