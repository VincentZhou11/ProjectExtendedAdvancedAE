package com.zhouvincent11.projectextendedadvancedae.mapper;


import com.zhouvincent11.projectextendedadvancedae.Projectextendedadvancedae;
import moze_intel.projecte.api.mapper.EMCMapper;
import moze_intel.projecte.api.mapper.IEMCMapper;
import moze_intel.projecte.api.mapper.collector.IMappingCollector;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


@EMCMapper
public class RecipeMapper implements IEMCMapper<NormalizedSimpleStack, Long> {


    private static final String ADVANCEDAE_MODID = "advanced_ae";
    private static final String EXTENDEDAE_MODID = "extendedae";

    private static final Logger LOGGER = Projectextendedadvancedae.LOGGER;

    @Override
    public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> iMappingCollector, ReloadableServerResources reloadableServerResources, RegistryAccess registryAccess, ResourceManager resourceManager) {

        RecipeManager mgr = ServerLifecycleHooks.getCurrentServer().getRecipeManager();

        if (ModList.get().isLoaded(ADVANCEDAE_MODID)) {
            LOGGER.info("Advanced AE loaded, attempting to map recipes");
            try {
                AdvancedAERecipeMapper getter = AdvancedAERecipeMapper.class.newInstance();
                getter.mapRecipes(mgr, iMappingCollector);
                LOGGER.info("Advanced AE recipes succesfully mapped");
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("Unable to instantiate AdvancedAERecipeMapper", e);
            }
        }

        if (ModList.get().isLoaded(EXTENDEDAE_MODID)) {
            LOGGER.info("Extended AE loaded, attempting to map recipes");
            try {
                ExtendedAERecipeMapper getter = ExtendedAERecipeMapper.class.newInstance();
                getter.mapRecipes(mgr, iMappingCollector);
                LOGGER.info("Extended AE recipes succesfully mapped");
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("Unable to instantiate ExtendedAERecipeMapper", e);
            }
        }

    }

    @Override
    public String getName() {
        return "ExtendedAdvancedAEMapper";
    }

    @Override
    public String getTranslationKey() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Maps EMC values for ExtendedAE and AdvancedAE";
    }
}
