package com.shantih19.farinata.items

import net.minecraft.item.Item
import net.minecraft.item.FoodComponent
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

val sickEffect = StatusEffectInstance(StatusEffects.NAUSEA, 100)

val chickpeaSlurryFoodComponent: FoodComponent = FoodComponent.Builder().saturationModifier(0.1f).hunger(1).alwaysEdible().statusEffect(
    sickEffect, 1f).build()

val chickpeaSlurrySettings: FabricItemSettings = FabricItemSettings().food(chickpeaSlurryFoodComponent)

object ChickpeaSlurryItem : Item(chickpeaSlurrySettings) {

}