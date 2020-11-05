package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    //todo

    private final String inputRootDir = PropertyReaderUtil.getProperties().getProperty("inputRootDir");
    private final String outputRootDir = PropertyReaderUtil.getProperties().getProperty("outputRootDir");
    private final String crewFileName = PropertyReaderUtil.getProperties().getProperty("crewFileName");
    private final String missionsFileName = PropertyReaderUtil.getProperties().getProperty("missionsFileName");
    private final Integer fileRefreshRate = Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("fileRefreshRate"));
    private final String dateTimeFormat = PropertyReaderUtil.getProperties().getProperty("dateTimeFormat");

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
