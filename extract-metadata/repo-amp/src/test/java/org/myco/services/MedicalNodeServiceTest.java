package org.myco.services;

import org.alfresco.service.cmr.repository.NodeRef;
import org.junit.Test;
import org.myco.MedicalBaseTest;
import org.myco.medical.bean.DocumentType;
import org.myco.medical.bean.Person;
import org.myco.medical.constant.MedicalServiceModel;
import org.myco.medical.service.MedicalNodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for MedicalNodeService
 */
public class MedicalNodeServiceTest extends MedicalBaseTest implements MedicalServiceModel
{
  /// TODO change orgunit and status, finish unit test.
  @Autowired
  private MedicalNodeService medicalNodeService;

  private NodeRef testDocument;

  private NodeRef testFolder;
  
  @Override
  public void setUp() throws Exception
  {
    super.setUp();
    testFolder = createFolderForTest();
    testDocument = createDocumentForTest(testFolder);
  }

  @Test
  public void test_setPersonAspect()
  {
    Person fakePerson = new Person();
    fakePerson.setId(1234L);
    fakePerson.setFirstName("Foo");
    fakePerson.setLastName("Bar");
    fakePerson.setStatus("STAFF");
    fakePerson.setOrgUnit("MYCO-GS-MED");
    medicalNodeService.setPersonAspect(testDocument, fakePerson);
    
//    assertEquals(TYPE_MEDICAL_DOCUMENT, nodeService.getType(testDocument));
    assertEquals(fakePerson.getId(), (Long) nodeService.getProperty(testDocument, QNAME_PROP_PERSON_ID));
    assertEquals(fakePerson.getFirstName(), nodeService.getProperty(testDocument, QNAME_PROP_FIRST_NAME));
    assertEquals(fakePerson.getLastName(), nodeService.getProperty(testDocument, QNAME_PROP_LAST_NAME));
    assertEquals(fakePerson.getOrgUnit(), nodeService.getProperty(testDocument, QNAME_PROP_ORG_UNIT));
    assertEquals(fakePerson.getStatus(), nodeService.getProperty(testDocument, QNAME_PROP_STATUS));
  }
  
  @Test
  public void test_setEffectiveDateAspect() throws ParseException
  {
    Date effectiveDate = createDummyDate();
    
    medicalNodeService.setEffectiveDateAspect(testDocument, effectiveDate);
    
    assertEquals(effectiveDate, nodeService.getProperty(testDocument, QNAME_PROP_DATE));
  }

  @Test
  public void setDocumentTypeAspect()
  {

    DocumentType fakeDocumentType = new DocumentType();
    fakeDocumentType.setCode("PR");
    fakeDocumentType.setName("Prescription");
    fakeDocumentType.setDescription("Medical prescription ...");

    medicalNodeService.setDocumentTypeAspect(testDocument, fakeDocumentType);
    
    assertEquals(fakeDocumentType.getCode(), nodeService.getProperty(testDocument, QNAME_PROP_DOC_TYPE_CODE));
    assertEquals(fakeDocumentType.getName(), nodeService.getProperty(testDocument, QNAME_PROP_DOC_TYPE_NAME));
    assertEquals(fakeDocumentType.getDescription(), nodeService.getProperty(testDocument, QNAME_PROP_DOC_TYPE_DESCRIPTION));
  }

}
