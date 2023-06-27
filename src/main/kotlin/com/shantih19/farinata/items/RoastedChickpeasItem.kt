package com.shantih19.farinata.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item

val roastedChickpeasFoodComponent: FoodComponent =
    FoodComponent.Builder().saturationModifier(0.6f).hunger(4).snack().build()

val roastedChickpeasSettings: FabricItemSettings = FabricItemSettings().food(roastedChickpeasFoodComponent)

object RoastedChickpeasItem : Item(roastedChickpeasSettings)