package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.drupal.model.Node;

public class UpdateCustomFieldForNodeTestCases  extends DrupalTestParent {
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
	
	@Test
	public void testUpdateCustomFieldForNode() throws Exception{
		runFlowAndGetPayload("update-custom-field-for-node");
		
		Integer nodeId = getTestRunMessageValue("nodeId");
		Node node = readNode(nodeId);
		
		List<java.util.Map> nodeCustomField = node.getBody().getUnd();
		assertEquals(nodeCustomField.get(0).get("value"), getTestRunMessageValue("customproperty"));
	}
	
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}
}
