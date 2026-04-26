package com.example.mc.examplemod.platform.modloader;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface IPlatformHelper {
    <T> DeferredRegistry<T> createRegistry(ResourceKey<? extends Registry<T>> key, String modId);
}
