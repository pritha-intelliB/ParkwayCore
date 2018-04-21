package com.parkway.core.txnhandler.connector;

import java.io.Serializable;

public abstract interface EConnectionManager extends Serializable
{
  public abstract Object allocateConnection(EManagedConnectionFactory paramEManagedConnectionFactory, String paramString);
}
