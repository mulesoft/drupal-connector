
package org.mule.modules.drupal.model.transformers;

import java.util.Arrays;
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
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.holders.CommentExpressionHolder;
import org.mule.modules.drupal.processors.AbstractExpressionEvaluator;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class CommentExpressionHolderTransformer
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
        return (aClass == CommentExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == CommentExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {CommentExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(CommentExpressionHolder.class)});
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
        return Comment.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(Comment.class);
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
        CommentExpressionHolder holder = ((CommentExpressionHolder) src);
        Comment result = new Comment();
        try {
            final Integer _transformedCid = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_cidType").getGenericType(), null, holder.getCid()));
            result.setCid(_transformedCid);
            final Integer _transformedPid = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_pidType").getGenericType(), null, holder.getPid()));
            result.setPid(_transformedPid);
            final Integer _transformedNid = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_nidType").getGenericType(), null, holder.getNid()));
            result.setNid(_transformedNid);
            final Integer _transformedUid = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_uidType").getGenericType(), null, holder.getUid()));
            result.setUid(_transformedUid);
            final String _transformedSubject = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_subjectType").getGenericType(), null, holder.getSubject()));
            result.setSubject(_transformedSubject);
            final String _transformedHostname = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_hostnameType").getGenericType(), null, holder.getHostname()));
            result.setHostname(_transformedHostname);
            final Integer _transformedCreated = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_createdType").getGenericType(), null, holder.getCreated()));
            result.setCreated(_transformedCreated);
            final Integer _transformedChanged = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_changedType").getGenericType(), null, holder.getChanged()));
            result.setChanged(_transformedChanged);
            final Integer _transformedStatus = ((Integer) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_statusType").getGenericType(), null, holder.getStatus()));
            result.setStatus(_transformedStatus);
            final String _transformedThread = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_threadType").getGenericType(), null, holder.getThread()));
            result.setThread(_transformedThread);
            final String _transformedName = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_nameType").getGenericType(), null, holder.getName()));
            result.setName(_transformedName);
            final String _transformedMail = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_mailType").getGenericType(), null, holder.getMail()));
            result.setMail(_transformedMail);
            final String _transformedHomepage = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_homepageType").getGenericType(), null, holder.getHomepage()));
            result.setHomepage(_transformedHomepage);
            final String _transformedLanguage = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_languageType").getGenericType(), null, holder.getLanguage()));
            result.setLanguage(_transformedLanguage);
            final String _transformedNodeType = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_nodeTypeType").getGenericType(), null, holder.getNodeType()));
            result.setNodeType(_transformedNodeType);
            final String _transformedRegisteredName = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_registeredNameType").getGenericType(), null, holder.getRegisteredName()));
            result.setRegisteredName(_transformedRegisteredName);
            final String _transformedSignature = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_signatureType").getGenericType(), null, holder.getSignature()));
            result.setSignature(_transformedSignature);
            final String _transformedSignatureFormat = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_signatureFormatType").getGenericType(), null, holder.getSignatureFormat()));
            result.setSignatureFormat(_transformedSignatureFormat);
            final String _transformedPicture = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_pictureType").getGenericType(), null, holder.getPicture()));
            result.setPicture(_transformedPicture);
            final String _transformedIsNew = ((String) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_isNewType").getGenericType(), null, holder.getIsNew()));
            result.setIsNew(_transformedIsNew);
            final CustomField _transformedCommentBody = ((CustomField) evaluateAndTransform(this.muleContext, event, CommentExpressionHolder.class.getDeclaredField("_commentBodyType").getGenericType(), null, holder.getCommentBody()));
            result.setCommentBody(_transformedCommentBody);
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
