
package org.mule.modules.drupal.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/drupal</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new DrupalConnectorConfigDefinitionParser());
        registerBeanDefinitionParser("read-node", new ReadNodeDefinitionParser());
        registerBeanDefinitionParser("read-comment", new ReadCommentDefinitionParser());
        registerBeanDefinitionParser("read-user", new ReadUserDefinitionParser());
        registerBeanDefinitionParser("read-taxonomy-term", new ReadTaxonomyTermDefinitionParser());
        registerBeanDefinitionParser("read-file", new ReadFileDefinitionParser());
        registerBeanDefinitionParser("read-taxonomy-vocabulary", new ReadTaxonomyVocabularyDefinitionParser());
        registerBeanDefinitionParser("create-node", new CreateNodeDefinitionParser());
        registerBeanDefinitionParser("create-comment", new CreateCommentDefinitionParser());
        registerBeanDefinitionParser("create-user", new CreateUserDefinitionParser());
        registerBeanDefinitionParser("create-taxonomy-term", new CreateTaxonomyTermDefinitionParser());
        registerBeanDefinitionParser("create-file", new CreateFileDefinitionParser());
        registerBeanDefinitionParser("create-taxonomy-vocabulary", new CreateTaxonomyVocabularyDefinitionParser());
        registerBeanDefinitionParser("register-user", new RegisterUserDefinitionParser());
        registerBeanDefinitionParser("update-node", new UpdateNodeDefinitionParser());
        registerBeanDefinitionParser("update-comment", new UpdateCommentDefinitionParser());
        registerBeanDefinitionParser("update-user", new UpdateUserDefinitionParser());
        registerBeanDefinitionParser("update-taxonomy-term", new UpdateTaxonomyTermDefinitionParser());
        registerBeanDefinitionParser("update-file", new UpdateFileDefinitionParser());
        registerBeanDefinitionParser("update-taxonomy-vocabulary", new UpdateTaxonomyVocabularyDefinitionParser());
        registerBeanDefinitionParser("delete-node", new DeleteNodeDefinitionParser());
        registerBeanDefinitionParser("delete-comment", new DeleteCommentDefinitionParser());
        registerBeanDefinitionParser("delete-file", new DeleteFileDefinitionParser());
        registerBeanDefinitionParser("delete-taxonomy-vocabulary", new DeleteTaxonomyVocabularyDefinitionParser());
        registerBeanDefinitionParser("delete-taxonomy-term", new DeleteTaxonomyTermDefinitionParser());
        registerBeanDefinitionParser("delete-user", new DeleteUserDefinitionParser());
        registerBeanDefinitionParser("count-all-comments", new CountAllCommentsDefinitionParser());
        registerBeanDefinitionParser("count-new-comments", new CountNewCommentsDefinitionParser());
        registerBeanDefinitionParser("index-nodes", new IndexNodesDefinitionParser());
        registerBeanDefinitionParser("index-comments", new IndexCommentsDefinitionParser());
        registerBeanDefinitionParser("index-users", new IndexUsersDefinitionParser());
        registerBeanDefinitionParser("index-taxonomy-terms", new IndexTaxonomyTermsDefinitionParser());
        registerBeanDefinitionParser("index-taxonomy-vocabulary", new IndexTaxonomyVocabularyDefinitionParser());
        registerBeanDefinitionParser("index-files", new IndexFilesDefinitionParser());
        registerBeanDefinitionParser("get-nodes-with-term", new GetNodesWithTermDefinitionParser());
        registerBeanDefinitionParser("get-taxonomy-vocabulary-tree", new GetTaxonomyVocabularyTreeDefinitionParser());
        registerBeanDefinitionParser("get-comments-for-node", new GetCommentsForNodeDefinitionParser());
        registerBeanDefinitionParser("get-files-for-node", new GetFilesForNodeDefinitionParser());
    }

}
