package org.mule.modules.drupal.automation;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.drupal.automation.testcases.AttachFilesToNodeTestCases;
import org.mule.modules.drupal.automation.testcases.CreateCommentTestCases;
import org.mule.modules.drupal.automation.testcases.CreateFileTestCases;
import org.mule.modules.drupal.automation.testcases.CreateNodeTestCases;
import org.mule.modules.drupal.automation.testcases.CreateTaxonomyTermTestCases;
import org.mule.modules.drupal.automation.testcases.CreateTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.CreateUserTestCases;
import org.mule.modules.drupal.automation.testcases.DeleteCommentTestCases;
import org.mule.modules.drupal.automation.testcases.DeleteFileTestCases;
import org.mule.modules.drupal.automation.testcases.DeleteNodeTestCases;
import org.mule.modules.drupal.automation.testcases.DeleteTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.DeleteUserTestCases;
import org.mule.modules.drupal.automation.testcases.GetFilesForNodeTestCases;
import org.mule.modules.drupal.automation.testcases.IndexCommentsTestCases;
import org.mule.modules.drupal.automation.testcases.IndexFilesTestCases;
import org.mule.modules.drupal.automation.testcases.IndexNodesTestCases;
import org.mule.modules.drupal.automation.testcases.IndexTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.IndexUsersTestCases;
import org.mule.modules.drupal.automation.testcases.ReadFileTestCases;
import org.mule.modules.drupal.automation.testcases.ReadNodeTestCases;
import org.mule.modules.drupal.automation.testcases.ReadTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.ReadUserTestCases;
import org.mule.modules.drupal.automation.testcases.SmokeTests;

@RunWith(Categories.class)
@IncludeCategory(SmokeTests.class)
@SuiteClasses({
	AttachFilesToNodeTestCases.class,
	CreateCommentTestCases.class,
	CreateFileTestCases.class,
	CreateNodeTestCases.class,
	CreateTaxonomyVocabularyTestCases.class,
	CreateTaxonomyTermTestCases.class,
	CreateUserTestCases.class,
	DeleteCommentTestCases.class,
	DeleteFileTestCases.class,
	DeleteNodeTestCases.class,
	DeleteTaxonomyVocabularyTestCases.class,
	DeleteUserTestCases.class,
	GetFilesForNodeTestCases.class,
	IndexCommentsTestCases.class,
	IndexFilesTestCases.class,
	IndexNodesTestCases.class,
	IndexTaxonomyVocabularyTestCases.class,
	IndexUsersTestCases.class,
	ReadFileTestCases.class,
	ReadNodeTestCases.class,
	ReadTaxonomyVocabularyTestCases.class,
	ReadUserTestCases.class,
})
public class SmokeTestSuite {

}
