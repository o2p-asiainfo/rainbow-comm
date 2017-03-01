package com.asiainfo.foundation.exception;

public class InfrastructureException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public InfrastructureException()
  {
  }

  public InfrastructureException(String message, Throwable throwable)
  {
    super(message, throwable);
  }

  public InfrastructureException(String message) {
    super(message);
  }

  public InfrastructureException(Throwable throwable) {
    super(throwable);
  }
}
