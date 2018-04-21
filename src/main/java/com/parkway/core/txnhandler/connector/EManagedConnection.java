package com.parkway.core.txnhandler.connector;

public abstract interface EManagedConnection
{
  public abstract void addConnectionEventListener(EConnectionEventListener paramEConnectionEventListener);

  public abstract void cleanup();

  public abstract void destroy();

  public abstract void removeConnectionEventListener(EConnectionEventListener paramEConnectionEventListener);

  public abstract Object getConnection();
}
