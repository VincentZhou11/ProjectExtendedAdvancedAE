package com.zhouvincent11.projectextendedadvancedae.datagen;

import appeng.core.definitions.AEItems;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.zhouvincent11.projectextendedadvancedae.Projectextendedadvancedae;
import moze_intel.projecte.api.data.CustomConversionProvider;
import moze_intel.projecte.api.nss.NSSItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ConversionProvider extends CustomConversionProvider {

    public ConversionProvider(@NotNull PackOutput output, @NotNull CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Projectextendedadvancedae.MODID);
    }

    @Override
    protected void addCustomConversions(@NotNull HolderLookup.Provider lookupProvider) {
        createConversionBuilder(Projectextendedadvancedae.RL("materials"))
                .before(NSSItem.createItem(AEItems.SINGULARITY), 256000)
                .before(NSSItem.createItem(AEItems.MATTER_BALL), 256)
                .before(NSSItem.createItem(EAESingletons.ENTRO_CRYSTAL), 1024)
        ;
    }
}
