package com.shantih19.farinata


import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.system.measureNanoTime


object FarinataFeature: Feature<DefaultFeatureConfig>(DefaultFeatureConfig.CODEC) {
    override fun generate(context: FeatureContext<DefaultFeatureConfig>): Boolean {
        val world: StructureWorldAccess = context.world
        val origin: BlockPos = context.origin

        val crop: BlockState = FarinataMod.CHICKPEA_CROP.withAge(7)
        val soil: BlockState = Blocks.FARMLAND.defaultState

        var testpos: BlockPos = BlockPos(origin)
        for (y in 0 until world.height) {
            testpos = testpos.up();
            if (world.getBlockState(testpos).isIn(BlockTags.DIRT)) {
                if (world.getBlockState(testpos.up()).isOf(Blocks.AIR)) {
                    world.setBlockState(testpos.up(), crop, Block.NOTIFY_LISTENERS)
                    world.setBlockState(testpos, soil, Block.NOTIFY_LISTENERS)
                    return true
                }
            }
        }
        return false
    }
}