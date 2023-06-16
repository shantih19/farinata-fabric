package com.shantih19.farinata

import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.registry.Registry
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import org.slf4j.LoggerFactory

object FarinataMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("farinata")

    @JvmField public val CHICKPEAS_ITEM = Item(FabricItemSettings())

    override fun onInitialize() {
        Registry.register(Registries.ITEM, Identifier("farinata", "chickpeas"), CHICKPEAS_ITEM)
    }

}