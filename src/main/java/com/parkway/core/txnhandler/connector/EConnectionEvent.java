package com.parkway.core.txnhandler.connector;

import java.util.EventObject;

public class EConnectionEvent extends EventObject
{
  public static final int CONNECTION_CLOSED = 1;
  public static final int CONNECTION_ERROR_OCCURRED = 5;
  protected Exception exception;
  protected int id;
  protected Object connectionHandle;

  public Object getConnectionHandle()
  {
    return this.connectionHandle;
  }

  public Exception getException()
  {
    return this.exception;
  }

  public int getId()
  {
    return this.id;
  }

  public void setConnectionHandle(Object connectionHandle)
  {
    this.connectionHandle = connectionHandle;
  }

  public EConnectionEvent(EManagedConnection mc, int id)
  {
    super(mc);

    this.id = id;
  }

  public EConnectionEvent(EManagedConnection mc, int id, Exception ex)
  {
    super(mc);

    this.exception = ex;
    this.id = id;
  }
}
