
package com.marlow.vividvacation.item;

import com.marlow.vividvacation.MarlowsVividVacation;
import com.marlow.vividvacation.item.MagicalSandBucketItem;
import com.marlow.vividvacation.block.ModBlocks;


import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.BlockItem;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MarlowsVividVacation.MODID);

    // Our very first item!


    public static final DeferredItem<SandBucketItem> SAND_BUCKET =
            ITEMS.registerItem(
                    "sand_bucket",
                    SandBucketItem::new,
                    new Item.Properties()
                            .stacksTo(1)
                            .food(new FoodProperties.Builder()
                                    .nutrition(0)
                                    .saturationModifier(0.0f)
                                    .alwaysEdible()
                                    .effect(
                                            () -> new MobEffectInstance(
                                                    MobEffects.CONFUSION,
                                                    200,
                                                    0
                                            ),
                                            1.0f
                                    )
                                    .usingConvertsTo(Items.BUCKET)
                                    .build()
                            )
            );

    public static final DeferredItem<MagicalSandBucketItem> MAGICAL_SAND_BUCKET =
            ITEMS.registerItem(
                    "magical_sand_bucket",
                    MagicalSandBucketItem::new,
                    new Item.Properties().stacksTo(1)
            );

    public static final java.util.function.Supplier<
            net.minecraft.world.item.BlockItem> TROPICAL_SAND =
            ITEMS.registerSimpleBlockItem(ModBlocks.TROPICAL_SAND
            );


    public static final Supplier<BlockItem> TROPICAL_SANDSTONE =
            ITEMS.registerSimpleBlockItem(
                    ModBlocks.TROPICAL_SANDSTONE
            );

    public static final Supplier<BlockItem> OCEANIC_STONE =
            ITEMS.registerSimpleBlockItem(
                    ModBlocks.OCEANIC_STONE
            );

    public static final Supplier<BlockItem> PALM_LOG =
            ITEMS.registerSimpleBlockItem(
                    ModBlocks.PALM_LOG
            );

    public static final Supplier<BlockItem> PALM_LEAVES =
            ITEMS.registerSimpleBlockItem(
                    ModBlocks.PALM_LEAVES
            );

    public static final Supplier<BlockItem> PINEAPPLE_BLOCK =
            ITEMS.registerSimpleBlockItem(
                    ModBlocks.PINEAPPLE_BLOCK
            );

    // Like a melon slice: small, cheap food
    public static final DeferredItem<Item> PINEAPPLE_SLICE =
            ITEMS.registerSimpleItem(
                    "pineapple_slice",
                    new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(2)
                                    .saturationModifier(0.3f)
                                    .build()
                            )
            );








}
