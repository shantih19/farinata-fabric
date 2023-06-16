package com.shantih19.farinata

import net.minecraft.item.Item
import net.minecraft.item.FoodComponent
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.fabricmc.fabric.api.item.v1.FabricItemSettings

val sickEffect = StatusEffectInstance(StatusEffects.NAUSEA,10,3)

val chickpeaSlurryFoodComponent = FoodComponent.Builder().saturationModifier(0.1f).hunger(1).alwaysEdible().statusEffect(sickEffect, 0.7f).build()

val chickpeaSlurrySettings = FabricItemSettings().food(chickpeaSlurryFoodComponent)

object ChickpeaSlurryItem : Item(chickpeaSlurrySettings) {

}