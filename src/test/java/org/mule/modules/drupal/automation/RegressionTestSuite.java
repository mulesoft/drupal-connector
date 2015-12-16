/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.drupal.automation.testcases.*;

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
        DeleteTaxonomyTermTestCases.class,
        DeleteTaxonomyVocabularyTestCases.class,
        DeleteUserTestCases.class,
        GetCommentsForNodeTestCases.class,
        GetFilesForNodeTestCases.class,
        GetTaxonomyVocabularyTreeTestCases.class,
        IndexCommentsTestCases.class,
        IndexFilesTestCases.class,
        IndexNodesTestCases.class,
        IndexTaxonomyTermsTestCases.class,
        IndexTaxonomyVocabularyTestCases.class,
        IndexUsersTestCases.class,
        ReadCommentTestCases.class,
        ReadFileTestCases.class,
        ReadNodeTestCases.class,
        ReadTaxonomyTermTestCases.class,
        ReadTaxonomyVocabularyTestCases.class,
        ReadUserTestCases.class,
        RegisterUserTestCases.class,
        UpdateCommentTestCases.class,
        UpdateCustomFieldForNodeTestCases.class,
        UpdateNodeTestCases.class,
        UpdateTaxonomyTermTestCases.class,
        UpdateTaxonomyVocabularyTestCases.class,
        UpdateUserTestCases.class
})
public class RegressionTestSuite {

}
