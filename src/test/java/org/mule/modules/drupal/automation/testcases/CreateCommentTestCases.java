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
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreateCommentTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("createCommentTestData");
        String title = getTestRunMessageValue("title");
        String content = getTestRunMessageValue("content");
        String type = getTestRunMessageValue("type");
        Node node = createNode(title, content, type);

        // set nodeId for createcomment testcase
        upsertOnTestRunMessage("nid", node.getNid());
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testCreateComment() {
        try {
            Integer nodeId = getTestRunMessageValue("nid");
            String commentSubject = getTestRunMessageValue("subject");
            String commentContent = getTestRunMessageValue("und");
            Comment comment = createComment(commentSubject, commentContent, nodeId);
            assertEquals(comment.getSubject(), getTestRunMessageValue("subject"));
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }


    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("nid");
        deleteNode(nodeId);
    }


}
