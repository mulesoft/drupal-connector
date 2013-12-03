package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.drupal.model.Node;

public class UpdateNodeTestCase extends DrupalTestParent {

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
	
	@Test
	public void testUpdateNode() throws Exception{
		Node node =runFlowAndGetPayload("update-node");
		assertEquals(getTestRunMessageValue("titleForUpdate"),node.getTitle());
	}
	
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}
	
}
