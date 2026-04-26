package com.example.mc.examplemod.neoforge.platform;

import com.example.mc.examplemod.platform.modloader.DeferredRegistry;
import com.example.mc.examplemod.platform.modloader.IPlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class IPlatformHelperNeoForge implements IPlatformHelper {

    @Override
    public <T> DeferredRegistry<T> createRegistry(ResourceKey<? extends Registry<T>> key, String modId) {
        return new DeferredRegistryNeoForge<>(key, modId);
    }

}
