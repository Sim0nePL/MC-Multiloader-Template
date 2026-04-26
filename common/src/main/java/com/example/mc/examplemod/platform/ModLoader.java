package com.example.mc.examplemod.platform;

import com.example.mc.examplemod.ExampleMod;
import com.example.mc.examplemod.platform.modloader.IPlatformHelper;

import java.util.ServiceLoader;

public class ModLoader {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> ServiceClass) {
        final T loadedService = ServiceLoader.load(ServiceClass, ModLoader.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + ServiceClass.getName()));
        ExampleMod.LOGGER.debug("Loaded {} for service {}", loadedService, ServiceClass);
        return loadedService;
    }

}
