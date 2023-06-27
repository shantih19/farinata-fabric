package com.shantih19.farinata.items

import com.shantih19.farinata.block.FarinataBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent

val farinataFoodComponent: FoodComponent = FoodComponent.Builder().saturationModifier(1.7f).hunger(12).build()

val farinataSettings: FabricItemSettings = FabricItemSettings().food(farinataFoodComponent)

object FarinataItem : BlockItem(FarinataBlock, farinataSettings)