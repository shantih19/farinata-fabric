package com.shantih19.farinata

import com.shantih19.farinata.block.ChickpeaCropBlock
import com.shantih19.farinata.block.FarinataBlock
import com.shantih19.farinata.items.ChickpeaSlurryItem
import com.shantih19.farinata.items.ChickpeasItem
import com.shantih19.farinata.items.FarinataItem
import com.shantih19.farinata.items.RoastedChickpeasItem
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries
import net.fabricmc.fabric.api.registry.VillagerPlantableRegistry
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.village.TradeOfferList
import net.minecraft.village.TradeOffers
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.PlacedFeature
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


//    @JvmField
//    val CHICKPEA_CROP_FEATURE: RegistryKey<PlacedFeature> =
//        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier("farinata", "crop"))

    override fun onInitialize() {
        logger.info("Farinata time!")
        CompostingChanceRegistry.INSTANCE.add(CHICKPEAS, 0.2f)
        VillagerPlantableRegistry.register(CHICKPEAS)
        VillagerInteractionRegistries.registerFood(CHICKPEAS, 1)
        VillagerInteractionRegistries.registerCollectable(CHICKPEAS)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register(ModifyEntries { content: FabricItemGroupEntries ->
                content.add(CHICKPEAS)
                content.add(ROASTED_CHICKPEAS)
                content.add(CHICKPEA_FLOUR)
                content.add(CHICKPEA_SLURRY)
                content.add(FARINATA_ITEM)
            })
//        BiomeModifications.addFeature(            //Not working since crop needs farmland D:
//            BiomeSelectors.all(),
//            GenerationStep.Feature.SURFACE_STRUCTURES,
//            CHICKPEA_CROP_FEATURE
//        )
    }
    fun placeFeaturesInWorld() {

    }

}