
package com.marlow.vividvacation.sound;

import com.marlow.vividvacation.MarlowsVividVacation;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(
                    BuiltInRegistries.SOUND_EVENT,
                    MarlowsVividVacation.MODID
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> ARCHIPELAGO_MUSIC =
            SOUND_EVENTS.register(
                    "archipelago_music",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath(
                                    MarlowsVividVacation.MODID,
                                    "archipelago_music"
                            )
                    )
            );
}
