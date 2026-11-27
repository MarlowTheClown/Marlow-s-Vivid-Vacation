
package com.marlow.vividvacation.event;

import com.marlow.vividvacation.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class SandBucketInteraction {

    public static void onRightClickBlock(
            PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        ItemStack heldItem = event.getItemStack();

        // The player must be sneaking.
        if (!player.isShiftKeyDown()) {
            return;
        }

        // The player must hold a vanilla empty bucket.
        if (!heldItem.is(Items.BUCKET)) {
            return;
        }

        // Only regular vanilla sand can be collected.
        if (!level.getBlockState(pos).is(Blocks.SAND)) {
            return;
        }

        // Respect basic com.marlow.vividvacation.block-interaction permissions.
        if (!level.mayInteract(player, pos)) {
            return;
        }

        // Prevent normal right-click behavior.
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        // Only the server changes blocks and inventory.
        if (level.isClientSide()) {
            return;
        }

        // Remove one sand com.marlow.vividvacation.block without dropping sand.
        if (!level.removeBlock(pos, false)) {
            return;
        }

        // Fill one bucket, handling stacked buckets too.
        ItemStack filledBucket = ItemUtils.createFilledResult(
                heldItem,
                player,
                new ItemStack(ModItems.SAND_BUCKET.get())
        );

        player.setItemInHand(event.getHand(), filledBucket);
    }
}
