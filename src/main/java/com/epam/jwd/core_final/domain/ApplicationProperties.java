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

    private final static String inputRootDir = PropertyReaderUtil.getProperties().getProperty("inputRootDir");
    private final static String outputRootDir = PropertyReaderUtil.getProperties().getProperty("outputRootDir");
    private final static String crewFileName = PropertyReaderUtil.getProperties().getProperty("crewFileName");
    private final static String missionsFileName = PropertyReaderUtil.getProperties().getProperty("missionsFileName");
    private final static String spaceshipsFileName = PropertyReaderUtil.getProperties().getProperty("spaceshipsFileName");
    private final static Integer fileRefreshRate = Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("fileRefreshRate"));
    private final static String dateTimeFormat = PropertyReaderUtil.getProperties().getProperty("dateTimeFormat");

    public static String getInputRootDir() {
        return inputRootDir;
    }

    public static String getOutputRootDir() {
        return outputRootDir;
    }

    public static String getCrewFileName() {
        return crewFileName;
    }

    public static String getMissionsFileName() {
        return missionsFileName;
    }

    public static Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public static String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public static String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }
}
