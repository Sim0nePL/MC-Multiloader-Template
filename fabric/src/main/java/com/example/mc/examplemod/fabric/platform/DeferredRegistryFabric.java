package com.example.mc.examplemod.fabric.platform;

import com.example.mc.examplemod.platform.modloader.DeferredRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class DeferredRegistryFabric<T> implements DeferredRegistry<T> {
    private final Registry<T> registry;
    private final String modId;

    public DeferredRegistryFabric(ResourceKey<? extends Registry<T>> key, String modId) {
        this.registry = (Registry<T>) BuiltInRegistries.REGISTRY.get((ResourceKey) key);
        this.modId = modId;

    }

    @Override
    public <I extends T> Supplier<I> register(String name, Supplier<I> supplier) {
        I object = Registry.register(registry, ResourceLocation.fromNamespaceAndPath(modId, name), supplier.get());
        return () -> object;
    }

    @Override
    public void registerToBus(Object eventBus) {
        // Useless on Fabric
    }
}
