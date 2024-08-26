package rundas.cgc.common.data;

import rundas.cgc.registry.CGCCreativeModeTabs;
import com.tterrag.registrate.util.entry.ItemEntry;
import static rundas.cgc.registry.CGCRegistries.REGISTRATE;
import net.minecraft.world.item.Item;


@SuppressWarnings("Convert2MethodRef")
public class CGCItems {
    static {
        REGISTRATE.creativeModeTab(() -> CGCCreativeModeTabs.CGC);
    }

    public static void init() {
    }
}
