/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class AttachFilesToNodeTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("attachFilesToNodeTestData");
		
		Node node = getTestRunMessageValue("node");
		Node createdNode = createNode(node);
		Integer nodeId = createdNode.getNid();
		
		upsertOnTestRunMessage("nodeId", nodeId);
		
		List<String> filePaths = getTestRunMessageValue("filePaths");
		List<File> files = new ArrayList<File>();
		
		for (String filePath : filePaths) {
			URI fileUri = getClass().getClassLoader().getResource(filePath).toURI();
			File file = new File(fileUri);
			files.add(file);
		}
		
		upsertOnTestRunMessage("files", files);		
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testAttachFilesToNode() {
		try {
			Integer nodeId = getTestRunMessageValue("nodeId");
			List<File> files = getTestRunMessageValue("files");
			String fieldName = getTestRunMessageValue("fieldName");
			boolean attach = Boolean.valueOf((String)getTestRunMessageValue("attach"));

			List<org.mule.modules.drupal.model.File> attachedFiles = attachFilesToNode(nodeId, files, fieldName, attach);
			upsertOnTestRunMessage("attachedFiles", attachedFiles);
			
			if (attach) {
				assertTrue(attachedFiles.size() >= files.size());
			}
			else assertEquals(attachedFiles.size(), files.size());
			
			List<org.mule.modules.drupal.model.File> nodeFiles = getFilesForNode(nodeId);
			assertEquals(nodeFiles.size(), attachedFiles.size());
			for (org.mule.modules.drupal.model.File nodeFile : nodeFiles) {
				boolean found = false;
				for(org.mule.modules.drupal.model.File attachedFile : attachedFiles) {
					if (attachedFile.getFid() == nodeFile.getFid()) {
						found = true;
						break;
					}
				}
				assertTrue(found);
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
	}

}
