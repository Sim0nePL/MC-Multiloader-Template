package com.example.mc.examplemod.fabric;

import com.example.mc.examplemod.ExampleMod;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
