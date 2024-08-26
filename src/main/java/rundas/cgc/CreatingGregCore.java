package rundas.cgc;

import rundas.cgc.block.CGCBlocks;
import rundas.cgc.common.data.*;
import rundas.cgc.registry.CGCCreativeModeTabs;
import rundas.cgc.registry.CGCRegistries;
import rundas.cgc.data.CGCDatagen;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.config.ConfigHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreatingGregCore.MOD_ID)
public class CreatingGregCore {
    public static final String MOD_ID = "cgc";
    public static MaterialRegistry MATERIAL_REGISTRY;

    public CreatingGregCore() {
        CreatingGregCore.init();
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        bus.addGenericListener(MachineDefinition.class, this::registerMachines);
    }

    public static void init() {
        ConfigHolder.init();
        CGCBlocks.init();
        CGCItems.init();
        CGCCreativeModeTabs.init();
        CGCDatagen.init();
        CGCRegistries.REGISTRATE.registerRegistrate();
    }


    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    @SubscribeEvent
    public void registerMaterialRegistry(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(CreatingGregCore.MOD_ID);
    }

    @SubscribeEvent
    public void registerMaterials(MaterialEvent event) {
        CGCMaterials.init();
    }

    @SubscribeEvent
    public void modifyMaterials(PostMaterialEvent event) {
        CGCMaterials.modifyMaterials();
    }

    @SubscribeEvent
    public void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CGCRecipeTypes.init();
    }

    @SubscribeEvent
    public void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CGCMachines.init();
    }
}

