package org.myco.medical.dao;

import org.myco.medical.bean.DocumentType;
import org.springframework.stereotype.Service;

/**
 * Document type data access object - fake implementation for the blog
 */
@Service
public class MedicalDocumentTypeDao
{
  private DocumentType fakeDocumentType;

  public MedicalDocumentTypeDao()
  {
    fakeDocumentType = new DocumentType();
    fakeDocumentType.setCode("PR");
    fakeDocumentType.setName("Prescription");
    fakeDocumentType.setDescription("Medical prescription ...");
  }

  public DocumentType getDocumentType(String documentTypeCode)
  {
    if (fakeDocumentType.getCode().equalsIgnoreCase(documentTypeCode))
    {
      return fakeDocumentType;
    }
    return null;
  }
}
