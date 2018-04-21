package com.parkway.core.txnhandler.connector;

import java.util.EventListener;

public abstract interface EConnectionEventListener extends EventListener
{
  public abstract void connectionClosed(EConnectionEvent paramEConnectionEvent);

  public abstract void connectionErrorOccurred(EConnectionEvent paramEConnectionEvent);
}
