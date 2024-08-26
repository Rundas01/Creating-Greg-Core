package rundas.cgc.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.VeinedVeinGenerator;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTOres.create;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import static rundas.cgc.CreatingGregCore.id;

public class CGCVeinGenerators {
    public static void registerVeins(){
        final GTOreDefinition CU_SN_FE_VEIN = create(id("copper_tin_vein"), vein -> vein
                .clusterSize(UniformInt.of(40, 52)).density(1.0f).weight(80)
                .layer(WorldGenLayers.STONE)
                .heightRangeUniform(10, 80)
                .biomes(BiomeTags.IS_OVERWORLD)
                .veinedVeinGenerator(generator -> generator
                        .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Copper, 4))
                        .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Tin, 4))
                        .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(Iron, 4))
                        .veininessThreshold(0.01f)
                        .maxRichnessThreshold(0.175f)
                        .minRichness(0.7f)
                        .maxRichness(1.0f)
                        .edgeRoundoffBegin(3)
                        .maxEdgeRoundoff(0.1f))
                .surfaceIndicatorGenerator(indicator -> indicator
                        .surfaceRock(Iron)));
    }
}
