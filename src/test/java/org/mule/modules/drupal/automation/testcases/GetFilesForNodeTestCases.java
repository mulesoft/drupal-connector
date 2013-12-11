package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class GetFilesForNodeTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getFilesForNodeTestData");

		String title = getTestRunMessageValue("title");
		String content = getTestRunMessageValue("content");
		String type = getTestRunMessageValue("type");

		Node node = createNode(title, content, type);

		//set nodeId for tearDown
		upsertOnTestRunMessage("nodeId", node.getNid());
		
		List<File> files = getTestRunMessageValue("files");

		// TODO: Attach file to node here
		
		List<File> createdFiles = new ArrayList<File>();
		List<Integer> createdFilesIds = new ArrayList<Integer>();
		
		upsertOnTestRunMessage("createdFiles", createdFiles);
		upsertOnTestRunMessage("createdFilesIds", createdFilesIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetFilesForNode() {
		try {
			List<File> createdFiles = getTestRunMessageValue("createdFiles");
			List<Integer> createdFilesIds = getTestRunMessageValue("createdFilesIds");
			
			List<File> nodeFiles = runFlowAndGetPayload("get-files-for-node");
			assertEquals(nodeFiles.size(), createdFiles.size());
			
			for (File nodeFile : nodeFiles) {
				assertTrue(createdFilesIds.contains(nodeFile.getFid()));
			}
			
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
		
		List<Integer> createdFilesIds = getTestRunMessageValue("createdFilesIds");
		for (Integer fileId : createdFilesIds) {
			deleteFile(fileId);			
		}
	}

}
