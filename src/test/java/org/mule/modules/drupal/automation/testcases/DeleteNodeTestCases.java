package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
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
	@Test
	public void testDeleteNode() {
		try {
			Integer nodeId = Integer.parseInt(getTestRunMessageValue("nodeId")
					.toString());
			deleteNode(nodeId);
			List<Node> nodes = indexNodes();
			for (Node curNode : nodes) {
				if (curNode.getNid() == nodeId) {
					fail("Node still present after delete");
				}
			}
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
}
