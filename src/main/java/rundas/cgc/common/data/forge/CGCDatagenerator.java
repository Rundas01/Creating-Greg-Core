package rundas.cgc.common.data.forge;

import rundas.cgc.CreatingGregCore;
import com.gregtechceu.gtceu.api.registry.registrate.SoundEntryBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CGCDatagenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        var registryAccess = event.getLookupProvider();
        if (event.includeServer()) {
            var set = Set.of(CreatingGregCore.MOD_ID);
            generator.addProvider(true, new SoundEntryBuilder.SoundEntryProvider(generator.getPackOutput(), CreatingGregCore.MOD_ID));
        }
    }
}