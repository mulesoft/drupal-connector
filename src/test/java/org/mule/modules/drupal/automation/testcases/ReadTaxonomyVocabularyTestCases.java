package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class ReadTaxonomyVocabularyTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("readTaxonomyVocabularyTestData");
		
		TaxonomyVocabulary vocabulary = getTestRunMessageValue("vocabulary");
		
		Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testReadTaxonomyVocabulary() {
		try {
			TaxonomyVocabulary vocabulary = getTestRunMessageValue("vocabulary");
			Integer vocabularyId = getTestRunMessageValue("vocabularyId");
			
			TaxonomyVocabulary retrievedVocabulary = readTaxonomyVocabulary(vocabularyId);
			assertEquals(retrievedVocabulary.getVid(), vocabularyId);
			assertEquals(vocabulary.getMachineName(), retrievedVocabulary.getMachineName());
			assertEquals(vocabulary.getName(), retrievedVocabulary.getName());
			assertEquals(vocabulary.getDescription(), retrievedVocabulary.getDescription());
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
