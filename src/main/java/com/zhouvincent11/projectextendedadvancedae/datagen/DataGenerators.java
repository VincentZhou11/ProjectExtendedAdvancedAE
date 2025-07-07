package com.zhouvincent11.projectextendedadvancedae.datagen;

import com.zhouvincent11.projectextendedadvancedae.Projectextendedadvancedae;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

import static com.zhouvincent11.projectextendedadvancedae.Projectextendedadvancedae.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private static final Logger LOGGER = Projectextendedadvancedae.LOGGER;


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final DataGenerator gen = event.getGenerator();
        final PackOutput packOutput = gen.getPackOutput();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        LOGGER.info("Initializing Conversion Provider");

        gen.addProvider(event.includeServer(), new ConversionProvider(packOutput, lookupProvider));
    }
}