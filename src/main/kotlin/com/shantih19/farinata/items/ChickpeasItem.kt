package com.shantih19.farinata

import net.minecraft.item.Item
import net.minecraft.item.FoodComponent
import net.fabricmc.fabric.api.item.v1.FabricItemSettings

val chickpeasFoodComponent = FoodComponent.Builder().saturationModifier(2.4f).hunger(2).snack().build()

val chickpeasSettings = FabricItemSettings().food(chickpeasFoodComponent)

object ChickpeasItem : Item(chickpeasSettings) {

}