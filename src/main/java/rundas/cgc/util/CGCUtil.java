package rundas.cgc.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.FluidProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import rundas.cgc.common.data.CGCMaterialFlags;
import rundas.cgc.common.data.CGCPropertyKeys;
import rundas.cgc.common.data.DistillationProperty;

import java.util.List;

public class CGCUtil {
    public static long[] decomposeOrganicMolecule(Material material) {
        List<MaterialStack> components = material.getMaterialComponents();
        long[] composition = new long[3];
        for (MaterialStack component : components) {
            if (component.material() == GTMaterials.Carbon) {
                composition[0] = component.amount();
            }
            if (component.material() == GTMaterials.Hydrogen) {
                composition[1] = component.amount();
            }
            if (component.material() == GTMaterials.Oxygen) {
                composition[2] = component.amount();
            }
        }
        return composition;
    }

    public static void addFluid(String name, String fluidType, Integer temperature, Integer density, Integer luminosity, Integer viscosity, FluidAttribute[] attributes) {
        Material material = GTMaterials.get(name);
        FluidProperty property = new FluidProperty();
        FluidBuilder builder = new FluidBuilder();
        if (temperature != null) {
            builder.temperature(temperature);
        }
        if (density != null) {
            builder.temperature(density);
        }
        if (luminosity != null) {
            builder.luminosity(luminosity);
        }
        if (viscosity != null) {
            builder.viscosity(viscosity);
        }
        if (attributes != null) {
            builder.attributes(attributes);
        }
        switch (fluidType) {
            case "fluid" -> property.getStorage().enqueueRegistration(FluidStorageKeys.LIQUID, builder);
            case "gas" -> property.getStorage().enqueueRegistration(FluidStorageKeys.GAS, builder);
            case "plasma" -> property.getStorage().enqueueRegistration(FluidStorageKeys.PLASMA, builder);
            default -> throw new RuntimeException("Wrong Fluid String given!");
        }
        assert material != null;
        material.setProperty(PropertyKey.FLUID, property);
    }

    public static void addDistillationProperty(String name, int tier) {
        Material material = GTMaterials.get(name);
        assert material != null;
        material.addFlags(CGCMaterialFlags.DECOMPOSITION_BY_DISTILLATION);
        material.setProperty(CGCPropertyKeys.DISTILLATION_PROPERTY_KEY, new DistillationProperty(GTValues.VA[tier], 32L * tier));
    }

    public static void addBlastProperty(String name, int temperature, String gasTier, int voltage, int duration) {
        Material material = GTMaterials.get(name);
        assert material != null;
        material.setProperty(PropertyKey.BLAST, new BlastProperty(temperature, BlastProperty.validateGasTier(gasTier), voltage, duration));
    }
}
