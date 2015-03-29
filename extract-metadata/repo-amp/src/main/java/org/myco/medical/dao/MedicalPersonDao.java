package org.myco.medical.dao;

import org.myco.medical.bean.Person;
import org.springframework.stereotype.Service;

/**
 * Person data access object - fake implementation for the blog.
 */
@Service
public class MedicalPersonDao
{
  private Person fakePerson;

  public Person getPerson(Long personId)
  {
    if (personId.equals(fakePerson.getId()))
    {
      return fakePerson;
    }
    return null;
  }

  public MedicalPersonDao()
  {
    fakePerson = new Person();
    fakePerson.setId(1234L);
    fakePerson.setFirstName("Foo");
    fakePerson.setLastName("Bar");
    fakePerson.setGender("Male");
    fakePerson.setJobTitle("Alfresco developer");
    fakePerson.setAge(25);
  }
}
