
package com.marlow.vividvacation.worldgen;

import com.marlow.vividvacation.MarlowsVividVacation;
import com.marlow.vividvacation.worldgen.foliage.PalmFoliagePlacer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(
                    BuiltInRegistries.FOLIAGE_PLACER_TYPE,
                    MarlowsVividVacation.MODID
            );

    public static final DeferredHolder<
            FoliagePlacerType<?>,
            FoliagePlacerType<PalmFoliagePlacer>
            > PALM_FOLIAGE =
            FOLIAGE_PLACERS.register(
                    "palm_foliage",
                    () -> new FoliagePlacerType<>(
                            PalmFoliagePlacer.CODEC
                    )
            );
}
