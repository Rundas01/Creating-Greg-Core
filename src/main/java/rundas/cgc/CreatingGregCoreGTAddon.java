package rundas.cgc;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;
import rundas.cgc.common.data.CGCRecipes;
import rundas.cgc.common.data.CGCVeinGenerators;
import rundas.cgc.registry.CGCRegistries;

import java.util.function.Consumer;

@GTAddon
public class CreatingGregCoreGTAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return CGCRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
    }

    @Override
    public String addonModId() {
        return CreatingGregCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
    }

    @Override
    public void registerCovers() {
        IGTAddon.super.registerCovers();
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }

    @Override
    public void registerVeinGenerators() {
        //CGCVeinGenerators.registerVeins();
    }

    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event) {IGTAddon.super.collectMaterialCasings(event);}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CGCRecipes.init(provider);
    }
}