package com.parkway.core.txnhandler.connector;

import java.io.Serializable;
import java.util.Set;

public abstract interface EManagedConnectionFactory extends Serializable
{
  public abstract Object createConnectionFactory();

  public abstract Object createConnectionFactory(EConnectionManager paramEConnectionManager);

  public abstract EManagedConnection createManagedConnection(String paramString);

  public abstract boolean equals(Object paramObject);

  public abstract EManagedConnection matchManagedConnections(Set paramSet, String paramString);
}
