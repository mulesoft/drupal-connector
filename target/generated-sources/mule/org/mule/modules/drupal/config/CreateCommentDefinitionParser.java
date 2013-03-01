
package org.mule.modules.drupal.config;

import java.util.Map;
import javax.annotation.Generated;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.model.holders.CommentExpressionHolder;
import org.mule.modules.drupal.model.holders.CustomFieldExpressionHolder;
import org.mule.modules.drupal.processors.CreateCommentMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class CreateCommentDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(CreateCommentMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "comment", "comment", "#[payload]")) {
            BeanDefinitionBuilder commentBuilder = BeanDefinitionBuilder.rootBeanDefinition(CommentExpressionHolder.class.getName());
            Element commentChildElement = DomUtils.getChildElementByTagName(element, "comment");
            if (commentChildElement!= null) {
                parseProperty(commentBuilder, commentChildElement, "cid", "cid");
                parseProperty(commentBuilder, commentChildElement, "pid", "pid");
                parseProperty(commentBuilder, commentChildElement, "nid", "nid");
                parseProperty(commentBuilder, commentChildElement, "uid", "uid");
                parseProperty(commentBuilder, commentChildElement, "subject", "subject");
                parseProperty(commentBuilder, commentChildElement, "hostname", "hostname");
                parseProperty(commentBuilder, commentChildElement, "created", "created");
                parseProperty(commentBuilder, commentChildElement, "changed", "changed");
                parseProperty(commentBuilder, commentChildElement, "status", "status");
                parseProperty(commentBuilder, commentChildElement, "thread", "thread");
                parseProperty(commentBuilder, commentChildElement, "name", "name");
                parseProperty(commentBuilder, commentChildElement, "mail", "mail");
                parseProperty(commentBuilder, commentChildElement, "homepage", "homepage");
                parseProperty(commentBuilder, commentChildElement, "language", "language");
                parseProperty(commentBuilder, commentChildElement, "nodeType", "nodeType");
                parseProperty(commentBuilder, commentChildElement, "registeredName", "registeredName");
                parseProperty(commentBuilder, commentChildElement, "signature", "signature");
                parseProperty(commentBuilder, commentChildElement, "signatureFormat", "signatureFormat");
                parseProperty(commentBuilder, commentChildElement, "picture", "picture");
                parseProperty(commentBuilder, commentChildElement, "isNew", "isNew");
                if (!parseObjectRef(commentChildElement, commentBuilder, "comment-body", "commentBody")) {
                    BeanDefinitionBuilder _commentBodyBuilder = BeanDefinitionBuilder.rootBeanDefinition(CustomFieldExpressionHolder.class.getName());
                    Element _commentBodyChildElement = DomUtils.getChildElementByTagName(commentChildElement, "comment-body");
                    if (_commentBodyChildElement!= null) {
                        parseListAndSetProperty(_commentBodyChildElement, _commentBodyBuilder, "und", "und", "und", new ParseDelegate<Map>() {


                            public Map parse(Element element) {
                                return parseMap(element, "inner-und", new ParseDelegate<String>() {


                                    public String parse(Element element) {
                                        return element.getTextContent();
                                    }

                                }
                                );
                            }

                        }
                        );
                        commentBuilder.addPropertyValue("commentBody", _commentBodyBuilder.getBeanDefinition());
                    }
                }
                builder.addPropertyValue("comment", commentBuilder.getBeanDefinition());
            }
        }
        parseProperty(builder, element, "username", "username");
        parseProperty(builder, element, "password", "password");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
