package org.myco.medical.constant;

import org.alfresco.service.namespace.QName;

/**
 * Constants that represent the medical service content model - medicalServiceModel.xml
 */
public interface MedicalServiceModel
{
  public static final String NAMESPACE_MEDICAL_MODEL_URI = "http://www.myco.ch/model/medicalservice/1.0";
  public static final String NAMESPACE_MEDICAL_MODEL_PREFIX = "ms";

  public static final String TYPE_MEDICAL_DOCUMENT = "document";
  public static final String ASPECT_PERSON = "person";
  public static final String ASPECT_EFFECTIVE_DATE = "effectiveDate";
  public static final String ASPECT_DOCUMENT_TYPE = "documentType";
  public static final String PROP_PERSON_ID = "personId";
  public static final String PROP_FIRST_NAME = "firstName";
  public static final String PROP_LAST_NAME = "lastName";
  public static final String PROP_STATUS = "status";
  public static final String PROP_ORG_UNIT = "orgUnit";
  public static final String PROP_DATE = "date";
  public static final String PROP_DOC_TYPE_CODE = "docTypeCode";
  public static final String PROP_DOC_TYPE_NAME = "docTypeName";
  public static final String PROP_DOC_TYPE_DESCRIPTION = "docTypeDescription";


  public static final QName QNAME_TYPE_MEDICAL_DOCUMENT =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, TYPE_MEDICAL_DOCUMENT);
  public static final QName QNAME_ASPECT_PERSON = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, ASPECT_PERSON);
  public static final QName QNAME_ASPECT_EFFECTIVE_DATE =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, ASPECT_EFFECTIVE_DATE);
  public static final QName QNAME_ASPECT_DOCUMENT_TYPE =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, ASPECT_DOCUMENT_TYPE);
  public static final QName QNAME_PROP_PERSON_ID = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_PERSON_ID);
  public static final QName QNAME_PROP_FIRST_NAME = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_FIRST_NAME);
  public static final QName QNAME_PROP_LAST_NAME = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_LAST_NAME);
  public static final QName QNAME_PROP_STATUS = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_STATUS);
  public static final QName QNAME_PROP_ORG_UNIT = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_ORG_UNIT);
  public static final QName QNAME_PROP_DATE = QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_DATE);
  public static final QName QNAME_PROP_DOC_TYPE_CODE =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_DOC_TYPE_CODE);
  public static final QName QNAME_PROP_DOC_TYPE_NAME =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_DOC_TYPE_NAME);
  public static final QName QNAME_PROP_DOC_TYPE_DESCRIPTION =
    QName.createQName(NAMESPACE_MEDICAL_MODEL_URI, PROP_DOC_TYPE_DESCRIPTION);
}
