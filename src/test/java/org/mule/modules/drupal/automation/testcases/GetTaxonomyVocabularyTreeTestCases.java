package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class GetTaxonomyVocabularyTreeTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getTaxonomyVocabularyTreeTestData");
		
		TaxonomyVocabulary taxonomyVocabulary = getTestRunMessageValue("taxonomyVocabulary");
		int vocabularyId = createTaxonomyVocabularyAndGetBackId(taxonomyVocabulary);
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
		
		List<TaxonomyTerm> terms = getTestRunMessageValue("taxonomyTerms");
		List<Integer> termIds = new ArrayList<Integer>();
		for (TaxonomyTerm taxonomyTerm : terms) {
			taxonomyTerm.setVid(vocabularyId);
			
			int termId = createTaxonomyTermAndGetBackId(taxonomyTerm);
			termIds.add(termId);
		}
		upsertOnTestRunMessage("termIds", termIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetTaxonomyVocabularyTree() {
		try {
			List<Integer> termIds = getTestRunMessageValue("termIds");
			List<TaxonomyTerm> terms = runFlowAndGetPayload("get-taxonomy-vocabulary-tree");
			
			assertEquals(terms.size(), termIds.size());
			for (TaxonomyTerm taxonomyTerm : terms) {
				assertTrue(termIds.contains(taxonomyTerm.getTid()));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer vocabularyId = getTestRunMessageValue("vocabularyId");
		deleteTaxonomyVocabulary(vocabularyId);
	}

}
