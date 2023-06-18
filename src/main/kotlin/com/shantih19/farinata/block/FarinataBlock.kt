package com.shantih19.farinata.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.block.Material

var farinataBlockSettings: FabricBlockSettings = FabricBlockSettings.of(Material.Builder(MapColor.PALE_YELLOW).burnable().destroyedByPiston().notSolid().build())

object FarinataBlock: Block(farinataBlockSettings) {

}