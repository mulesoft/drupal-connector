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

public class UpdateCommentTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("updateCommentTestData");

        String nodeTitle = getTestRunMessageValue("title");
        String nodeContent = getTestRunMessageValue("content");
        String nodeType = getTestRunMessageValue("type");

        Node node = createNode(nodeTitle, nodeContent, nodeType);
        int nodeId = node.getNid();

        upsertOnTestRunMessage("nodeId", nodeId);

        Comment comment = getTestRunMessageValue("comment");
        Comment createdComment = createComment(comment, nodeId);
        int commentId = createdComment.getCid();

        upsertOnTestRunMessage("commentId", commentId);
    }

    @Category({RegressionTests.class})
    @Test
    public void testUpdateComment() {
        try {
            Integer commentId = getTestRunMessageValue("commentId");
            Comment toUpdate = getTestRunMessageValue("update");
            toUpdate.setCid(commentId);

            upsertOnTestRunMessage("commentRef", toUpdate);

            Integer updatedCommentId = runFlowAndGetPayload("update-comment");
            assertEquals(commentId, updatedCommentId);
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
