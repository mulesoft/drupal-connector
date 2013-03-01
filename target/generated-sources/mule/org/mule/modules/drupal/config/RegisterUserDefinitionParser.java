
package org.mule.modules.drupal.config;

import javax.annotation.Generated;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.model.holders.UserExpressionHolder;
import org.mule.modules.drupal.processors.RegisterUserMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class RegisterUserDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RegisterUserMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "user", "user", "#[payload]")) {
            BeanDefinitionBuilder userBuilder = BeanDefinitionBuilder.rootBeanDefinition(UserExpressionHolder.class.getName());
            Element userChildElement = DomUtils.getChildElementByTagName(element, "user");
            if (userChildElement!= null) {
                parseProperty(userBuilder, userChildElement, "uid", "uid");
                parseProperty(userBuilder, userChildElement, "name", "name");
                parseProperty(userBuilder, userChildElement, "mail", "mail");
                parseProperty(userBuilder, userChildElement, "password", "password");
                parseProperty(userBuilder, userChildElement, "theme", "theme");
                parseProperty(userBuilder, userChildElement, "signature", "signature");
                parseProperty(userBuilder, userChildElement, "signatureFormat", "signatureFormat");
                parseProperty(userBuilder, userChildElement, "created", "created");
                parseProperty(userBuilder, userChildElement, "access", "access");
                parseProperty(userBuilder, userChildElement, "login", "login");
                parseProperty(userBuilder, userChildElement, "status", "status");
                parseProperty(userBuilder, userChildElement, "timezones", "timezones");
                parseProperty(userBuilder, userChildElement, "language", "language");
                parseProperty(userBuilder, userChildElement, "picture", "picture");
                parseProperty(userBuilder, userChildElement, "init", "init");
                parseProperty(userBuilder, userChildElement, "data", "data");
                parseMapAndSetProperty(userChildElement, userBuilder, "roles", "roles", "role", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                builder.addPropertyValue("user", userBuilder.getBeanDefinition());
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
