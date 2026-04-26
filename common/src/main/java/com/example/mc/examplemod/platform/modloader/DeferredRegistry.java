package com.example.mc.examplemod.platform.modloader;

import java.util.function.Supplier;

public interface DeferredRegistry<T> {
    <I extends T> Supplier<I> register(String name, Supplier<I> supplier);

    void registerToBus(Object eventBus);
}

