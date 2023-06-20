package com.shantih19.farinata.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item

val sickEffect = StatusEffectInstance(StatusEffects.NAUSEA, 100)

val chickpeaSlurryFoodComponent: FoodComponent =
    FoodComponent.Builder().saturationModifier(0.1f).hunger(1).alwaysEdible().statusEffect(
        sickEffect, 1f
    ).build()

val chickpeaSlurrySettings: FabricItemSettings = FabricItemSettings().food(chickpeaSlurryFoodComponent)

object ChickpeaSlurryItem : Item(chickpeaSlurrySettings)