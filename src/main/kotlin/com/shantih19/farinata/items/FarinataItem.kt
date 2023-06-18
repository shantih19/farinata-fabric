package com.shantih19.farinata.items

import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import com.shantih19.farinata.block.FarinataBlock

val farinataFoodComponent: FoodComponent = FoodComponent.Builder().saturationModifier(12f).hunger(10).build()

val farinataSettings: FabricItemSettings = FabricItemSettings().food(farinataFoodComponent)

object FarinataItem : BlockItem(FarinataBlock,farinataSettings) {

}