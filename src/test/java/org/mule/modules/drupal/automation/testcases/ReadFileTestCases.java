package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

public class ReadFileTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("readFileTestData");
		
		File file = getTestRunMessageValue("file");

		File createdFile = createFile(file);
		upsertOnTestRunMessage("file", createdFile);
		upsertOnTestRunMessage("fileId", createdFile.getFid());
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testReadFile() {
		try {
			File file = getTestRunMessageValue("file");
			Integer fileId = getTestRunMessageValue("fileId");
			
			File readFile = readFile(fileId);
			assertEquals(readFile.getFid(), fileId);
			assertEquals(readFile.getFilename(), file.getFilename());
			assertEquals(readFile.getFilesize(), file.getFilesize());
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
