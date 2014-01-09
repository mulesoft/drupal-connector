/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class CreateNodeTestCases extends DrupalTestParent {

	@SuppressWarnings("unchecked")
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testCreateNode() {
		try {
			initializeTestRunMessage("createNodeTestData");
			String title = getTestRunMessageValue("title");
			String content = getTestRunMessageValue("content");
			String type = getTestRunMessageValue("type");

			Node node = createNode(title, content, type);

			//set nodeId for tearDown
			upsertOnTestRunMessage("nodeId", node.getNid());
			
			assertNotNull(node.getNid());
			assertEquals(node.getTitle(), title);
			
			List<java.util.Map> nodeCustomField = node.getBody().getUnd();
			//get the first map from the list and get the value element from it.
			assertEquals(nodeCustomField.get(0).get("value"), content);
		
		} catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = Integer.parseInt(getTestRunMessageValue("nodeId").toString()); 
		deleteNode(nodeId);
	}

}
