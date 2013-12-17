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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

public class IndexFilesTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("indexFilesTestData");
		
		List<File> files = getTestRunMessageValue("files");
		List<Integer> fileIds = new ArrayList<Integer>();
		for (File file : files) {
			File createdFile = createFile(file);
			fileIds.add(createdFile.getFid());
		}
		upsertOnTestRunMessage("createdFileIds", fileIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testIndexFiles() {
		try {
			List<Integer> createdFileIds = getTestRunMessageValue("createdFileIds");
			
			List<File> retrievedFiles = runFlowAndGetPayload("index-files");
			assertEquals(retrievedFiles.size(), createdFileIds.size());
			
			for (File file : retrievedFiles) {
				assertTrue(createdFileIds.contains(file.getFid()));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		List<Integer> createdFileIds = getTestRunMessageValue("createdFileIds");
		for (Integer fileId : createdFileIds) {
			deleteFile(fileId);
		}
	}

}
