package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH =  "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE_PATH =  "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INVALID_DELIMITER_FILE_PATH =  "./src/test/resources/invalidDelimitersIndiaStateCensusData.csv";
    private static final String INVALID_HEADER_FILE_PATH =  "./src/test/resources/invalidHeadersIndiaStateCensusData.csv";
    //the number of records should matcher
    @Test
    public void  givenStatesCensusCSVFileReturnsCorrectRecords() {
        try {
            //Creating object for censusAnalyser class
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            //Calling loadIndiaCensusData method here
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println("CSV File:"+ numOfRecords);
            Assert.assertEquals(29,numOfRecords);
        }
        catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenCensusCSVFileIfIncorrectThenReturnsException() {
        //Creating object for censusAnalyser class
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException exceptionRule =  ExpectedException.none();
        //Calling CensusAnalyserException class
        exceptionRule.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        }
        catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenCensusCSVFileCorrectButTypeIncorrectReturnsException() {
        //Creating object for censusAnalyser class
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException exceptionRule =  ExpectedException.none();
        exceptionRule.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE_PATH);
        }
        catch (CensusAnalyserException e) {
            Assert.assertNotEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCSVFileCorrectButDelimiterIncorrectReturnsException() {
        //Creating object for censusAnalyser class
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException exceptionRule =  ExpectedException.none();
        exceptionRule.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(INVALID_DELIMITER_FILE_PATH);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertNotEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCSVFileCorrectButCsvHeaderIncorrectReturnsException() {
        //Creating object for censusAnalyser class
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(INVALID_HEADER_FILE_PATH);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertNotEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE_OR_DELIMITER_OR_HEADER, e.type);
        }
    }
}
