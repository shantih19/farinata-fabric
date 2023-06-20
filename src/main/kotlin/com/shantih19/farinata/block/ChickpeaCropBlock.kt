package com.shantih19.farinata.block

import com.shantih19.farinata.FarinataMod
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.ItemConvertible
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

var chickpeasCropSettings: FabricBlockSettings =
    FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(
        BlockSoundGroup.CROP
    )

object ChickpeaCropBlock : CropBlock(chickpeasCropSettings) {
    override fun getSeedsItem(): ItemConvertible {
        return FarinataMod.CHICKPEAS
    }

    override fun getOutlineShape(
        state: BlockState, world: BlockView?, pos: BlockPos?, context: ShapeContext?
    ): VoxelShape {
        return AGE_TO_SHAPE[(state.get(this.ageProperty) as Int)]
    }

    private val AGE_TO_SHAPE = arrayOf<VoxelShape>(
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
        Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    )


}