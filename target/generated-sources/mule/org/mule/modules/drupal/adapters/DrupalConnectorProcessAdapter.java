
package org.mule.modules.drupal.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.drupal.DrupalConnector;


/**
 * A <code>DrupalConnectorProcessAdapter</code> is a wrapper around {@link DrupalConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorProcessAdapter
    extends DrupalConnectorLifecycleAdapter
    implements ProcessAdapter<DrupalConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, DrupalConnectorCapabilitiesAdapter> getProcessTemplate() {
        final DrupalConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,DrupalConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, DrupalConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, DrupalConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
