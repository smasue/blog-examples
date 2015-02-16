package org.myco.helper;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.transaction.TransactionService;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * TransactionHelper
 */
public class TransactionTestHelper
{

  private TransactionService transactionService;

  private UserTransaction tx;

  public TransactionTestHelper(TransactionService transactionService)
  {
    this.transactionService = transactionService;
  }

  public void startTransaction() throws SystemException, NotSupportedException
  {
    AuthenticationUtil.setFullyAuthenticatedUser(AuthenticationUtil.getSystemUserName());
    tx = transactionService.getUserTransaction();
    tx.begin();
  }

  public void startTransactionAdmin() throws SystemException, NotSupportedException
  {
    AuthenticationUtil.setFullyAuthenticatedUser(AuthenticationUtil.getAdminUserName());
    tx = transactionService.getUserTransaction();
    tx.begin();
  }

  public void commitTransaction()
    throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException
  {
    if ((tx.getStatus() == Status.STATUS_ACTIVE) || (tx.getStatus() != Status.STATUS_MARKED_ROLLBACK))
    {
      tx.commit();
    }
  }

  public void rollbackTransaction() throws SystemException
  {
    if ((tx.getStatus() == Status.STATUS_ACTIVE) || (tx.getStatus() == Status.STATUS_MARKED_ROLLBACK))
    {
      tx.rollback();
    }
    AuthenticationUtil.clearCurrentSecurityContext();
  }

}
