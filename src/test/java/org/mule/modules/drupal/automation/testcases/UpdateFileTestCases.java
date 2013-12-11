package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

public class UpdateFileTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("updateFileTestData");

		File file = getTestRunMessageValue("file");
		
		file = createFile(file);
		upsertOnTestRunMessage("file", file);
		upsertOnTestRunMessage("fileId", file.getFid());
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testUpdateFile() {
		try {
			File createdFile = getTestRunMessageValue("file");
			String newFileName = getTestRunMessageValue("newFileName");
			Integer fileId = getTestRunMessageValue("fileId");
			
			createdFile.setFilename(newFileName);

			upsertOnTestRunMessage("fileRef", createdFile);
			
			File updatedFile = runFlowAndGetPayload("update-file");
			assertEquals(updatedFile, fileId);
			assertEquals(updatedFile.getFilename(), newFileName);
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
