package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class CreateTaxonomyTermTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createTaxonomyTermTestData");
		
		TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
		Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testCreateTaxonomyTerm() {
		try {
			Integer vocabularyId = getTestRunMessageValue("vocabularyId");
			TaxonomyTerm term = getTestRunMessageValue("taxonomyTerm");
			term.setVid(vocabularyId);
			
			TaxonomyTerm createdTerm = createTaxonomyTerm(term);
			Integer termId = createdTerm.getTid();
			assertTrue(termId >= 0);

			upsertOnTestRunMessage("termId", termId);
			
			TaxonomyTerm retrievedTerm = readTaxonomyTerm(termId);
			assertEquals(retrievedTerm.getTid(), termId);
			assertEquals(retrievedTerm.getName(), createdTerm.getName());
			assertEquals(retrievedTerm.getDescription(), createdTerm.getDescription());
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
