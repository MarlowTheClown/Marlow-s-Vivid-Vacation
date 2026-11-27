
package com.marlow.vividvacation.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SandBucketItem extends Item {

    public SandBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            Item.TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
                "tooltip.marlowsvividvacation.sand_bucket.line1"
        ).withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.translatable(
                "tooltip.marlowsvividvacation.sand_bucket.line2"
        ).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, context, tooltip, flag);
    }
}
