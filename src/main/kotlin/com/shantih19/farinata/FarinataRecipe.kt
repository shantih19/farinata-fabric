package com.shantih19.farinata

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.*
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.world.World

class FarinataRecipe(val inputA: Ingredient, val inputB: Ingredient, val output: ItemStack, private val id: Identifier): Recipe<CraftingInventory> {

    object Type: RecipeType<FarinataRecipe> {
        @JvmField val ID: String = "farinata_recipe"
    }
    override fun matches(inventory: CraftingInventory?, world: World?): Boolean {
        if (inventory?.size()!! < 2) return false
        return inputA.test(inventory.getStack(0)) and inputB.test(inventory.getStack(1))
    }

    override fun craft(inventory: CraftingInventory?, registryManager: DynamicRegistryManager?): ItemStack {
        return ItemStack.EMPTY
    }

    override fun fits(width: Int, height: Int): Boolean {
        return false
    }

    override fun getOutput(registryManager: DynamicRegistryManager?): ItemStack {
        return output
    }

    override fun getId(): Identifier {
        return id
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return FarinataRecipeSerializer.Serializer
    }

    override fun getType(): RecipeType<*> {
        return Type
    }

}

data class FarinataRecipeJsonFormat(
    var inputA: JsonObject,
    var inputB: JsonObject,
    var outputItem: String,
    var outputAmount: Int
)

open class FarinataRecipeSerializer: RecipeSerializer<FarinataRecipe> {

    object Serializer: FarinataRecipeSerializer()
    object ID: Identifier("farinata:farinata_recipe")

    override fun read(id: Identifier, json: JsonObject): FarinataRecipe {
        var recipeJson: FarinataRecipeJsonFormat = Gson().fromJson(json, FarinataRecipeJsonFormat::class.java)
        if (recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null) {
            throw JsonSyntaxException("A required attribute is missing!")
        }
        var inputA: Ingredient = Ingredient.fromJson(recipeJson.inputA)
        var inputB: Ingredient = Ingredient.fromJson(recipeJson.inputB)
        var outputItem: Item = Registries.ITEM.getOrEmpty(Identifier(recipeJson.outputItem)).orElseThrow()
        var output = ItemStack(outputItem, recipeJson.outputAmount)

        return FarinataRecipe(inputA, inputB, output, id)
    }

    override fun read(id: Identifier, buf: PacketByteBuf): FarinataRecipe {
        var inputA = Ingredient.fromPacket(buf)
        var inputB = Ingredient.fromPacket(buf)
        var output = buf.readItemStack()
        return FarinataRecipe(inputA, inputB, output, id)
    }

    override fun write(buf: PacketByteBuf, recipe: FarinataRecipe) {
        recipe.inputA.write(buf)
        recipe.inputB.write(buf)
        buf.writeItemStack(recipe.output)
    }

}