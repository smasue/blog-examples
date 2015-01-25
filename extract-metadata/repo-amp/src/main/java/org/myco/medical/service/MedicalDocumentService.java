package org.myco.medical.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.log4j.Logger;
import org.myco.medical.bean.DocumentIdentifiers;
import org.myco.medical.bean.MedicalDocument;
import org.myco.medical.dao.MedicalDocumentTypeDao;
import org.myco.medical.dao.MedicalPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Medical Document Service. Provides services at the document level.
 */
@Service
public class MedicalDocumentService
{

  private Logger logger = Logger.getLogger(MedicalDocumentService.class);

  // Medical service API
  @Autowired
  private MedicalPersonDao medicalPersonDao;
  @Autowired
  private MedicalDocumentTypeDao medicalDocumentTypeDao;
  @Autowired
  private MedicalNodeService medicalNodeService;

  /**
   * Builds a MedicalDocument from a DocumentIdentifier. Retrieves all data from DAO. 
   * @param documentIdentifiers
   * @return medical document corresponding to the identifiers
   */
  public MedicalDocument buildMedicalDocumentFromIdentifiers(DocumentIdentifiers documentIdentifiers)
  {
    MedicalDocument medicalDocument = new MedicalDocument();
    medicalDocument.setPerson(medicalPersonDao.getPerson(documentIdentifiers.getPersonId()));
    medicalDocument.setDocumentType(medicalDocumentTypeDao.getDocumentType(documentIdentifiers.getDocumentTypeCode()));
    medicalDocument.setName(documentIdentifiers.getFileName());
    medicalDocument.setEffectiveDate(documentIdentifiers.getEffectiveDate());
    return medicalDocument;
  }

  /**
   * Creates a medical document from an existing node. 
   *
   * @param nodeRef ref of the node.
   * @param medicalDocument object used to set the properties.
   */
  public void createMedicalDocumentFromExistingNode(NodeRef nodeRef, MedicalDocument medicalDocument)
  {
    medicalNodeService.setMedicalDocumentType(nodeRef);
    medicalNodeService.setPersonAspect(nodeRef, medicalDocument.getPerson());
    medicalNodeService.setDocumentTypeAspect(nodeRef, medicalDocument.getDocumentType());
    medicalNodeService.setEffectiveDateAspect(nodeRef, medicalDocument.getEffectiveDate());
    medicalNodeService.setName(nodeRef, medicalDocument.getName());
  }
}
