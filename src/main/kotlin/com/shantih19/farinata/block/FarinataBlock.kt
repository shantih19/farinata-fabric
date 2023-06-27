package com.shantih19.farinata.block

import com.google.common.collect.ImmutableMap
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.slf4j.LoggerFactory

var farinataBlockSettings: FabricBlockSettings =
    FabricBlockSettings.of(Material.Builder(MapColor.PALE_YELLOW).burnable().destroyedByPiston().notSolid().build())

var voxelOne: VoxelShape = VoxelShapes.union(
    VoxelShapes.cuboid(0.125, 0.0, 0.125, 0.5, 0.0625, 0.5),
    VoxelShapes.cuboid(0.3125, 0.0, 0.0, 0.5, 0.0625, 0.125),
    VoxelShapes.cuboid(0.0, 0.0, 0.3125, 0.125, 0.0625, 0.5),
    VoxelShapes.cuboid(0.1875, 0.0, 0.0625, 0.3125, 0.0625, 0.125),
    VoxelShapes.cuboid(0.0625, 0.0, 0.1875, 0.125, 0.0625, 0.3125)
)
var voxelTwo: VoxelShape = VoxelShapes.union(
    voxelOne,
    VoxelShapes.cuboid(0.5, 0.0, 0.125, 0.875, 0.0625, 0.5),
    VoxelShapes.cuboid(0.875, 0.0, 0.3125, 1.0, 0.0625, 0.5),
    VoxelShapes.cuboid(0.5, 0.0, 0.0, 0.6875, 0.0625, 0.125),
    VoxelShapes.cuboid(0.875, 0.0, 0.1875, 0.9375, 0.0625, 0.3125),
    VoxelShapes.cuboid(0.6875, 0.0, 0.0625, 0.8125, 0.0625, 0.125)
)
var voxelThree: VoxelShape = VoxelShapes.union(
    voxelTwo,
    VoxelShapes.cuboid(0.5, 0.0, 0.5, 0.875, 0.0625, 0.875),
    VoxelShapes.cuboid(0.5, 0.0, 0.875, 0.6875, 0.0625, 1.0),
    VoxelShapes.cuboid(0.875, 0.0, 0.5, 1.0, 0.0625, 0.6875),
    VoxelShapes.cuboid(0.6875, 0.0, 0.875, 0.8125, 0.0625, 0.9375),
    VoxelShapes.cuboid(0.875, 0.0, 0.6875, 0.9375, 0.0625, 0.8125)
)
var voxelFour: VoxelShape = VoxelShapes.union(
    voxelThree,
    VoxelShapes.cuboid(0.125, 0.0, 0.5, 0.5, 0.0625, 0.875),
    VoxelShapes.cuboid(0.0, 0.0, 0.5, 0.125, 0.0625, 0.6875),
    VoxelShapes.cuboid(0.3125, 0.0, 0.875, 0.5, 0.0625, 1.0),
    VoxelShapes.cuboid(0.0625, 0.0, 0.6875, 0.125, 0.0625, 0.8125),
    VoxelShapes.cuboid(0.1875, 0.0, 0.875, 0.3125, 0.0625, 0.9375)
)

@JvmField
var BITES: IntProperty = Properties.BITES

object FarinataBlock : Block(farinataBlockSettings) {

    @JvmField
    var SHAPES: ImmutableMap<BlockState, VoxelShape> = ImmutableMap.of(
        defaultState.with(BITES, 4), voxelFour,
        defaultState.with(BITES, 3), voxelThree,
        defaultState.with(BITES, 2), voxelTwo,
        defaultState.with(BITES, 1), voxelOne,
        defaultState.with(BITES, 0), voxelOne,
    )

    init {
        super.setDefaultState(defaultState.with(BITES, 4))
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(BITES)
    }

    override fun onUse(
        state: BlockState?, world: World?, pos: BlockPos?, player: PlayerEntity?, hand: Hand?, hit: BlockHitResult?
    ): ActionResult {
        if (world?.isClient == false) {
            var bites = world.getBlockState(pos)?.get(BITES)
            if (bites!! > 0) {
                player?.hungerManager?.add(5, 1.2f)
                world.setBlockState(pos, state?.with(BITES, bites - 1))
            }
            bites = world.getBlockState(pos)?.get(BITES)
            if (bites!! == 0) {
                world.breakBlock(pos, false)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun getOutlineShape(
        state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?
    ): VoxelShape {
        val shape: VoxelShape? = SHAPES[state]
        if (shape == null) {
            return VoxelShapes.fullCube()
        }
        return shape
    }

}