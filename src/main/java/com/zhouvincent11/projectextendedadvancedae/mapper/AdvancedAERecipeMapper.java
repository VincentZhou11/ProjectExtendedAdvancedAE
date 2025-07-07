package com.zhouvincent11.projectextendedadvancedae.mapper;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import moze_intel.projecte.api.mapper.collector.IMappingCollector;
import moze_intel.projecte.api.nss.NSSFluid;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pedroksl.advanced_ae.recipes.ReactionChamberRecipe;

import java.util.List;
import java.util.stream.Collectors;

public class AdvancedAERecipeMapper {

    private static final String ADVANCEDAE_MODID = "advanced_ae";

    public void mapRecipes(RecipeManager mgr, IMappingCollector<NormalizedSimpleStack, Long> iMappingCollector) {
        List<ReactionChamberRecipe> reactionChamberRecipes = mgr.getAllRecipesFor(ReactionChamberRecipe.TYPE).stream().map(RecipeHolder::value).collect(Collectors.toList());

        reactionChamberRecipes.forEach(recipe -> {
            Object2IntMap<NormalizedSimpleStack> inputs = new Object2IntOpenHashMap<>();


            recipe.getInputs().forEach(input -> {
                inputs.put(NSSItem.createItem(input.getIngredient().getItems()[0]), input.getAmount());
            });


            if (recipe.getFluid().getAmount() > 0) {
                inputs.put(NSSFluid.createFluid(recipe.getFluid().getIngredient().getStacks()[0]), recipe.getFluid().getAmount());
            }

            if (recipe.getResultItem().getCount() > 0) {
                iMappingCollector.addConversion(recipe.getResultItem().getCount(), NSSItem.createItem(recipe.getResultItem().getItem()), inputs);

            }
            else if (recipe.getResultFluid().getAmount() > 0) {
                iMappingCollector.addConversion(recipe.getResultFluid().getAmount(), NSSFluid.createFluid(recipe.getResultFluid().getFluid()), inputs);
            }

        });
    }
}
