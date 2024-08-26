package rundas.cgc.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class LangHandler extends com.gregtechceu.gtceu.data.lang.LangHandler {
    public static void init(RegistrateLangProvider provider) {
        initMachineLang(provider);
        initRecipeTypeLang(provider);
    }
    private static void initRecipeTypeLang(RegistrateLangProvider provider) {
        provider.add("gtceu.create_mixer", "Kinetic Mixing");
        provider.add("cgc.ore_cracking", "Kinetic Ore Cracking");
        provider.add("cgc.gas_bubble_reacting", "Kinetic Gas Bubble Reacting");
        provider.add("cgc.batch_reacting", "Kinetic Batch Reacting");
        provider.add("cgc.mixing_reacting", "Kinetic Mixing Reacting");
        provider.add("cgc.catalytic_reacting", "Kinetic Catalytic Reacting");
        provider.add("cgc.dissolution_reacting", "Kinetic Dissolution Reacting");
        provider.add("cgc.polymerizing", "Kinetic Polymerizing");
        provider.add("cgc.reaction_furnace", "Reaction Furnace");
        provider.add("cgc.advanced_combustion_turbine", "Advanced Combustion Turbine");
        provider.add("cgc.kinetic_smelting", "Kinetic Smelting");
        provider.add("cgc.kinetic_alloying", "Kinetic Alloy Smelting");
        provider.add("cgc.kinetic_hammering", "Kinetic Hammering");
        provider.add("cgc.kinetic_extracting", "Kinetic Extracting");
        provider.add("cgc.kinetic_compressing", "Kinetic Compressing");
        provider.add("cgc.kinetic_macerating", "Kinetic Macerating");
        provider.add("cgc.kinetic_lathing", "Kinetic Lathing");
        provider.add("cgc.kinetic_engraving", "Kinetic Engraving");
        provider.add("cgc.kinetic_washing", "Kinetic Washing");
        provider.add("cgc.kinetic_bathing", "Kinetic Bathing");
        provider.add("cgc.kinetic_wiremilling", "Kinetic Wiremilling");
        provider.add("cgc.kinetic_separating", "Kinetic Separating");
        provider.add("cgc.kinetic_electrolyzing", "Kinetic Electrolyzing");
        provider.add("cgc.kinetic_thermal_centrifuging", "Kinetic Thermal Centrifuging");
        provider.add("cgc.kinetic_centrifuging", "Kinetic Centrifuging");
        provider.add("cgc.kinetic_heating", "Kinetic Heating");
        provider.add("cgc.kinetic_fermenting", "Kinetic Fermenting");
        provider.add("cgc.kinetic_distilling", "Kinetic Distilling");
        provider.add("cgc.kinetic_assembling", "Kinetic Assembling");
        provider.add("cgc.kinetic_circuit_assembling", "Kinetic Circuit Assembling");
        provider.add("cgc.kinetic_bending", "Kinetic Bending");
        provider.add("cgc.kinetic_solidifying", "Kinetic Solidifying");
        provider.add("cgc.kinetic_pressing", "Kinetic Pressing");
        provider.add("cgc.kinetic_extruding", "Kinetic Extruding");
        provider.add("cgc.kinetic_packing", "Kinetic Packing");
        provider.add("cgc.kinetic_polarizing", "Kinetic Polarizing");
        provider.add("cgc.kinetic_sifting", "Kinetic Sifting");
        provider.add("cgc.kinetic_gas_collecting", "Kinetic Collecting");
        provider.add("cgc.kinetic_breaking", "Kinetic Breaking");
        provider.add("cgc.kinetic_brewing", "Kinetic Brewing");
        provider.add("cgc.kinetic_mass_fabricating", "Kinetic Fabricating");
        provider.add("cgc.kinetic_replicating", "Kinetic Replicating");
        provider.add("cgc.kinetic_arc_smelting", "Kinetic Arc Smelting");
        provider.add("cgc.kinetic_canning", "Kinetic Canning");
        provider.add("cgc.kinetic_cutting", "Kinetic Cutting");
        provider.add("cgc.kinetic_autoclaving", "Kinetic Crystallization");
        provider.add("cgc.flotating", "Flotation");
        provider.add("cgc.clarifying", "Clarifying");
        provider.add("cgc.heat_exchanging", "Heat Exchanging");
        provider.add("cgc.chemical_plant", "Advanced Large Chemical Reactor");
        provider.add("cgc.advanced_electrolysis", "Advanced Electrolysis");
    }
    private static void initMachineLang(RegistrateLangProvider provider) {
        provider.add("block.gtceu.flotation_cell", "Flotation Cell");
        provider.add("block.gtceu.clarification_plant", "Clarification Plant");
        provider.add("block.gtceu.heat_exchanger", "Heat Exchanger");
        provider.add("block.gtceu.crucible", "Crucible");
        provider.add("block.gtceu.advanced_electrolyzer", "Advanced Large Electrolytic Cell");
        addMachineLang("kinetic_input_box", "Kinetic Input Box", provider);
        addMachineLang("kinetic_mixer", "Kinetic Mixer", provider);
        addMachineLang("ore_cracker", "Kinetic Ore Cracker", provider);
        addMachineLang("gas_bubble_reactor", "Kinetic Gas Bubble Reactor", provider);
        addMachineLang("batch_reactor", "Kinetic Batch Reactor", provider);
        addMachineLang("catalytic_reactor", "Kinetic Catalytic Reactor", provider);
        addMachineLang("dissolution_reactor", "Kinetic Dissolution Reactor", provider);
        addMachineLang("mixing_reactor", "Kinetic Mixing Reactor", provider);
        addMachineLang("polymerizer", "Kinetic Polymerizer", provider);
        addMachineLang("kinetic_furnace", "Kinetic Furnace", provider);
        addMachineLang("kinetic_alloy_smelter", "Kinetic Alloy Smelter", provider);
        addMachineLang("kinetic_forge_hammer", "Kinetic Hammer", provider);
        addMachineLang("kinetic_compressor", "Kinetic Compressor", provider);
        addMachineLang("kinetic_extractor", "Kinetic Extractor", provider);
        addMachineLang("kinetic_macerator", "Kinetic Macerator", provider);
        addMachineLang("kinetic_ore_washer", "Kinetic Washer", provider);
        addMachineLang("kinetic_chemical_bath", "Kinetic Bath", provider);
        addMachineLang("kinetic_thermal_centrifuge", "Kinetic Thermal Centrifuge", provider);
        addMachineLang("kinetic_centrifuge", "Kinetic Centrifuge", provider);
        addMachineLang("kinetic_bender", "Kinetic Bender", provider);
        addMachineLang("kinetic_extruder", "Kinetic Extruder", provider);
        addMachineLang("kinetic_press", "Kinetic Press", provider);
        addMachineLang("kinetic_lathe", "Kinetic Lathe", provider);
        addMachineLang("kinetic_wiremill", "Kinetic Wiremill", provider);
        addMachineLang("kinetic_engraver", "Kinetic Engraver", provider);
        addMachineLang("kinetic_packer", "Kinetic Packer", provider);
        addMachineLang("kinetic_sifter", "Kinetic Sifter", provider);
        addMachineLang("kinetic_fluid_solidifier", "Kinetic Solidifier", provider);
        addMachineLang("kinetic_electrolyzer", "Kinetic Electrolyzer", provider);
        addMachineLang("kinetic_separator", "Kinetic Separator", provider);
        addMachineLang("kinetic_polarizer", "Kinetic Polarizer", provider);
        addMachineLang("kinetic_assembler", "Kinetic Assembler", provider);
        addMachineLang("kinetic_circuit_assembler", "Kinetic Circuit Assembler", provider);
        addMachineLang("kinetic_fluid_heater", "Kinetic Heater", provider);
        addMachineLang("kinetic_fermenter", "Kinetic Fermenter", provider);
        addMachineLang("kinetic_brewery", "Kinetic Brewer", provider);
        addMachineLang("kinetic_distillery", "Kinetic Distillery", provider);
        addMachineLang("kinetic_gas_collector", "Kinetic Gas Collector", provider);
        addMachineLang("kinetic_rock_crusher", "Kinetic Rock Crusher", provider);
        addMachineLang("kinetic_mass_fabricator", "Kinetic Mass Fabricator", provider);
        addMachineLang("kinetic_replicator", "Kinetic Replicator", provider);
        addMachineLang("kinetic_arc_furnace", "Kinetic Arc Furnace", provider);
        addMachineLang("kinetic_canner", "Kinetic Canner", provider);
        addMachineLang("kinetic_cutter", "Kinetic Cutter", provider);
        addMachineLang("kinetic_autoclave", "Kinetic Autoclave", provider);
        addMachineLang("kinetic_generator", "Kinetic Generator", provider);
    }
    private static void addMachineLang(String unlocalMachineName, String localMachineName, RegistrateLangProvider provider) {
        for (int i = 1; i < 14; i++) {
            provider.add("block.gtceu.%s_%s".formatted(VN[i].toLowerCase(),unlocalMachineName), "%s %s %s".formatted(VLVH[i], localMachineName, VLVT[i]));
        }
    }
}
