package com.shantih19.farinata

import com.shantih19.farinata.block.ChickpeaCropBlock
import com.shantih19.farinata.block.FarinataBlock
import com.shantih19.farinata.items.ChickpeaSlurryItem
import com.shantih19.farinata.items.ChickpeasItem
import com.shantih19.farinata.items.FarinataItem
import com.shantih19.farinata.items.RoastedChickpeasItem
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object FarinataMod : ModInitializer {
    val logger = LoggerFactory.getLogger("farinata")


    @JvmField
    val CHICKPEAS: ChickpeasItem =
        Registry.register(Registries.ITEM, Identifier("farinata", "chickpeas"), ChickpeasItem)

    @JvmField
    val CHICKPEA_FLOUR: ChickpeaFlourItem =
        Registry.register(Registries.ITEM, Identifier("farinata", "chickpeaflour"), ChickpeaFlourItem)

    @JvmField
    val CHICKPEA_SLURRY: ChickpeaSlurryItem =
        Registry.register(Registries.ITEM, Identifier("farinata", "chickpeaslurry"), ChickpeaSlurryItem)

    @JvmField
    val ROASTED_CHICKPEAS: RoastedChickpeasItem =
        Registry.register(Registries.ITEM, Identifier("farinata", "roastedchickpeas"), RoastedChickpeasItem)

    @JvmField
    val FARINATA_ITEM: FarinataItem =
        Registry.register(Registries.ITEM, Identifier("farinata", "farinata"), FarinataItem)

    @JvmField
    val FARINATA_BLOCK: FarinataBlock =
        Registry.register(Registries.BLOCK, Identifier("farinata", "farinata"), FarinataBlock)

    @JvmField
    val CHICKPEA_CROP: ChickpeaCropBlock =
        Registry.register(Registries.BLOCK, Identifier("farinata", "chickpeacrop"), ChickpeaCropBlock)

    override fun onInitialize() {
        logger.info("Farinata time!")
        CompostingChanceRegistry.INSTANCE.add(CHICKPEAS, 0.2f)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register(ModifyEntries { content: FabricItemGroupEntries ->
                content.add(CHICKPEAS)
                content.add(ROASTED_CHICKPEAS)
                content.add(CHICKPEA_FLOUR)
                content.add(CHICKPEA_SLURRY)
                content.add(FARINATA_ITEM)
            })
        logger.info("Registering recipe serializer")
        Registry.register(
            Registries.RECIPE_SERIALIZER,
            Identifier("farinata:farinata_recipe"),
            FarinataRecipeSerializer
        )
        logger.info("Registering recipe type")
        Registry.register(Registries.RECIPE_TYPE, Identifier("farinata", "farinata_recipe"), FarinataRecipeType)
    }

}