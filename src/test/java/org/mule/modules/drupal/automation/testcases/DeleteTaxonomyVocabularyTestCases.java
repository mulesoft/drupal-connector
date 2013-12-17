package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class DeleteTaxonomyVocabularyTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteTaxonomyVocabularyTestData");
		
		TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
		
		int vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testDeleteTaxonomyVocabulary() {
		try {
			int vocabularyId = getTestRunMessageValue("vocabularyId");
			deleteTaxonomyVocabulary(vocabularyId);
			
			List<TaxonomyVocabulary> taxonomyVocabularies = indexTaxonomyVocabulary();

			for (TaxonomyVocabulary taxonomyVocabulary : taxonomyVocabularies) {
				assertTrue(taxonomyVocabulary.getVid() != vocabularyId);
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}

}