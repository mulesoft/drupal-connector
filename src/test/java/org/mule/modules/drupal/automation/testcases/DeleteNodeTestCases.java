/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

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
	@Category({SmokeTests.class, RegressionTests.class })
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
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
}
