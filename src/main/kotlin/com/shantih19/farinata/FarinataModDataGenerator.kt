package com.shantih19.farinata

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.LootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootPoolEntry
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class FarinataRecipeGenerator(out: FabricDataOutput) : FabricRecipeProvider(out) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>?) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, FarinataMod.CHICKPEA_FLOUR).input(
            FarinataMod.CHICKPEAS).criterion(hasItem(FarinataMod.CHICKPEAS),
            conditionsFromItem(FarinataMod.CHICKPEAS)).offerTo(exporter)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, FarinataMod.CHICKPEA_SLURRY).input(
            FarinataMod.CHICKPEA_FLOUR).input(Items.WATER_BUCKET).criterion(hasItem(
            FarinataMod.CHICKPEA_FLOUR), conditionsFromItem(
            FarinataMod.CHICKPEA_FLOUR)).offerTo(exporter)
    }
}

class FarinataBlockLootTableGenerator(out: FabricDataOutput): FabricBlockLootTableProvider(out){
    override fun generate() {
        addDrop(FarinataMod.CHICKPEA_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(FarinataMod.CHICKPEAS))))
    }
}

object FarinataModDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {

        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        pack.addProvider(::FarinataRecipeGenerator)
        pack.addProvider(::FarinataBlockLootTableGenerator)
    }
}

