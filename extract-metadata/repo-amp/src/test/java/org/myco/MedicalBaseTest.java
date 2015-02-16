package org.myco;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.model.Repository;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.transaction.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.myco.helper.TransactionTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Base test class for Medical project
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:alfresco/application-context.xml"})
public class MedicalBaseTest
{
  @Autowired
  @Qualifier("nodeService")
  protected NodeService nodeService;

  @Autowired
  @Qualifier("fileFolderService")
  protected FileFolderService fileFolderService;

  @Autowired
  @Qualifier("transactionService")
  protected TransactionService transactionService;

  @Autowired
  @Qualifier("actionService")
  protected ActionService actionService;

  @Autowired
  @Qualifier("contentService")
  protected ContentService contentService;
  
  @Autowired
  @Qualifier("repositoryHelper")
  private Repository repository;

  protected TransactionTestHelper transactionTestHelper;

  public static final String FOLDER_FOR_TEST = "folder_test";
  public static final String DOCUMENT_FOR_TEST = "document_test";
  public static String DATE_STRING = "01-01-2015";

  @Before
  public void setUp() throws Exception
  {
    transactionTestHelper = new TransactionTestHelper(transactionService);
    transactionTestHelper.startTransactionAdmin();
  }

  @After
  public void tearDown() throws Exception
  {
    transactionTestHelper.rollbackTransaction();
  }

  /**
   * create cm:folder in companyHome. The variable FOLDER_FOR_TEST is used as folder name.
   *
   * @return nodeRef of the folder
   */
  public NodeRef createFolderForTest()
  {
    NodeRef companyHome = repository.getCompanyHome();
    NodeRef testFolder = fileFolderService.create(companyHome, FOLDER_FOR_TEST, ContentModel.TYPE_FOLDER).getNodeRef();
    assertTrue(nodeService.exists(testFolder));
    return testFolder;
  }

  /**
   * create a cm:content. The variable DOCUMENT_FOR_TEST is used for document name.
   *
   * @param parentFolder
   * @return document NodeRef
   */
  public NodeRef createDocumentForTest(NodeRef parentFolder)
  {
    assertNotNull(parentFolder);
    NodeRef document =
      fileFolderService.create(parentFolder, DOCUMENT_FOR_TEST, ContentModel.TYPE_CONTENT).getNodeRef();
    assertNotNull(document);
    assertTrue(nodeService.exists(document));
    return document;
  }
  
  public Date createDummyDate() throws ParseException
  {
    return new SimpleDateFormat("dd-MM-yyyy").parse(DATE_STRING);
  }
}
