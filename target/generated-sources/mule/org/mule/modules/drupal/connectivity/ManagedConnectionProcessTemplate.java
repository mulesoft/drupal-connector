
package org.mule.modules.drupal.connectivity;

import javax.annotation.Generated;
import org.mule.api.ConnectionManager;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessInterceptor;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.drupal.adapters.DrupalConnectorConnectionIdentifierAdapter;
import org.mule.modules.drupal.process.ManagedConnectionProcessInterceptor;
import org.mule.modules.drupal.process.ProcessCallbackProcessInterceptor;
import org.mule.modules.drupal.process.RetryProcessInterceptor;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class ManagedConnectionProcessTemplate<P >implements ProcessTemplate<P, DrupalConnectorConnectionIdentifierAdapter>
{

    private final ProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter> processInterceptor;

    public ManagedConnectionProcessTemplate(ConnectionManager<DrupalConnectorConnectionKey, DrupalConnectorConnectionIdentifierAdapter> connectionManager, MuleContext muleContext) {
        ProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter> processCallbackProcessInterceptor = new ProcessCallbackProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter>();
        ProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter> managedConnectionProcessInterceptor = new ManagedConnectionProcessInterceptor<P>(processCallbackProcessInterceptor, connectionManager, muleContext);
        ProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter> retryProcessInterceptor = new RetryProcessInterceptor<P, DrupalConnectorConnectionIdentifierAdapter>(managedConnectionProcessInterceptor, muleContext, connectionManager.getRetryPolicyTemplate());
        processInterceptor = retryProcessInterceptor;
    }

    public P execute(ProcessCallback<P, DrupalConnectorConnectionIdentifierAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, messageProcessor, event);
    }

    public P execute(ProcessCallback<P, DrupalConnectorConnectionIdentifierAdapter> processCallback, Filter filter, MuleMessage message)
        throws Exception
    {
        return processInterceptor.execute(processCallback, null, filter, message);
    }

}
