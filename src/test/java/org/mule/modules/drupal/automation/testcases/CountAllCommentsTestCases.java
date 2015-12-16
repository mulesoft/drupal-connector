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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CountAllCommentsTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("countAllCommentsTestData");

        String nodeTitle = getTestRunMessageValue("title");
        String nodeContent = getTestRunMessageValue("content");
        String nodeType = getTestRunMessageValue("type");

        Node node = createNode(nodeTitle, nodeContent, nodeType);
        int nodeId = node.getNid();

        upsertOnTestRunMessage("nodeId", nodeId);

        List<Comment> comments = getTestRunMessageValue("comments");
        List<Integer> commentIds = new ArrayList<Integer>();

        for (Comment comment : comments) {
            comment.setNid(nodeId);
            Comment createdComment = createComment(comment);
            commentIds.add(createdComment.getCid());
        }

        upsertOnTestRunMessage("commentIds", commentIds);
    }

    @Category({RegressionTests.class})
    @Test
    public void testCountAllComments() {
        try {
            List<Integer> commentIds = getTestRunMessageValue("commentIds");

            int count = (Integer) runFlowAndGetPayload("count-all-comments");
            assertEquals(count, commentIds.size());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        List<Integer> commentIds = getTestRunMessageValue("commentIds");
        for (Integer commentId : commentIds) {
            deleteComment(commentId);
        }

        Integer nodeId = getTestRunMessageValue("nodeId");
        deleteNode(nodeId);
    }

}
