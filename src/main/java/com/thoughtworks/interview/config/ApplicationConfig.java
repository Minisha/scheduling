package com.thoughtworks.interview.config;

import io.advantageous.config.Config;
import io.advantageous.config.ConfigLoader;

public class ApplicationConfig {

    public static Config config() {
        String configFile = System.getProperty("config-file", "application-config.js");
        return ConfigLoader.load(configFile);
    }
}
