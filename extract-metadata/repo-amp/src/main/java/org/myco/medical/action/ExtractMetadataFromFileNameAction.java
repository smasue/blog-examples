package org.myco.medical.action;

import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.transaction.TransactionService;
import org.myco.medical.bean.DocumentIdentifiers;
import org.myco.medical.bean.MedicalDocument;
import org.myco.medical.service.MedicalDocumentService;
import org.myco.medical.service.MedicalFileNameService;
import org.myco.medical.service.MedicalNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Action extract metadata from node name
 */
public class ExtractMetadataFromFileNameAction extends ActionExecuterAbstractBase
{

  // Alfresco foundation API
  @Autowired
  @Qualifier("NodeService")
  private NodeService nodeService;
  @Autowired
  @Qualifier("TransactionService")
  private TransactionService transactionService;

  // Myco Medical API
  @Autowired
  private MedicalFileNameService medicalFileNameService;
  @Autowired
  private MedicalNodeService medicalNodeService;
  @Autowired
  private MedicalDocumentService medicalDocumentService;

  public static final String NAME = "extract-metadata-from-file-name";
  public static final String DESCRIPTION = "Extract metadata from file name following the pattern personId;" +
                                           "documentCode;effectiveDate";

  /**
   * Execute the action implementation
   *
   * @param action              the action
   * @param actionedUponNodeRef the actioned upon node
   */
  @Override
  protected void executeImpl(Action action, final NodeRef actionedUponNodeRef)
  {
    if (this.nodeService.exists(actionedUponNodeRef))
    {
      transactionService.getRetryingTransactionHelper()
        .doInTransaction(new RetryingTransactionHelper.RetryingTransactionCallback<Void>()
        {
          public Void execute() throws Throwable
          {
            // get file name from nodeRef
            String fileName = medicalNodeService.getName(actionedUponNodeRef);
            // parse file name and return documentIdentifier
            DocumentIdentifiers documentIdentifiers = medicalFileNameService.parseFormattedFileName(fileName);
            // build complete medical document from the identifiers - call DAOs
            MedicalDocument medicalDocument = medicalDocumentService.buildMedicalDocumentFromIdentifiers
              (documentIdentifiers);
            // makes the node a medical document
            medicalDocumentService.createMedicalDocumentFromExistingNode(actionedUponNodeRef, medicalDocument);
            return null;
          }
        });
    }
  }

  /**
   * Adds the parameter definitions to the list
   *
   * @param paramList the parameter definitions list
   */
  @Override
  protected void addParameterDefinitions(List<ParameterDefinition> paramList)
  {
    // none !
  }
}
