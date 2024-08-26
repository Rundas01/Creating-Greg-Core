package rundas.cgc.registry;


import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import rundas.cgc.CreatingGregCore;

import static rundas.cgc.registry.CGCRegistries.REGISTRATE;

public class CGCCreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> CGC = REGISTRATE.defaultCreativeTab(CreatingGregCore.MOD_ID,
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(CreatingGregCore.MOD_ID, REGISTRATE))
                            .icon(GTItems.LAPOTRON_CRYSTAL::asStack)
                            .title(Component.literal("Creating Greg"))
                            .build())
            .register();

    public static void init() {

    }
}
