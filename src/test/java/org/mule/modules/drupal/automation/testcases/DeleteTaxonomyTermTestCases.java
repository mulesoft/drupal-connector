package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class DeleteTaxonomyTermTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteTaxonomyTermTestData");
		
		TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
		Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);		
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
		
		TaxonomyTerm term = getTestRunMessageValue("taxonomyTerm");
		term.setVid(vocabularyId);
		Integer termId = createTaxonomyTermAndGetBackId(term);
		upsertOnTestRunMessage("termId", termId);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testDeleteTaxonomyTerm() {
		try {
			int taxonomyId = getTestRunMessageValue("termId");
			deleteTaxonomyTerm(taxonomyId);
			
			List<TaxonomyTerm> terms = indexTaxonomyTerms();
			for (TaxonomyTerm taxonomyTerm : terms) {
				assertTrue(taxonomyTerm.getTid().intValue() != taxonomyId);
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
