/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

public class DeleteFileTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteFileTestData");

		File file = getTestRunMessageValue("file");
		
		File createdFile = createFile(file);
		upsertOnTestRunMessage("fileId", createdFile.getFid());
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testDeleteFile() {
		try {
			Integer fileId = getTestRunMessageValue("fileId");
			boolean result = deleteFile(fileId);
			assertTrue(result);
			
			// Get all files. Check that none of them have the same fileId as the one we created/deleted
			List<File> files = indexFiles();
			for (File file : files) {
				assertTrue(file.getFid() != fileId);
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}

}
