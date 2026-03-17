package com.apps.quantitymeasurement.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * ApplicationConfig
 *
 * Centralized configuration loader for the Quantity Measurement Application.
 *
 * Responsibilities:
 * - Load application properties from classpath
 * - Provide typed access to properties
 * - Support repository switching through configuration
 * - Provide database connection settings to utility/repository layers
 *
 * Architectural Role:
 * - Utility/configuration class
 * - Used by Application entry point and database utility classes
 */
public class ApplicationConfig {

    private static final Logger logger = Logger.getLogger(ApplicationConfig.class.getName());
    private static final String CONFIG_FILE = "application.properties";

    private static ApplicationConfig instance;

    private final Properties properties;

    private ApplicationConfig() {
        properties = new Properties();
        loadConfiguration();
    }

    public static synchronized ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    /**
     * Loads configuration from application.properties.
     */
    private void loadConfiguration() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Application configuration loaded successfully from " + CONFIG_FILE);
            } else {
                logger.warning("Configuration file not found: " + CONFIG_FILE + ". Using defaults.");
                loadDefaultConfiguration();
            }
        } catch (IOException exception) {
            logger.warning("Error loading configuration. Using defaults. Reason: " + exception.getMessage());
            loadDefaultConfiguration();
        }
    }

    /**
     * Loads default fallback configuration.
     */
    private void loadDefaultConfiguration() {
        properties.setProperty("repository.type", "cache");
        properties.setProperty("db.url", "jdbc:h2:./quantitymeasurementdb;AUTO_SERVER=TRUE");
        properties.setProperty("db.username", "sa");
        properties.setProperty("db.password", "");
        properties.setProperty("db.driver", "org.h2.Driver");
        properties.setProperty("db.pool.size", "3");
        logger.info("Default configuration initialized.");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        try {
            return value == null ? defaultValue : Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }

    /**
     * Returns repository type.
     *
     * Expected values:
     * - cache
     * - database
     *
     * @return repository type
     */
    public String getRepositoryType() {
        return getProperty("repository.type", "cache");
    }
}