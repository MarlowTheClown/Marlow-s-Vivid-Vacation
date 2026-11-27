
package com.marlow.vividvacation.client;

import com.marlow.vividvacation.MarlowsVividVacation;
import com.marlow.vividvacation.sound.ModSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.Level;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.SelectMusicEvent;

@EventBusSubscriber(
        modid = MarlowsVividVacation.MODID,
        value = Dist.CLIENT
)
public class ArchipelagoMusicHandler {

    private static final ResourceKey<Level> ARCHIPELAGO =
            ResourceKey.create(
                    Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(
                            MarlowsVividVacation.MODID,
                            "the_archipelago"
                    )
            );

    private static final Music ARCHIPELAGO_MUSIC =
            new Music(
                    ModSounds.ARCHIPELAGO_MUSIC,
                    200,
                    600,
                    true
            );

    @SubscribeEvent
    public static void onSelectMusic(SelectMusicEvent event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null) {
            return;
        }

        if (minecraft.level.dimension().equals(ARCHIPELAGO)) {
            event.overrideMusic(ARCHIPELAGO_MUSIC);
        }
    }
}
