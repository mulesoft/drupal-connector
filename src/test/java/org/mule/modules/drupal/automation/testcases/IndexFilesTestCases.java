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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testIndexFiles() {
        try {
            List<Integer> createdFileIds = getTestRunMessageValue("createdFileIds");

            List<File> retrievedFiles = runFlowAndGetPayload("index-files");
            assertEquals(retrievedFiles.size(), createdFileIds.size());

            for (File file : retrievedFiles) {
                assertTrue(createdFileIds.contains(file.getFid()));
            }
        } catch (Exception e) {
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
