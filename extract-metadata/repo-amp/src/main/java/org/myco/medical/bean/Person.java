package org.myco.medical.bean;

/**
 * Person 
 */
public class Person
{
  private Long id;
  private String firstName;
  private String lastName;
  private String status; 
  private String orgUnit;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getOrgUnit()
  {
    return orgUnit;
  }

  public void setOrgUnit(String orgUnit)
  {
    this.orgUnit = orgUnit;
  }
}
