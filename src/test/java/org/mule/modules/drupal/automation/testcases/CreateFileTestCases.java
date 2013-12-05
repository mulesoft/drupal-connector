package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.config.DeleteFileDefinitionParser;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;
import org.mule.util.Base64;
import org.mule.util.IOUtils;

public class CreateFileTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createFileTestData");
	
		String fileName = getTestRunMessageValue("fileName");
		String filePath = getTestRunMessageValue("filePath");
		
		File file = new File();
		
		// Do not load large files here, you may run out of memory
		InputStream fileStream = getClass().getClassLoader().getResourceAsStream(filePath);
		byte[] byteArray = IOUtils.toByteArray(fileStream);
		String fileContent = Base64.encodeBytes(byteArray);
		
		file.setContent(fileContent);;
		file.setFilename(fileName);
		file.setFilesize(byteArray.length);
		
		upsertOnTestRunMessage("file", file);
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
