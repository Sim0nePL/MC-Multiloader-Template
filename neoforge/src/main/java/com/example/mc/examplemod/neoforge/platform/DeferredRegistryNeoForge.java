package com.example.mc.examplemod.neoforge.platform;

import com.example.mc.examplemod.platform.modloader.DeferredRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DeferredRegistryNeoForge<T> implements DeferredRegistry<T> {
    private final DeferredRegister<T> deferredRegister;

    public DeferredRegistryNeoForge(ResourceKey<? extends Registry<T>> key, String modId) {
        deferredRegister = DeferredRegister.create(key, modId);
    }

    @Override
    public <I extends T> Supplier<I> register(String name, Supplier<I> supplier) {
        return deferredRegister.register(name, supplier);
    }

    @Override
    public void registerToBus(Object eventBus) {
        if(eventBus instanceof IEventBus bus) {
            deferredRegister.register(bus);
        }
    }

}
