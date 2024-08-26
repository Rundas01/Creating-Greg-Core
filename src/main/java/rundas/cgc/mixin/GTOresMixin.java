package rundas.cgc.mixin;

import com.gregtechceu.gtceu.common.data.GTOres;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = GTOres.class, remap = false) // TODO remove once a way to add ore gen to an addon without JSON is made
public class GTOresMixin {

}
