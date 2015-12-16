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
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GetFilesForNodeTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("getFilesForNodeTestData");

        String fieldName = getTestRunMessageValue("fieldName");

        Node node = getTestRunMessageValue("node");
        Node createdNode = createNode(node);
        Integer nodeId = createdNode.getNid();

        upsertOnTestRunMessage("nodeId", nodeId);

        List<String> filePaths = getTestRunMessageValue("filePaths");
        List<File> files = new ArrayList<File>();

        for (String filePath : filePaths) {
            URI fileUri = getClass().getClassLoader().getResource(filePath).toURI();
            File file = new File(fileUri);
            files.add(file);
        }

        List<org.mule.modules.drupal.model.File> attachedFiles = attachFilesToNode(nodeId, files, fieldName);
        upsertOnTestRunMessage("attachedFiles", attachedFiles);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testGetFilesForNode() {
        try {
            Integer nodeId = getTestRunMessageValue("nodeId");

            List<org.mule.modules.drupal.model.File> attachedFiles = getTestRunMessageValue("attachedFiles");
            List<org.mule.modules.drupal.model.File> nodeFiles = getFilesForNode(nodeId);

            assertEquals(nodeFiles.size(), attachedFiles.size());
            for (org.mule.modules.drupal.model.File nodeFile : nodeFiles) {
                boolean found = false;
                for (org.mule.modules.drupal.model.File attachedFile : attachedFiles) {
                    if (attachedFile.getFid() == nodeFile.getFid()) {
                        found = true;
                        break;
                    }
                }
                assertTrue(found);
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("nodeId");
        deleteNode(nodeId);
    }

}
