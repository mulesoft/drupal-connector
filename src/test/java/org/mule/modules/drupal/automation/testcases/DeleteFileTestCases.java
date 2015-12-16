/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.File;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}
