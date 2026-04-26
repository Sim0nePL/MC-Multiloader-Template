package com.example.mc.examplemod.items;

import com.example.mc.examplemod.ExampleMod;
import com.example.mc.examplemod.platform.ModLoader;
import com.example.mc.examplemod.platform.modloader.DeferredRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ExampleModItems {
    public static final DeferredRegistry<Item> ITEMS = ModLoader.PLATFORM.createRegistry(Registries.ITEM, ExampleMod.MOD_ID);

    public static void init() {
        ExampleMod.LOGGER.info("{} items initialization", ExampleMod.MOD_ID);

        Supplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties()));
    }
}
