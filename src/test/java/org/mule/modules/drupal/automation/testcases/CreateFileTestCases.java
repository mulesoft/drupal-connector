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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

public class CreateFileTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createFileTestData");
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testCreateFile() {
		try {
			File file = getTestRunMessageValue("file");
			File createdFile = createFile(file);
			
			assertEquals(file.getFilename(), createdFile.getFilename());
			assertEquals(file.getFilesize(), createdFile.getFilesize());
			assertEquals(file.getContent(), createdFile.getContent());
			assertTrue(file.getFid() > 0);
			
			Integer fileId = file.getFid();
			upsertOnTestRunMessage("fileId", fileId);
			
			File retrievedFile = readFile(fileId);
			assertEquals(file.getFid(), retrievedFile.getFid());
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer fileId = getTestRunMessageValue("fileId");
		deleteFile(fileId);
	}
	
}
