package rundas.cgc.client.renderer;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.client.renderer.machine.KineticWorkableTieredHullMachineRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.TransformerRenderer;
import com.lowdragmc.lowdraglib.client.bakedpipeline.FaceQuad;
import com.lowdragmc.lowdraglib.client.model.ModelFactory;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

import javax.annotation.Nullable;
import java.util.List;

public class KineticGeneratorRenderer extends KineticWorkableTieredHullMachineRenderer {

    private final ResourceLocation energyOut;

    public KineticGeneratorRenderer(int tier, ResourceLocation modelLocation, ResourceLocation overlayModel) {
        super(tier, modelLocation, overlayModel);
        if (tier > 4){
            this.energyOut = TransformerRenderer.ENERGY_OUT_MULTI;
        }
        else {
            this.energyOut = TransformerRenderer.ENERGY_OUT;
        }
    }

    @Override
    public void renderMachine(List<BakedQuad> quads, MachineDefinition definition, @Nullable MetaMachine machine,
                              Direction frontFacing, @Nullable Direction side, RandomSource rand, Direction modelFacing,
                              ModelState modelState) {
        super.renderMachine(quads, definition, machine, frontFacing, side, rand, modelFacing, modelState);
        if (side == frontFacing && modelFacing != null) {
            quads.add(FaceQuad.bakeFace(
                    modelFacing, ModelFactory.getBlockSprite(energyOut),
                    modelState, 2));
        }
    }
}
