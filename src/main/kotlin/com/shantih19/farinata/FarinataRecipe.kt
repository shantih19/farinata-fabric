package com.shantih19.farinata

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.*
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList
import net.minecraft.world.World

class FarinataRecipe(
    val inputA: Item,
    val inputB: Item,
    val output: ItemStack,
    private val id: Identifier
) : Recipe<CraftingInventory> {


    override fun matches(inventory: CraftingInventory, world: World): Boolean {
        val found: MutableList<Item> = mutableListOf()
        if (!inventory.containsAny { it == ItemStack(inputA) || it == ItemStack(inputB) }) return false
        for (i in 0 until inventory.size()) {
            if (inventory.getStack(i).isEmpty) continue
            if (!inventory.getStack(i).isOf(inputA) && !inventory.getStack(i).isOf(inputB)) continue
            found.add(inventory.getStack(i).item)
        }
        return found.size == 2 && found.containsAll(listOf(inputA, inputB))
    }

    override fun craft(inventory: CraftingInventory?, registryManager: DynamicRegistryManager?): ItemStack {
        return getOutput(registryManager).copy()
    }

    override fun fits(width: Int, height: Int): Boolean {
        return true
    }

    override fun getOutput(registryManager: DynamicRegistryManager?): ItemStack {
        return output
    }

    override fun getId(): Identifier {
        return id
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return FarinataRecipeSerializer
    }

    override fun getType(): RecipeType<FarinataRecipe> {
        return FarinataRecipeType
    }

    override fun getRemainder(inventory: CraftingInventory?): DefaultedList<ItemStack> {
        return DefaultedList.copyOf(ItemStack.EMPTY)
    }

}

data class FarinataRecipeJsonFormat(
    var inputA: String,
    var inputB: String,
    var outputItem: String,
    var outputAmount: Int
)

object FarinataRecipeSerializer : RecipeSerializer<FarinataRecipe> {


    override fun read(id: Identifier, json: JsonObject): FarinataRecipe {
        val recipeJson: FarinataRecipeJsonFormat = Gson().fromJson(json, FarinataRecipeJsonFormat::class.java)
        val inputA: Item = Registries.ITEM.getOrEmpty(Identifier(recipeJson.inputA)).orElseThrow()
        val inputB: Item = Registries.ITEM.getOrEmpty(Identifier(recipeJson.inputB)).orElseThrow()
        val outputItem: Item = Registries.ITEM.getOrEmpty(Identifier(recipeJson.outputItem)).orElseThrow()
        val output = ItemStack(outputItem, recipeJson.outputAmount)

        return FarinataRecipe(inputA, inputB, output, id)
    }

    override fun read(id: Identifier, buf: PacketByteBuf): FarinataRecipe {
        val inputA = buf.readItemStack().item
        val inputB = buf.readItemStack().item
        val output = buf.readItemStack()
        return FarinataRecipe(inputA, inputB, output, id)
    }

    override fun write(buf: PacketByteBuf, recipe: FarinataRecipe) {
        buf.writeItemStack(ItemStack(recipe.inputA))
        buf.writeItemStack(ItemStack(recipe.inputB))
        buf.writeItemStack(recipe.output)
    }

}

object FarinataRecipeType : RecipeType<FarinataRecipe>
