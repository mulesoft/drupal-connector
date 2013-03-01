
package org.mule.modules.drupal.model.transformers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.MessageTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transformer.TransformerMessagingException;
import org.mule.config.i18n.CoreMessages;
import org.mule.modules.drupal.model.User;
import org.mule.modules.drupal.model.holders.UserExpressionHolder;
import org.mule.modules.drupal.processors.AbstractExpressionEvaluator;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class UserExpressionHolderTransformer
    extends AbstractExpressionEvaluator
    implements DiscoverableTransformer, MessageTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;
    private ImmutableEndpoint endpoint;
    private MuleContext muleContext;
    private String name;

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

    public boolean isSourceTypeSupported(Class<?> aClass) {
        return (aClass == UserExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == UserExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {UserExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(UserExpressionHolder.class)});
    }

    public boolean isAcceptNull() {
        return false;
    }

    public boolean isIgnoreBadInput() {
        return false;
    }

    public Object transform(Object src)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public Object transform(Object src, String encoding)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public void setReturnClass(Class<?> theClass) {
        throw new UnsupportedOperationException();
    }

    public Class<?> getReturnClass() {
        return User.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(User.class);
    }

    public void setEndpoint(ImmutableEndpoint ep) {
        endpoint = ep;
    }

    public ImmutableEndpoint getEndpoint() {
        return endpoint;
    }

    public void dispose() {
    }

    public void initialise()
        throws InitialisationException
    {
    }

    public void setMuleContext(MuleContext context) {
        muleContext = context;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public Object transform(Object src, MuleEvent event)
        throws TransformerMessagingException
    {
        return transform(src, null, event);
    }

    public Object transform(Object src, String encoding, MuleEvent event)
        throws TransformerMessagingException
    {
        if (src == null) {
            return null;
        }
        UserExpressionHolder holder = ((UserExpressionHolder) src);
        User result = new User();
        try {
            final Integer _transformedUid = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_uidType").getGenericType(), null, holder.getUid()));
            result.setUid(_transformedUid);
            final String _transformedName = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_nameType").getGenericType(), null, holder.getName()));
            result.setName(_transformedName);
            final String _transformedMail = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_mailType").getGenericType(), null, holder.getMail()));
            result.setMail(_transformedMail);
            final String _transformedPassword = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_passwordType").getGenericType(), null, holder.getPassword()));
            result.setPassword(_transformedPassword);
            final String _transformedTheme = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_themeType").getGenericType(), null, holder.getTheme()));
            result.setTheme(_transformedTheme);
            final String _transformedSignature = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_signatureType").getGenericType(), null, holder.getSignature()));
            result.setSignature(_transformedSignature);
            final String _transformedSignatureFormat = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_signatureFormatType").getGenericType(), null, holder.getSignatureFormat()));
            result.setSignatureFormat(_transformedSignatureFormat);
            final Integer _transformedCreated = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_createdType").getGenericType(), null, holder.getCreated()));
            result.setCreated(_transformedCreated);
            final Integer _transformedAccess = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_accessType").getGenericType(), null, holder.getAccess()));
            result.setAccess(_transformedAccess);
            final Integer _transformedLogin = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_loginType").getGenericType(), null, holder.getLogin()));
            result.setLogin(_transformedLogin);
            final Integer _transformedStatus = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_statusType").getGenericType(), null, holder.getStatus()));
            result.setStatus(_transformedStatus);
            final String _transformedTimezones = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_timezonesType").getGenericType(), null, holder.getTimezones()));
            result.setTimezones(_transformedTimezones);
            final String _transformedLanguage = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_languageType").getGenericType(), null, holder.getLanguage()));
            result.setLanguage(_transformedLanguage);
            final Integer _transformedPicture = ((Integer) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_pictureType").getGenericType(), null, holder.getPicture()));
            result.setPicture(_transformedPicture);
            final String _transformedInit = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_initType").getGenericType(), null, holder.getInit()));
            result.setInit(_transformedInit);
            final String _transformedData = ((String) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_dataType").getGenericType(), null, holder.getData()));
            result.setData(_transformedData);
            final HashMap<Integer, String> _transformedRoles = ((HashMap<Integer, String> ) evaluateAndTransform(this.muleContext, event, UserExpressionHolder.class.getDeclaredField("_rolesType").getGenericType(), null, holder.getRoles()));
            result.setRoles(_transformedRoles);
        } catch (NoSuchFieldException e) {
            throw new TransformerMessagingException(CoreMessages.createStaticMessage("internal error"), event, this, e);
        } catch (TransformerException e) {
            throw new TransformerMessagingException(e.getI18nMessage(), event, this, e);
        }
        return result;
    }

    public MuleEvent process(MuleEvent event) {
        return null;
    }

    public String getMimeType() {
        return null;
    }

    public String getEncoding() {
        return null;
    }

    public MuleContext getMuleContext() {
        return muleContext;
    }

}
