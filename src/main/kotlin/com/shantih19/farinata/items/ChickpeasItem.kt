package com.shantih19.farinata.items

import net.minecraft.item.Item
import net.minecraft.item.FoodComponent
import net.fabricmc.fabric.api.item.v1.FabricItemSettings

val chickpeasFoodComponent: FoodComponent = FoodComponent.Builder().saturationModifier(1.2f).hunger(2).snack().build()

val chickpeasSettings: FabricItemSettings = FabricItemSettings().food(chickpeasFoodComponent)

object ChickpeasItem : Item(chickpeasSettings) {

}