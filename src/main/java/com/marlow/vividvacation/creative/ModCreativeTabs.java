

package com.marlow.vividvacation.creative;

import com.marlow.vividvacation.MarlowsVividVacation;
import com.marlow.vividvacation.item.ModItems;
import com.marlow.vividvacation.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    MarlowsVividVacation.MODID
            );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            CREATIVE_MODE_TABS.register("main", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable(
                                    "itemGroup.marlowsvividvacation"
                            ))
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .icon(() ->
                                    ModItems.SAND_BUCKET.get().getDefaultInstance()
                            )
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.SAND_BUCKET.get());
                                output.accept(ModItems.MAGICAL_SAND_BUCKET.get());
                                output.accept(ModBlocks.TROPICAL_SAND.get());
                                output.accept(ModBlocks.TROPICAL_SANDSTONE.get());
                                output.accept(ModBlocks.OCEANIC_STONE.get());
                                output.accept(ModBlocks.PALM_LOG.get());
                                output.accept(ModBlocks.PALM_LEAVES.get());

                            })
                            .build()
            );
}
