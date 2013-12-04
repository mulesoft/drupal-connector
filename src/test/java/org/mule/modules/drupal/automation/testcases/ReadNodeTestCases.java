package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class ReadNodeTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("readNodeTestData");
		String title = getTestRunMessageValue("title");
		String content = getTestRunMessageValue("content");
		String type = getTestRunMessageValue("type");

		Node node = createNode(title, content, type);

		// set nodeId for read testcase
		upsertOnTestRunMessage("nodeId", node.getNid());
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testReadNode() {
		try {
			Integer nodeId = getTestRunMessageValue("nodeId");
			Node node =readNode(nodeId);
			assertEquals(node.getTitle(),getTestRunMessageValue("title"));
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}
	
}
