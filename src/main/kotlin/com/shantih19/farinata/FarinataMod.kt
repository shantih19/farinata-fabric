package com.shantih19.farinata

import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.registry.Registry
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import org.slf4j.LoggerFactory

object FarinataMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("farinata")


    @JvmField public val CHICKPEAS = Registry.register(Registries.ITEM, Identifier("farinata", "chickpeas"), ChickpeasItem)
    @JvmField public val CHICKPEA_FLOUR = Registry.register(Registries.ITEM, Identifier("farinata", "chickpeaflour"), ChickpeaFlourItem)
    @JvmField public val CHICKPEA_SLURRY = Registry.register(Registries.ITEM, Identifier("farinata", "chickpeaslurry"), ChickpeaSlurryItem)

    override fun onInitialize() {
        CompostingChanceRegistry.INSTANCE.add(CHICKPEAS, 0.2f)
    }

}