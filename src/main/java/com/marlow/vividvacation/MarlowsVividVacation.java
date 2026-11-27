
package com.marlow.vividvacation;

import com.marlow.vividvacation.block.ModBlocks;
import com.marlow.vividvacation.creative.ModCreativeTabs;
import com.marlow.vividvacation.event.SandBucketInteraction;
import com.marlow.vividvacation.item.ModItems;
import com.marlow.vividvacation.worldgen.ModFoliagePlacers;
import com.marlow.vividvacation.sound.ModSounds;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MarlowsVividVacation.MODID)
public class MarlowsVividVacation {

    public static final String MODID = "marlowsvividvacation";

    public MarlowsVividVacation(IEventBus modEventBus) {

        ModBlocks.BLOCKS.register(modEventBus);

        ModFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);

        ModItems.ITEMS.register(modEventBus);

        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(
                SandBucketInteraction::onRightClickBlock
        );
    }
}
