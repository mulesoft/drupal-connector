package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
			deleteNode(nodeId);
			
			Node node = readNode(nodeId);
			fail("Node still present after delete. ID of the node still present is: "+ node.getNid());
		} 
		catch (MessagingException e) {
			if (e.getCause() instanceof DrupalException) {
				DrupalException cause = (DrupalException) e.getCause();
				assertTrue(cause.getMessage().contains("404") && cause.getMessage().contains("Not Found"));
			}
			else fail(ConnectorTestUtils.getStackTrace(e));
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
}
