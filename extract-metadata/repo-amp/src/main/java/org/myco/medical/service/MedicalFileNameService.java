package org.myco.medical.service;

import org.alfresco.util.ParameterCheck;
import org.apache.log4j.Logger;
import org.myco.medical.bean.DocumentIdentifiers;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Medical file name service
 */
@Service
public class MedicalFileNameService
{
  private Logger logger = Logger.getLogger(MedicalFileNameService.class);

  public static final String SEPARATOR = ";";
  public static final String EXPECTED_FORMAT = "personId;documentCode;effectiveDate";
  public static final String STRING_DATE_FORMAT = "dd-MM-yyyy";
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(STRING_DATE_FORMAT);
  public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

  /**
   * generate unique file name based on timestamp
   * @return
   */
  public String getGenerateName(){
    return "Medical_document_" + FULL_DATE_FORMAT.format(new Date());
  }
  
  /**
   * This method parses a formatted file name into a DocumentIdentifiers object
   *
   * @param formattedFileName file name that respects the pattern EXPECTED_FORMAT
   * @return DocumentIdentifiers associated
   */
  public DocumentIdentifiers parseFormattedFileName(String formattedFileName)
  {
    ParameterCheck.mandatory("formattedFileName", formattedFileName);

    DocumentIdentifiers documentIdentifiers = new DocumentIdentifiers();

    String[] tokens = formattedFileName.split(SEPARATOR);

    if (tokens.length != 3)
    {
      logger.info("The file name [" + formattedFileName + "] does not respect the format: " + EXPECTED_FORMAT);
      return documentIdentifiers;
    }

    setPersonId(tokens[0], documentIdentifiers);
    setDocumentTypeCode(tokens[1], documentIdentifiers);
    setEffectiveDate(tokens[2], documentIdentifiers);
    documentIdentifiers.setFileName(getGenerateName());

    return documentIdentifiers;
  }

  private void setDocumentTypeCode(String token, DocumentIdentifiers documentIdentifiers)
  {
    documentIdentifiers.setDocumentTypeCode(token);
  }

  private void setEffectiveDate(String token, DocumentIdentifiers documentIdentifiers)
  {
    try
    {
      documentIdentifiers.setEffectiveDate(DATE_FORMAT.parse(token));
    }
    catch (ParseException e)
    {
      logger.debug("Wrong date format: " + token + "Expected " + STRING_DATE_FORMAT, e);
    }
  }

  private void setPersonId(String token, DocumentIdentifiers documentIdentifiers)
  {
    try
    {
      documentIdentifiers.setPersonId(Long.valueOf(token));
    }
    catch (NumberFormatException e)
    {
      logger.debug("Wrong person id format: " + token, e);
    }
  }
  

}
