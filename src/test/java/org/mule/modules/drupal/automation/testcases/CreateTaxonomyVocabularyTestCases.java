package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.fail;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class CreateTaxonomyVocabularyTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createTaxonomyVocabularyTestData");
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	
	@Test
	public void testCreateTaxonomyVocabulary() {
		try {
			
			TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
			
			//TaxonomyVocabulary createdVocabulary = createTaxonomyVocabulary(vocabulary);
			
			System.out.println(ReflectionToStringBuilder.toString(createTaxonomyVocabulary(vocabulary)));
			
//			
//			Integer vocabularyId = createdVocabulary.getVid();
//			
//			upsertOnTestRunMessage("vocabularyId", vocabularyId);
//			
//			assertTrue(vocabularyId >= 0);
//			
//			TaxonomyVocabulary retrievedVocabulary = readTaxonomyVocabulary(vocabularyId);
//			assertEquals(vocabularyId, retrievedVocabulary.getVid());
//			assertEquals(vocabulary.getName(), retrievedVocabulary.getName());
//			assertEquals(vocabulary.getDescription(), retrievedVocabulary.getDescription());
//			assertEquals(vocabulary.getMachineName(), retrievedVocabulary.getMachineName());
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
