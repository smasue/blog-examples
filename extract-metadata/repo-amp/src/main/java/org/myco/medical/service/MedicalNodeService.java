package org.myco.medical.service;

import com.google.common.collect.Maps;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.myco.medical.bean.DocumentType;
import org.myco.medical.bean.Person;
import org.myco.medical.constant.MedicalServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Medical node service
 * Additional layer on top of alfresco NodeService
 */
@Service
public class MedicalNodeService implements MedicalServiceModel
{
  private Logger logger = Logger.getLogger(MedicalNodeService.class);


  // Alfresco foundation API
  @Autowired
  @Qualifier("NodeService")
  private NodeService nodeService;

  /**
   * set type ms:document to the node.
   *
   * @param nodeRef ref of the node.
   */
  public void setMedicalDocumentType(NodeRef nodeRef)
  {
    nodeService.setType(nodeRef, QNAME_TYPE_MEDICAL_DOCUMENT);
  }

  /**
   * set the aspect ms:person to the node.
   *
   * @param nodeRef ref of the node.
   * @param person  properties for the aspect.
   */
  public void setPersonAspect(NodeRef nodeRef, Person person)
  {
    Map<QName, Serializable> aspectValues = Maps.newHashMap();

    aspectValues.put(QNAME_PROP_PERSON_ID, person.getId());
    aspectValues.put(QNAME_PROP_FIRST_NAME, person.getFirstName());
    aspectValues.put(QNAME_PROP_LAST_NAME, person.getLastName());
    aspectValues.put(QNAME_PROP_STATUS, person.getStatus());
    aspectValues.put(QNAME_PROP_ORG_UNIT, person.getOrgUnit());
    nodeService.addAspect(nodeRef, QNAME_ASPECT_PERSON, aspectValues);

    logger.debug("Set person aspect. Node id: " + nodeRef.getId() + ", person id: " + person.getId());
  }

  /**
   * remove the aspect ms:person to the node.
   *
   * @param nodeRef ref of the node.
   */
  public void removePersonAspect(NodeRef nodeRef)
  {
    nodeService.removeAspect(nodeRef, QNAME_ASPECT_PERSON);
  }

  /**
   * set the aspect ms:effectiveDate to the node.
   *
   * @param nodeRef       ref of the node.
   * @param effectiveDate property for the aspect
   */
  public void setEffectiveDateAspect(NodeRef nodeRef, Date effectiveDate)
  {
    Map<QName, Serializable> aspectValues = Maps.newHashMap();

    aspectValues.put(QNAME_PROP_DATE, effectiveDate);
    nodeService.addAspect(nodeRef, QNAME_ASPECT_EFFECTIVE_DATE, aspectValues);

    logger.debug("Set effectiveDate aspect. Node id: " + nodeRef.getId() + ", date: " + effectiveDate);
  }

  /**
   * remove the aspect ms:effectiveDate to the node.
   *
   * @param nodeRef ref of the node.
   */
  public void removeEffectiveDateAspect(NodeRef nodeRef)
  {
    nodeService.removeAspect(nodeRef, QNAME_ASPECT_EFFECTIVE_DATE);
  }

  /**
   * set the aspect ms:documentType to the node.
   *
   * @param nodeRef      ref of the node.
   * @param documentType properties for the aspect
   */
  public void setDocumentTypeAspect(NodeRef nodeRef, DocumentType documentType)
  {
    Map<QName, Serializable> aspectValues = Maps.newHashMap();

    aspectValues.put(QNAME_PROP_DOC_TYPE_CODE, documentType.getCode());
    aspectValues.put(QNAME_PROP_DOC_TYPE_NAME, documentType.getName());
    aspectValues.put(QNAME_PROP_DOC_TYPE_DESCRIPTION, documentType.getDescription());
    nodeService.addAspect(nodeRef, QNAME_ASPECT_DOCUMENT_TYPE, aspectValues);

    logger.debug(
      "Set document type aspect. Node id: " + nodeRef.getId() + ", document type code: " + documentType.getCode());
  }

  /**
   * remove the aspect ms:documentType to the node.
   *
   * @param nodeRef ref of the node.
   */
  public void removeDocumentTypeAspect(NodeRef nodeRef)
  {
    nodeService.removeAspect(nodeRef, QNAME_ASPECT_DOCUMENT_TYPE);
  }

  /**
   * return property ms:personId of the node.
   *
   * @param nodeRef ref of the node.
   * @return personId
   */
  public Long getPersonId(NodeRef nodeRef)
  {
    return (Long) nodeService.getProperty(nodeRef, QNAME_PROP_PERSON_ID);
  }

  /**
   * return property ms:firstName of the node.
   *
   * @param nodeRef ref of the node.
   * @return firstName
   */
  public String getFirstName(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_FIRST_NAME);
  }

  /**
   * return property ms:lastName of the node.
   *
   * @param nodeRef ref of the node.
   * @return lastName
   */
  public Long getLastName(NodeRef nodeRef)
  {
    return (Long) nodeService.getProperty(nodeRef, QNAME_PROP_LAST_NAME);
  }

  /**
   * return property ms:status of the node.
   *
   * @param nodeRef ref of the node.
   * @return status
   */
  public String getStatus(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_STATUS);
  }

  /**
   * return property ms:orgUnit of the node.
   *
   * @param nodeRef ref of the node.
   * @return orgUnit
   */
  public String getOrgUnit(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_ORG_UNIT);
  }

  /**
   * return property ms:date of the node.
   *
   * @param nodeRef ref of the node.
   * @return effectiveDate
   */
  public Date getEffectiveDate(NodeRef nodeRef)
  {
    return (Date) nodeService.getProperty(nodeRef, QNAME_PROP_DATE);
  }

  /**
   * return property ms:docTypeCode of the node.
   *
   * @param nodeRef ref of the node.
   * @return docTypeCode
   */
  public String getDocTypeCode(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_DOC_TYPE_CODE);
  }

  /**
   * return property ms:docTypeName of the node.
   *
   * @param nodeRef ref of the node.
   * @return docTypeName
   */
  public String getDocTypeName(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_DOC_TYPE_NAME);
  }

  /**
   * return property ms:docTypeDescription of the node.
   *
   * @param nodeRef ref of the node.
   * @return docTypeDescription
   */
  public String getDocTypeDescription(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, QNAME_PROP_DOC_TYPE_DESCRIPTION);
  }

  /**
   * return property cm:name of the node.
   *
   * @param nodeRef ref of the node.
   * @return name
   */
  public String getName(NodeRef nodeRef)
  {
    return (String) nodeService.getProperty(nodeRef, ContentModel.PROP_NAME);
  }

  /**
   * Sets cm:name property to the node.
   * @param nodeRef ref of the node.
   * @param name name to set.
   */
  public void setName(NodeRef nodeRef, String name)
  {
    nodeService.setProperty(nodeRef, ContentModel.PROP_NAME, name);
  }
}
