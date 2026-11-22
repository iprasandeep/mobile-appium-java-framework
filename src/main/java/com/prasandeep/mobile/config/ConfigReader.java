package com.prasandeep.mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

/**
 * Loads framework settings from classpath config, optional local overrides, and env vars.
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        load("config.properties");
        loadOptionalResource("config.local.properties");
    }

    private ConfigReader() {
    }

    private static void load(String resourceName) {
        try (InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (stream == null) {
                throw new IllegalStateException("Missing classpath resource: " + resourceName);
            }
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + resourceName, e);
        }
    }

    private static void loadOptionalResource(String resourceName) {
        try (InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (stream != null) {
                PROPERTIES.load(stream);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load optional " + resourceName, e);
        }
    }

    public static String get(String key) {
        return resolve(key).orElseThrow(() -> new IllegalStateException("Missing config key: " + key));
    }

    public static Optional<String> getOptional(String key) {
        return resolve(key).filter(value -> !value.isBlank() && !"optional".equalsIgnoreCase(value));
    }

    private static Optional<String> resolve(String key) {
        String envKey = key.toUpperCase().replace('.', '_');
        String fromEnv = System.getenv(envKey);
        if (fromEnv != null && !fromEnv.isBlank()) {
            return Optional.of(fromEnv.trim());
        }
        String fromSystem = System.getProperty(key);
        if (fromSystem != null && !fromSystem.isBlank()) {
            return Optional.of(fromSystem.trim());
        }
        String fromFile = PROPERTIES.getProperty(key);
        if (fromFile == null) {
            return Optional.empty();
        }
        return Optional.of(fromFile.trim());
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static Path resolveAppPath() {
        String configured = get("app.path");
        Path path = Paths.get(configured);
        if (!path.isAbsolute()) {
            path = Paths.get(System.getProperty("user.dir")).resolve(path).normalize();
        }
        if (!Files.exists(path)) {
            throw new IllegalStateException(
                    "APK not found at " + path + ". Place Sauce Labs My Demo App Android APK under apps/ "
                            + "or set app.path / APP_PATH. See README.");
        }
        return path;
    }
}
