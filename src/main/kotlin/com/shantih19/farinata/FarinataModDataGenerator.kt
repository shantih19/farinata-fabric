package com.shantih19.farinata

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Items
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

object FarinataModDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {

        var pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        pack.addProvider(::FarinataRecipeGenerator)
    }
}

