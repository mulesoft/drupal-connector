package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.drupal.client.DrupalException;
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
			deleteFile(fileId);

			// Show throw an exception
			File retrievedFile = readFile(fileId);
			
			fail("An exception should have been thrown when reading the file. "
					+ "The file was read successfully, when it should have been deleted."
					+ "ID of the deleted file: "+retrievedFile.getFid());
		}
		catch (MessagingException e) {
			if (e.getCause() instanceof DrupalException) {
				DrupalException cause = (DrupalException) e.getCause();
				/* 
				 * File has not been found so an exception has been thrown
				 * Status code returned is HTTP 406 (Not Acceptable)
				 * Response entity in JSON would look as follows:
				 *			["There is no file with ID 5"]
				 */
				assertTrue(cause.getMessage().contains("406") && cause.getMessage().contains("Not Acceptable"));
			}
			else fail(ConnectorTestUtils.getStackTrace(e));
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}

}
