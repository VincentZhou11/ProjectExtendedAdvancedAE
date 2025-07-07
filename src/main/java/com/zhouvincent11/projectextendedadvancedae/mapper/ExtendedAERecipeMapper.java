package com.zhouvincent11.projectextendedadvancedae.mapper;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import moze_intel.projecte.api.mapper.collector.IMappingCollector;
import moze_intel.projecte.api.nss.NSSFluid;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.world.item.crafting.Recipe;
import com.glodblock.github.extendedae.recipe.CrystalAssemblerRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pedroksl.advanced_ae.recipes.ReactionChamberRecipe;

import java.util.List;
import java.util.stream.Collectors;

public class ExtendedAERecipeMapper {
    private static final String EXTENDEDAE_MODID = "extendedae";

    public void mapRecipes(RecipeManager mgr, IMappingCollector<NormalizedSimpleStack, Long> iMappingCollector) {
        List<CrystalAssemblerRecipe> crystalAssemblerRecipes = mgr.getAllRecipesFor(CrystalAssemblerRecipe.TYPE).stream().map(RecipeHolder::value).collect(Collectors.toList());

        crystalAssemblerRecipes.forEach(recipe -> {
            Object2IntMap<NormalizedSimpleStack> inputs = new Object2IntOpenHashMap<>();


            recipe.getInputs().forEach(input -> {
                inputs.put(NSSItem.createItem(input.getIngredient().getItems()[0]), input.getAmount());
            });


            if (recipe.getFluid() != null) {
                inputs.put(NSSFluid.createFluid(recipe.getFluid().getIngredient().getStacks()[0]), recipe.getFluid().getAmount());
            }


            iMappingCollector.addConversion(recipe.output.getCount(), NSSItem.createItem(recipe.output), inputs);

        });
    }
}
