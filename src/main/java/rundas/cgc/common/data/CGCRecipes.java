package rundas.cgc.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.BLAST_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DISTILLATION_RECIPES;
import static rundas.cgc.common.data.CGCRecipeTypes.KINETIC_DISTILLERY_RECIPES;

public class CGCRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        changeRecipeMaps();
        registerAutogeneratedDistilleryRecipes(provider);
    }

    private static void changeRecipeMaps() {
        DISTILLATION_RECIPES.setMaxTooltips(4);
        BLAST_RECIPES.setMaxTooltips(5);
    }

    private static void registerAutogeneratedDistilleryRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (!material.hasFlag(CGCMaterialFlags.DECOMPOSITION_BY_DISTILLATION)){
                continue;
            }
            DistillationProperty prop = material.getProperty(CGCPropertyKeys.DISTILLATION_PROPERTY_KEY);
            if (prop == null) {
                continue;
            }
            List<MaterialStack> components = material.getMaterialComponents();
            Tuple<List<Tuple<Material,Long>>, List<Tuple<Material,Long>>> materials = separateComponents(components);
            List<Tuple<Material,Long>> dusts = materials.getA(), fluids = materials.getB();
            if (dusts.size() * fluids.size() <= 32) {
                addDistilleryRecipes(material, prop, dusts, fluids, provider);
            }
            if (dusts.size() == 0 || (dusts.size() == 1 && fluids.size() <= DISTILLATION_RECIPES.getMaxOutputs(FluidRecipeCapability.CAP))) {
                addDistillationTowerRecipe(material, prop, dusts, fluids, provider);
            }
        }
    }

    private static void addDistillationTowerRecipe(Material material, DistillationProperty prop, List<Tuple<Material, Long>> dusts, List<Tuple<Material, Long>> fluids, Consumer<FinishedRecipe> provider) {
        GTRecipeBuilder builder = DISTILLATION_RECIPES.recipeBuilder("distilling_" + material.getName() + "_by_tower")
                .EUt(4 * prop.voltage())
                .rpm(4 * prop.rotation());
        long inputAmount = 0, totalDur = 0;
        for (Tuple<Material, Long> component : fluids) {
            inputAmount += component.getB();
            totalDur += component.getA().getMass() * component.getB();
            builder.outputFluids(component.getA().getFluid(1000 * component.getB()));
        }
        if (dusts.size() > 0) {
            builder.outputItems(ChemicalHelper.get(dust, dusts.get(0).getA(), Math.toIntExact(dusts.get(0).getB())));
        }
        builder.inputFluids(material.getFluid(1000 * inputAmount)).duration((int) totalDur).save(provider);
    }

    private static void addDistilleryRecipes(Material material, DistillationProperty prop, List<Tuple<Material, Long>> dusts, List<Tuple<Material, Long>> fluids, Consumer<FinishedRecipe> provider) {
        int i = 1;
        long inputAmount = 0;
        for (Tuple<Material, Long> component : fluids) {
            inputAmount += component.getB();
        }
        if (dusts.size() > 0){
            for (Tuple<Material, Long> dustStack : dusts) {
                for (Tuple<Material, Long> fluidStack : fluids) {
                    KINETIC_DISTILLERY_RECIPES.recipeBuilder("distilling_" + material.getName() + "_into_" + dustStack.getA().getName() + "_and_" + fluidStack.getA().getName())
                            .circuitMeta(i)
                            .inputFluids(material.getFluid(inputAmount * 1000))
                            .outputItems(ChemicalHelper.get(dust, dustStack.getA(), Math.toIntExact(dustStack.getB())))
                            .outputFluids(fluidStack.getA().getFluid(fluidStack.getB() * 1000))
                            .EUt(prop.voltage())
                            .duration((int) (20 * ((fluidStack.getA().getMass() * fluidStack.getB() + dustStack.getA().getMass()) / (fluidStack.getB() + 1))))
                            .rpm(prop.rotation())
                            .save(provider);
                    i++;
                }
            }
        } else {
            for (Tuple<Material, Long> fluidStack : fluids) {
                KINETIC_DISTILLERY_RECIPES.recipeBuilder("distilling_" + material.getName() + "_into_" + fluidStack.getA().getName())
                        .circuitMeta(i)
                        .inputFluids(material.getFluid(inputAmount * 1000))
                        .outputFluids(fluidStack.getA().getFluid(fluidStack.getB() * 1000))
                        .EUt(prop.voltage())
                        .duration((int) (20 * fluidStack.getA().getMass() * fluidStack.getB() / fluidStack.getB()))
                        .rpm(prop.rotation())
                        .save(provider);
                i++;
            }
        }
    }

    private static Tuple<List<Tuple<Material, Long>>, List<Tuple<Material,Long>>> separateComponents(List<MaterialStack> components) {
        List<Tuple<Material,Long>> dusts = new ArrayList<>(), fluids = new ArrayList<>();
        for (MaterialStack stack : components) {
            int atomCount = 0;
            for (MaterialStack materialStack : stack.material().getMaterialComponents()) {
                atomCount += materialStack.amount();
            }
            if (stack.material().hasProperty(PropertyKey.FLUID) && !stack.material().hasProperty(PropertyKey.DUST)){
                fluids.add(new Tuple<>(stack.material(), stack.amount()));
            } else if (!stack.material().hasProperty(PropertyKey.FLUID) && stack.material().hasProperty(PropertyKey.DUST)) {
                dusts.add(new Tuple<>(stack.material(), stack.amount() * atomCount));
            } else if (stack.material().hasProperty(PropertyKey.FLUID) && stack.material().hasProperty(PropertyKey.DUST)) {
                dusts.add(new Tuple<>(stack.material(), stack.amount() * atomCount));
            }
        }
        return new Tuple<>(dusts, fluids);
    }
}
