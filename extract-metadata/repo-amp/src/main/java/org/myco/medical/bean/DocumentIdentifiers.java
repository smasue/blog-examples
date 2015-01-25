package org.myco.medical.bean;

import java.util.Date;

/**
 * identifiers for medical documents 
 */
public class DocumentIdentifiers
{
  private Long personId;
  private String documentTypeCode;
  private Date effectiveDate;
  private String fileName;

  public String getFileName()
  {
    return fileName;
  }

  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public Date getEffectiveDate()
  {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate)
  {
    this.effectiveDate = effectiveDate;
  }

  public String getDocumentTypeCode()
  {
    return documentTypeCode;
  }

  public void setDocumentTypeCode(String documentTypeCode)
  {
    this.documentTypeCode = documentTypeCode;
  }

  public Long getPersonId()
  {
    return personId;
  }

  public void setPersonId(Long personId)
  {
    this.personId = personId;
  }
}
