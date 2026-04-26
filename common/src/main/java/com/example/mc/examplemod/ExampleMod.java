package com.example.mc.examplemod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "example";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("{} initializing", MOD_ID);
    }
}
