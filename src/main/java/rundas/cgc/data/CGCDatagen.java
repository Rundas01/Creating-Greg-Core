package rundas.cgc.data;

import rundas.cgc.registry.CGCRegistries;
import rundas.cgc.data.lang.LangHandler;
import com.tterrag.registrate.providers.ProviderType;

public class CGCDatagen {
    public static void init() {
        CGCRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}