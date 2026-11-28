
package com.marlow.vividvacation.block;

import com.marlow.vividvacation.MarlowsVividVacation;

import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(
                    MarlowsVividVacation.MODID
            );

    public static final DeferredBlock<ColoredFallingBlock> TROPICAL_SAND =
            BLOCKS.registerBlock(
                    "tropical_sand",
                    properties -> new ColoredFallingBlock(
                            new ColorRGBA(0xE8D9B0FF),
                            properties
                    ),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
            );

    public static final DeferredBlock<Block> TROPICAL_SANDSTONE =
            BLOCKS.registerSimpleBlock(
                    "tropical_sandstone",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
            );

    public static final DeferredBlock<Block> OCEANIC_STONE =
            BLOCKS.registerSimpleBlock(
                    "oceanic_stone",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            );

    public static final DeferredBlock<RotatedPillarBlock> PALM_LOG =
            BLOCKS.registerBlock(
                    "palm_log",
                    RotatedPillarBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            );

    public static final DeferredBlock<LeavesBlock> PALM_LEAVES =
            BLOCKS.registerBlock(
                    "palm_leaves",
                    LeavesBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            );

    // Pineapple block: melon-like behaviour (strength, sound, push reaction), but yellow
    public static final DeferredBlock<Block> PINEAPPLE_BLOCK =
            BLOCKS.registerSimpleBlock(
                    "pineapple_block",
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MELON)
                            .mapColor(MapColor.COLOR_YELLOW)
            );



}
