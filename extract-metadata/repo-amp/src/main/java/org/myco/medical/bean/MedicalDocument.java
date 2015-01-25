package org.myco.medical.bean;

import java.util.Date;

/**
 * Medical document
 */
public class MedicalDocument
{
  private Person person;
  private DocumentType documentType;
  private String name;
  private Date effectiveDate;

  public Person getPerson()
  {
    return person;
  }

  public void setPerson(Person person)
  {
    this.person = person;
  }

  public DocumentType getDocumentType()
  {
    return documentType;
  }

  public void setDocumentType(DocumentType documentType)
  {
    this.documentType = documentType;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Date getEffectiveDate()
  {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate)
  {
    this.effectiveDate = effectiveDate;
  }
}
