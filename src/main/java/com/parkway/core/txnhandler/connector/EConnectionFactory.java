package com.parkway.core.txnhandler.connector;

import java.io.Serializable;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;

public abstract interface EConnectionFactory extends Serializable, Referenceable
{
  public abstract EConnection getConnection();

  public abstract EConnection getConnection(String paramString);

  public abstract Reference getReference() throws NamingException;

  public abstract void setReference(Reference paramReference);
  
}
