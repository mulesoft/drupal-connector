/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class GetNodesWithTermTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getNodesWithTermTestData");
		
		TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
		int vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
		
		TaxonomyTerm term = getTestRunMessageValue("taxonomyTerm");
		term.setVid(vocabularyId);
		int termId = createTaxonomyTermAndGetBackId(term);
		upsertOnTestRunMessage("termId", termId);
		
		List<Node> nodes = getTestRunMessageValue("nodes");
		List<Integer> nodeIds = new ArrayList<Integer>();
		for (Node node : nodes) {
			addTermToNode(node, termId);
			
			Node createdNode = createNode(node);
			
			int nodeId = createdNode.getNid();
			nodeIds.add(nodeId);
		}
		upsertOnTestRunMessage("nodeIds", nodeIds);
	}
	
	// Test fails because terms are not being assigned to nodes on Drupal's end
	@Category({RegressionTests.class})
	@Test
	@Ignore
	public void testGetNodesWithTerm() {
		try {
			List<Integer> nodeIds = getTestRunMessageValue("nodeIds");
			Integer termId = getTestRunMessageValue("termId");
			
			upsertOnTestRunMessage("taxonomyTermId", termId);
			List<Node> nodesWithTerm = runFlowAndGetPayload("get-nodes-with-term");
			for (Node node : nodesWithTerm) {
				assertTrue(nodeIds.contains(node.getNid()));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		List<Integer> nodeIds = getTestRunMessageValue("nodeIds");
		for (Integer nodeId : nodeIds) {
			deleteNode(nodeId);
		}
		
		Integer vocabularyId = getTestRunMessageValue("vocabularyId");
		deleteTaxonomyVocabulary(vocabularyId);
	}

}
