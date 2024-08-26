package rundas.cgc.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class CGCMaterials {

    public static void init() {}

    public static void modifyMaterials() {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(DECOMPOSITION_BY_ELECTROLYZING)){
                material.addFlags(DISABLE_DECOMPOSITION);
            }
            if (!(material.isElement()) && material.hasProperty(PropertyKey.ORE)){
                material.addFlags(NO_SMELTING, NO_ORE_SMELTING);
                OreProperty prop = material.getProperty(PropertyKey.ORE);
                if (prop != null){
                    prop.setDirectSmeltResult(null);
                }
            }
        }
    }
}