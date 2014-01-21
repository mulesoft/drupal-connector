/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;

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
	public void testUpdateNode() throws Exception{
		String titleForUpdate = getTestRunMessageValue("titleForUpdate");
		
		Node node = runFlowAndGetPayload("update-node");

		Node updatedNode = readNode(node.getNid());
		
		assertEquals(updatedNode.getNid(), node.getNid());
		assertEquals(titleForUpdate, updatedNode.getTitle());
	}
	
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}
	
}
