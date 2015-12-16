/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer fileId = getTestRunMessageValue("fileId");
        deleteFile(fileId);
    }

}
