
package com.marlow.vividvacation.worldgen.foliage;

import com.marlow.vividvacation.worldgen.ModFoliagePlacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmFoliagePlacer> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    foliagePlacerParts(instance).apply(
                            instance,
                            PalmFoliagePlacer::new
                    )
            );

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PALM_FOLIAGE.get();
    }

    @Override
    public int foliageHeight(
            RandomSource random,
            int trunkHeight,
            TreeConfiguration config
    ) {
        return 4;
    }

    @Override
    protected boolean shouldSkipLocation(
            RandomSource random,
            int localX,
            int localY,
            int localZ,
            int range,
            boolean large
    ) {
        return false;
    }


    @Override
    protected void createFoliage(
            LevelSimulatedReader level,
            FoliageSetter setter,
            RandomSource random,
            TreeConfiguration config,
            int maxFreeTreeHeight,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        BlockPos center = attachment.pos().above(offset);

        // Keep the crown small.
        tryPlaceLeaf(level, setter, random, config, center);
        tryPlaceLeaf(level, setter, random, config, center.above());

        // Eight fronds radiating from one crown.
        int[][] directions = {
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1}
        };

        for (int[] direction : directions) {
            int dx = direction[0];
            int dz = direction[1];

            boolean diagonal = dx != 0 && dz != 0;

            // Longer fronds, with slight random variation.
            int length = foliageRadius + random.nextInt(2);

            // Diagonal fronds are slightly shorter.
            if (diagonal) {
                length--;
            }

            for (int step = 1; step <= length; step++) {

                int x = dx * step;
                int z = dz * step;

                // Fronds initially rise, then droop.
                int y;

                if (step <= 2) {
                    y = 1;
                } else if (step == 3) {
                    y = 0;
                } else if (step == 4) {
                    y = -1;
                } else if (step == 5) {
                    y = -2;
                } else {
                    y = -3;
                }

                // The main thin spine.
                tryPlaceLeaf(
                        level, setter, random, config,
                        center.offset(x, y, z)
                );

                // Add some width in the middle,
                // leaving the outer tips narrow.
                boolean addLeaflets =
                        (!diagonal && (step == 3 || step == 4))
                                || (diagonal && step == 3);

                if (addLeaflets) {

                    // Left leaflet.
                    tryPlaceLeaf(
                            level, setter, random, config,
                            center.offset(
                                    x - dz,
                                    y,
                                    z + dx
                            )
                    );

                    // Right leaflet.
                    tryPlaceLeaf(
                            level, setter, random, config,
                            center.offset(
                                    x + dz,
                                    y,
                                    z - dx
                            )
                    );
                }

                // Give height changes a short connecting segment.
                if (step >= 3) {
                    tryPlaceLeaf(
                            level, setter, random, config,
                            center.offset(
                                    x - dx,
                                    y,
                                    z - dz
                            )
                    );
                }
            }

            // Distinct hanging leaf at the frond tip.
            int tipY = length >= 6 ? -4 : -3;

            tryPlaceLeaf(
                    level, setter, random, config,
                    center.offset(
                            dx * length,
                            tipY,
                            dz * length
                    )
            );
        }
    }
}