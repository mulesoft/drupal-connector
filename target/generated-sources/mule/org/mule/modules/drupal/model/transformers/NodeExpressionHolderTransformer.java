
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
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.holders.NodeExpressionHolder;
import org.mule.modules.drupal.processors.AbstractExpressionEvaluator;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class NodeExpressionHolderTransformer
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
        return (aClass == NodeExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == NodeExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {NodeExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(NodeExpressionHolder.class)});
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
        return Node.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(Node.class);
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
        NodeExpressionHolder holder = ((NodeExpressionHolder) src);
        Node result = new Node();
        try {
            final Integer _transformedNid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_nidType").getGenericType(), null, holder.getNid()));
            result.setNid(_transformedNid);
            final Integer _transformedUid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_uidType").getGenericType(), null, holder.getUid()));
            result.setUid(_transformedUid);
            final Integer _transformedVid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_vidType").getGenericType(), null, holder.getVid()));
            result.setVid(_transformedVid);
            final String _transformedType = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_typeType").getGenericType(), null, holder.getType()));
            result.setType(_transformedType);
            final String _transformedLanguage = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_languageType").getGenericType(), null, holder.getLanguage()));
            result.setLanguage(_transformedLanguage);
            final String _transformedTitle = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_titleType").getGenericType(), null, holder.getTitle()));
            result.setTitle(_transformedTitle);
            final Integer _transformedStatus = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_statusType").getGenericType(), null, holder.getStatus()));
            result.setStatus(_transformedStatus);
            final Integer _transformedComment = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_commentType").getGenericType(), null, holder.getComment()));
            result.setComment(_transformedComment);
            final Integer _transformedPromote = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_promoteType").getGenericType(), null, holder.getPromote()));
            result.setPromote(_transformedPromote);
            final Integer _transformedSticky = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_stickyType").getGenericType(), null, holder.getSticky()));
            result.setSticky(_transformedSticky);
            final Integer _transformedTnid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_tnidType").getGenericType(), null, holder.getTnid()));
            result.setTnid(_transformedTnid);
            final Integer _transformedTranslated = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_translatedType").getGenericType(), null, holder.getTranslated()));
            result.setTranslated(_transformedTranslated);
            final Integer _transformedRevisionTimestamp = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_revisionTimestampType").getGenericType(), null, holder.getRevisionTimestamp()));
            result.setRevisionTimestamp(_transformedRevisionTimestamp);
            final Integer _transformedRevisionUid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_revisionUidType").getGenericType(), null, holder.getRevisionUid()));
            result.setRevisionUid(_transformedRevisionUid);
            final CustomField _transformedBody = ((CustomField) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_bodyType").getGenericType(), null, holder.getBody()));
            result.setBody(_transformedBody);
            final Integer _transformedCreated = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_createdType").getGenericType(), null, holder.getCreated()));
            result.setCreated(_transformedCreated);
            final Integer _transformedChanged = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_changedType").getGenericType(), null, holder.getChanged()));
            result.setChanged(_transformedChanged);
            final Integer _transformedCid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_cidType").getGenericType(), null, holder.getCid()));
            result.setCid(_transformedCid);
            final Integer _transformedLastCommentTimestamp = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_lastCommentTimestampType").getGenericType(), null, holder.getLastCommentTimestamp()));
            result.setLastCommentTimestamp(_transformedLastCommentTimestamp);
            final String _transformedLastCommentName = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_lastCommentNameType").getGenericType(), null, holder.getLastCommentName()));
            result.setLastCommentName(_transformedLastCommentName);
            final Integer _transformedLastCommentUid = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_lastCommentUidType").getGenericType(), null, holder.getLastCommentUid()));
            result.setLastCommentUid(_transformedLastCommentUid);
            final Integer _transformedCommentCount = ((Integer) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_commentCountType").getGenericType(), null, holder.getCommentCount()));
            result.setCommentCount(_transformedCommentCount);
            final String _transformedName = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_nameType").getGenericType(), null, holder.getName()));
            result.setName(_transformedName);
            final String _transformedPicture = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_pictureType").getGenericType(), null, holder.getPicture()));
            result.setPicture(_transformedPicture);
            final String _transformedData = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_dataType").getGenericType(), null, holder.getData()));
            result.setData(_transformedData);
            final String _transformedPath = ((String) evaluateAndTransform(this.muleContext, event, NodeExpressionHolder.class.getDeclaredField("_pathType").getGenericType(), null, holder.getPath()));
            result.setPath(_transformedPath);
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
