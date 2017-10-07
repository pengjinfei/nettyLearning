package com.pengjinfei.netty.ch9;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class MarshallingCodeCFactory {

    public static MarshallingDecoder buildingMarshallingDecoder() {
        MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);
        return new MarshallingDecoder(provider);
    }

    public static MarshallingEncoder buildingMarshallingEncoder() {
        MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);
        return new MarshallingEncoder(provider);
    }
}
