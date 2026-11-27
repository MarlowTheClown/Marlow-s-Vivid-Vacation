
package com.marlow.vividvacation.item;

import com.marlow.vividvacation.MarlowsVividVacation;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Set;

public class MagicalSandBucketItem extends Item {

    // Our custom dimension.
    private static final ResourceKey<Level> ARCHIPELAGO =
            ResourceKey.create(
                    Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(
                            MarlowsVividVacation.MODID,
                            "the_archipelago"
                    )
            );

    public MagicalSandBucketItem(Properties properties) {
        super(properties);
    }

    // Always display the enchantment glimmer.
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    // Triggered by right-clicking with the item.
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            return InteractionResultHolder.success(stack);
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.fail(stack);
        }

        boolean inArchipelago =
                level.dimension().equals(ARCHIPELAGO);

        // Choose our destination dimension.
        ResourceKey<Level> destinationKey =
                inArchipelago ? Level.OVERWORLD : ARCHIPELAGO;

        ServerLevel destination =
                serverPlayer.getServer().getLevel(destinationKey);

        if (destination == null) {
            return InteractionResultHolder.fail(stack);
        }

        double x;
        double y;
        double z;

        if (inArchipelago) {
            // Return to the Overworld's shared spawn.
            BlockPos spawn = destination.getSharedSpawnPos();

            x = spawn.getX() + 0.5;
            y = spawn.getY() + 1;
            z = spawn.getZ() + 0.5;


        } else {
            // Our temporary Archipelago arrival point.
            // Load the destination chunk before reading its terrain.
            destination.getChunkAt(new BlockPos(0, 64, 0));

            // Find the top of the terrain at X=0, Z=0.
            int surfaceY = destination.getHeight(
                    Heightmap.Types.MOTION_BLOCKING,
                    0, 0
            );

            // Place the platform slightly above the terrain.
            int landingY = Math.min(
                    Math.max(surfaceY + 3,
                            destination.getMinBuildHeight() + 5),
                    destination.getMaxBuildHeight() - 5
            );

            // Build a 5x5 sandstone platform.
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {

                    BlockPos floor = new BlockPos(
                            dx, landingY - 1, dz
                    );

                    destination.setBlock(
                            floor,
                            Blocks.SANDSTONE.defaultBlockState(),
                            3
                    );

                    // Clear four blocks of headroom
                    // above every tile of the platform.
                    for (int dy = 0; dy < 4; dy++) {

                        BlockPos space = new BlockPos(
                                dx, landingY + dy, dz
                        );

                        destination.setBlock(
                                space,
                                Blocks.AIR.defaultBlockState(),
                                3
                        );
                    }
                }
            }

            // Place the player in the middle of the platform.
            x = 0.5;
            y = landingY;
            z = 0.5;
        }


        // Teleport the player across dimensions.
        boolean teleported = serverPlayer.teleportTo(
                destination,
                x, y, z,
                Set.of(),
                serverPlayer.getYRot(),
                serverPlayer.getXRot()
        );

        if (teleported) {
            serverPlayer.fallDistance = 0;
            serverPlayer.getCooldowns().addCooldown(this, 40);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.fail(stack);
    }
}