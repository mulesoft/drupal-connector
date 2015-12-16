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

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DeleteCommentTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteCommentTestData");

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

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testDeleteComment() {
        try {
            Integer commentId = getTestRunMessageValue("commentId");
            boolean result = deleteComment(commentId);
            assertTrue(result);

            // Get all comments. Check that none of them have the same commentId as the one we created/deleted
            List<Comment> comments = indexComments();
            for (Comment comment : comments) {
                assertTrue(comment.getCid() != commentId);
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
