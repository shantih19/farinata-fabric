package com.shantih19.farinata

import com.shantih19.farinata.block.ChickpeaCropBlock
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.impl.datagen.FabricTagBuilder
import net.minecraft.block.Block
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class FarinataRecipeGenerator(out: FabricDataOutput) : FabricRecipeProvider(out) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>?) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, FarinataMod.CHICKPEA_FLOUR).input(
            FarinataMod.CHICKPEAS
        ).criterion(
            hasItem(FarinataMod.CHICKPEAS), conditionsFromItem(FarinataMod.CHICKPEAS)
        ).offerTo(exporter)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, FarinataMod.CHICKPEA_SLURRY).input(
            FarinataMod.CHICKPEA_FLOUR
        ).input(Items.WATER_BUCKET).criterion(
            hasItem(
                FarinataMod.CHICKPEA_FLOUR
            ), conditionsFromItem(
                FarinataMod.CHICKPEA_FLOUR
            )
        ).offerTo(exporter)
        RecipeProvider.offerSmelting(
            exporter,
            listOf(FarinataMod.CHICKPEAS),
            RecipeCategory.FOOD,
            FarinataMod.ROASTED_CHICKPEAS,
            0.25f,
            90,
            "farinata"
        )
        RecipeProvider.offerSmelting(
            exporter,
            listOf(FarinataMod.CHICKPEA_SLURRY),
            RecipeCategory.FOOD,
            FarinataMod.FARINATA_ITEM,
            0.6f,
            300,
            "farinata"
        )
    }
}

class FarinataBlockLootTableGenerator(out: FabricDataOutput) : FabricBlockLootTableProvider(out) {

    override fun generate() {
        addDrop(
            FarinataMod.CHICKPEA_CROP, LootTable.builder().pool(
                LootPool.builder().with(
                    ItemEntry.builder(FarinataMod.CHICKPEAS).apply(
                        SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(2f, 5f)
                        )
                    ).build()
                ).conditionally(
                    BlockStatePropertyLootCondition.builder(FarinataMod.CHICKPEA_CROP).properties(
                        StatePredicate.Builder.create().exactMatch(ChickpeaCropBlock.ageProperty, 7)
                    )
                )
            )
        )
    }
}


@JvmField
val FARINATA_CROP_TAG: TagKey<Block?>? = TagKey.of<Block>(RegistryKeys.BLOCK, Identifier("minecraft", "crops"))

@JvmField val FARINATA_BEE_TAG: TagKey<Block?>? = TagKey.of(RegistryKeys.BLOCK, Identifier("minecraft", "bee_growables"))

class FarinataBlockTagGenerator(out: FabricDataOutput, completableFuture: CompletableFuture<WrapperLookup>): FabricTagProvider.BlockTagProvider(out, completableFuture) {
    override fun configure(arg: WrapperLookup?) {
        getOrCreateTagBuilder(FARINATA_CROP_TAG).add(FarinataMod.CHICKPEA_CROP)
        getOrCreateTagBuilder(FARINATA_BEE_TAG).add(FarinataMod.CHICKPEA_CROP)
    }

}

object FarinataModDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {

        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        pack.addProvider(::FarinataRecipeGenerator)
        pack.addProvider(::FarinataBlockLootTableGenerator)
        pack.addProvider(::FarinataBlockTagGenerator)
    }
}

