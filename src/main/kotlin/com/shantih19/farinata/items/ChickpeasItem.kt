package com.shantih19.farinata.items

import com.shantih19.farinata.block.ChickpeaCropBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent

val chickpeasFoodComponent: FoodComponent = FoodComponent.Builder().saturationModifier(0.4f).hunger(2).snack().build()

val chickpeasSettings: FabricItemSettings = FabricItemSettings().food(chickpeasFoodComponent)

object ChickpeasItem : BlockItem(ChickpeaCropBlock, chickpeasSettings)