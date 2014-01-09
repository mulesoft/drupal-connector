package org.mule.modules.drupal.automation;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.drupal.automation.testcases.AttachFilesToNodeTestCases;
import org.mule.modules.drupal.automation.testcases.CountAllCommentsTestCases;
import org.mule.modules.drupal.automation.testcases.CountNewCommentsTestCases;
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
import org.mule.modules.drupal.automation.testcases.GetCommentsForNodeTestCases;
import org.mule.modules.drupal.automation.testcases.GetFilesForNodeTestCases;
import org.mule.modules.drupal.automation.testcases.IndexCommentsTestCases;
import org.mule.modules.drupal.automation.testcases.IndexFilesTestCases;
import org.mule.modules.drupal.automation.testcases.IndexNodesTestCases;
import org.mule.modules.drupal.automation.testcases.IndexTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.IndexUsersTestCases;
import org.mule.modules.drupal.automation.testcases.ReadCommentTestCases;
import org.mule.modules.drupal.automation.testcases.ReadFileTestCases;
import org.mule.modules.drupal.automation.testcases.ReadNodeTestCases;
import org.mule.modules.drupal.automation.testcases.ReadTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.ReadUserTestCases;
import org.mule.modules.drupal.automation.testcases.RegisterUserTestCases;
import org.mule.modules.drupal.automation.testcases.RegressionTests;
import org.mule.modules.drupal.automation.testcases.UpdateCommentTestCases;
import org.mule.modules.drupal.automation.testcases.UpdateCustomFieldForNodeTestCases;
import org.mule.modules.drupal.automation.testcases.UpdateNodeTestCase;
import org.mule.modules.drupal.automation.testcases.UpdateTaxonomyVocabularyTestCases;
import org.mule.modules.drupal.automation.testcases.UpdateUserTestCases;

@RunWith(Categories.class)
@IncludeCategory(RegressionTests.class)
@SuiteClasses({
	AttachFilesToNodeTestCases.class,
	CountAllCommentsTestCases.class,
	CountNewCommentsTestCases.class,
	CreateCommentTestCases.class,
	CreateFileTestCases.class,
	CreateNodeTestCases.class,
	CreateTaxonomyTermTestCases.class,
	CreateTaxonomyVocabularyTestCases.class,
	CreateUserTestCases.class,
	DeleteCommentTestCases.class,
	DeleteFileTestCases.class,
	DeleteNodeTestCases.class,
	DeleteTaxonomyVocabularyTestCases.class,
	DeleteUserTestCases.class,
	GetCommentsForNodeTestCases.class,
	GetFilesForNodeTestCases.class,
	IndexCommentsTestCases.class,
	IndexFilesTestCases.class,
	IndexNodesTestCases.class,
	IndexTaxonomyVocabularyTestCases.class,
	IndexUsersTestCases.class,
	ReadCommentTestCases.class,
	ReadFileTestCases.class,
	ReadNodeTestCases.class,
	ReadTaxonomyVocabularyTestCases.class,
	ReadUserTestCases.class,
	RegisterUserTestCases.class,
	UpdateCommentTestCases.class,
	UpdateCustomFieldForNodeTestCases.class,
	UpdateNodeTestCase.class,
	UpdateTaxonomyVocabularyTestCases.class,
	UpdateUserTestCases.class
})
public class RegressionTestSuite {

}
