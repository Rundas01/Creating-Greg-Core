package rundas.cgc.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.client.instance.SplitShaftInstance;
import com.gregtechceu.gtceu.client.renderer.machine.KineticWorkableTieredHullMachineRenderer;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.KineticMachineDefinition;
import com.gregtechceu.gtceu.common.machine.kinetic.SimpleKineticElectricWorkableMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import rundas.cgc.client.renderer.KineticGeneratorRenderer;
import rundas.cgc.common.data.machine.CrucibleMachine;
import rundas.cgc.common.data.machine.KineticGeneratorMachine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GCyMBlocks.ELECTROLYTIC_CELL;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Steel;
import static com.gregtechceu.gtceu.common.data.machines.GTCreateMachines.KINETIC_INPUT_BOX;
import static com.gregtechceu.gtceu.common.data.machines.GTCreateMachines.registerTieredMachines;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static rundas.cgc.block.CGCBlocks.CASING_FLOTATION;
import static rundas.cgc.common.data.CGCRecipeTypes.ADVANCED_COMBUSTION_RECIPES;
import static rundas.cgc.registry.CGCRegistries.REGISTRATE;

public class CGCMachines {
    public static final MachineDefinition[] ORE_CRACKER = registerSimpleKineticElectricMachine("ore_cracker", CGCRecipeTypes.ORE_CRACKER_RECIPES, largeTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] BATCH_REACTOR = registerSimpleKineticElectricMachine("batch_reactor", CGCRecipeTypes.BATCH_REACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] GAS_BUBBLE_REACTOR = registerSimpleKineticElectricMachine("gas_bubble_reactor", CGCRecipeTypes.GAS_BUBBLE_REACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] MIXING_REACTOR = registerSimpleKineticElectricMachine("mixing_reactor", CGCRecipeTypes.MIXING_REACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] CATALYTIC_REACTOR = registerSimpleKineticElectricMachine("catalytic_reactor", CGCRecipeTypes.CATALYTIC_REACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] DISSOLUTION_REACTOR = registerSimpleKineticElectricMachine("dissolution_reactor", CGCRecipeTypes.DISSOLUTION_REACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] POLYMERIZER = registerSimpleKineticElectricMachine("polymerizer", CGCRecipeTypes.POLYMERIZER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_FURNACE = registerSimpleKineticElectricMachine("kinetic_furnace", CGCRecipeTypes.KINETIC_FURNACE_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_ALLOY_SMELTER = registerSimpleKineticElectricMachine("kinetic_alloy_smelter", CGCRecipeTypes.KINETIC_ALLOY_SMELTER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_HAMMER = registerSimpleKineticElectricMachine("kinetic_forge_hammer", CGCRecipeTypes.KINETIC_HAMMER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_COMPRESSOR = registerSimpleKineticElectricMachine("kinetic_compressor", CGCRecipeTypes.KINETIC_COMPRESSOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_EXTRACTOR = registerSimpleKineticElectricMachine("kinetic_extractor", CGCRecipeTypes.KINETIC_EXTRACTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_MACERATOR = registerSimpleKineticElectricMachine("kinetic_macerator", CGCRecipeTypes.KINETIC_MACERATOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_WASHER = registerSimpleKineticElectricMachine("kinetic_ore_washer", CGCRecipeTypes.KINETIC_WASHER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_BATH = registerSimpleKineticElectricMachine("kinetic_chemical_bath", CGCRecipeTypes.KINETIC_BATH_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_ARC_FURNACE = registerSimpleKineticElectricMachine("kinetic_arc_furnace", CGCRecipeTypes.KINETIC_ARC_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_AUTOCLAVE = registerSimpleKineticElectricMachine("kinetic_autoclave", CGCRecipeTypes.KINETIC_AUTOCLAVE_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_CANNER = registerSimpleKineticElectricMachine("kinetic_canner", CGCRecipeTypes.KINETIC_CANNER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_CUTTER = registerSimpleKineticElectricMachine("kinetic_cutter", CGCRecipeTypes.KINETIC_CUTTER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_THERMAL_CENTRIFUGE = registerSimpleKineticElectricMachine("kinetic_thermal_centrifuge", CGCRecipeTypes.KINETIC_THERMAL_CENTRIFUGE_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_CENTRIFUGE = registerSimpleKineticElectricMachine("kinetic_centrifuge", CGCRecipeTypes.KINETIC_CENTRIFUGE_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_BENDER = registerSimpleKineticElectricMachine("kinetic_bender", CGCRecipeTypes.KINETIC_BENDER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_EXTRUDER = registerSimpleKineticElectricMachine("kinetic_extruder", CGCRecipeTypes.KINETIC_EXTRUDER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_PRESS = registerSimpleKineticElectricMachine("kinetic_press", CGCRecipeTypes.KINETIC_PRESS_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_LATHE = registerSimpleKineticElectricMachine("kinetic_lathe", CGCRecipeTypes.KINETIC_LATHE_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_WIREMILL = registerSimpleKineticElectricMachine("kinetic_wiremill", CGCRecipeTypes.KINETIC_WIREMILL_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_ENGRAVER = registerSimpleKineticElectricMachine("kinetic_engraver", CGCRecipeTypes.KINETIC_ENGRAVER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_PACKER = registerSimpleKineticElectricMachine("kinetic_packer", CGCRecipeTypes.KINETIC_PACKER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_SIFTER = registerSimpleKineticElectricMachine("kinetic_sifter", CGCRecipeTypes.KINETIC_SIFTER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_SOLIDIFIER = registerSimpleKineticElectricMachine("kinetic_fluid_solidifier", CGCRecipeTypes.KINETIC_SOLIDIFIER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_ELECTROLYZER = registerSimpleKineticElectricMachine("kinetic_electrolyzer", CGCRecipeTypes.KINETIC_ELECTROLYZER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_SEPARATOR = registerSimpleKineticElectricMachine("kinetic_separator", CGCRecipeTypes.KINETIC_SEPARATOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_POLARIZER = registerSimpleKineticElectricMachine("kinetic_polarizer", CGCRecipeTypes.KINETIC_POLARIZER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_ASSEMBLER = registerSimpleKineticElectricMachine("kinetic_assembler", CGCRecipeTypes.KINETIC_ASSEMBLER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_CIRCUIT_ASSEMBLER = registerSimpleKineticElectricMachine("kinetic_circuit_assembler", CGCRecipeTypes.KINETIC_CIRCUIT_ASSEMBLER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_HEATER = registerSimpleKineticElectricMachine("kinetic_fluid_heater", CGCRecipeTypes.KINETIC_HEATER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_FERMENTER = registerSimpleKineticElectricMachine("kinetic_fermenter", CGCRecipeTypes.KINETIC_FERMENTER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_BREWER = registerSimpleKineticElectricMachine("kinetic_brewery", CGCRecipeTypes.KINETIC_BREWER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_DISTILLERY = registerSimpleKineticElectricMachine("kinetic_distillery", CGCRecipeTypes.KINETIC_DISTILLERY_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_COLLECTOR = registerSimpleKineticElectricMachine("kinetic_gas_collector", CGCRecipeTypes.KINETIC_COLLECTOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_CRUSHER = registerSimpleKineticElectricMachine("kinetic_rock_crusher", CGCRecipeTypes.KINETIC_BREAKER_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_FABRICATOR = registerSimpleKineticElectricMachine("kinetic_mass_fabricator", CGCRecipeTypes.KINETIC_MASS_FABRICATOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_REPLICATOR = registerSimpleKineticElectricMachine("kinetic_replicator", CGCRecipeTypes.KINETIC_REPLICATOR_RECIPES, defaultTankSizeFunction, ELECTRIC_TIERS);
    public static final MachineDefinition[] KINETIC_GENERATOR = registerKineticGenerator(ELECTRIC_TIERS);


    //Multiblocks
    public static final MultiblockMachineDefinition REACTION_FURNACE = REGISTRATE
            .multiblock("reaction_furnace", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CGCRecipeTypes.REACTION_FURNACE_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CC CC","CC CC","     ","     ","     ")
                    .aisle("C   C","CXKXC"," XXX "," HHH "," XXX ")
                    .aisle("     "," XXX "," XPX "," HPH "," XXX ")
                    .aisle("C   C","CXXXC"," XSX "," HHH "," XXX ")
                    .aisle("CC CC","CC CC","     ","     ","     ")
                    .where('S', controller(blocks(definition.get())))
                    .where('X', blocks(CASING_STEEL_SOLID.get()).or(Predicates.autoAbilities(definition.getRecipeTypes())).or(abilities(PartAbility.INPUT_ENERGY)))
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .where('C', Predicates.frames(Steel))
                    .where('P', blocks(CASING_BRONZE_PIPE.get()))
                    .where('H', heatingCoils())
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor"), false)
            .tooltips(Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component
                                    .translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                                            100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) +
                                                    "K")
                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                }
            })
            .compassSections(GTCompassSections.TIER[LV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition ADVANCED_LARGE_STEAM_TURBINE = REGISTRATE.multiblock("advanced_large_steam_turbine", holder -> new LargeTurbineMachine(holder, HV))
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.STEAM_TURBINE_FUELS)
            .generator(true)
            .recipeModifier(LargeTurbineMachine::recipeModifier, true)
            .appearanceBlock(CASING_STEEL_TURBINE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("GHHHHHHHH", "GHHHHHHHH", "G   H   H")
                    .aisle("GHHHHHHHH", "RCCCCCCCR", "GHHHHHHHH")
                    .aisle("GHHHHHHHH", "GSHHHHHHH", "G   H   H")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('G', blocks(CASING_STEEL_GEARBOX.get()))
                    .where('C', blocks(COIL_NICHROME.get()))
                    .where('R',
                            new TraceabilityPredicate(
                                    new SimplePredicate(
                                            state -> MetaMachine.getMachine(state.getWorld(),
                                                    state.getPos()) instanceof IRotorHolderMachine rotorHolder &&
                                                    state.getWorld()
                                                            .getBlockState(state.getPos()
                                                                    .relative(rotorHolder.self().getFrontFacing()))
                                                            .isAir(),
                                            () -> PartAbility.ROTOR_HOLDER.getAllBlocks().stream()
                                                    .map(BlockInfo::fromBlock).toArray(BlockInfo[]::new)))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.clear_amount_3"))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                            VN[HV]))
                                    .setExactLimit(1)
                                    .or(abilities(PartAbility.OUTPUT_ENERGY)).setExactLimit(1))
                    .where('H', blocks(CASING_STEEL_TURBINE.get())
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, false, false, true, true))
                            .or(autoAbilities(true, false, false)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/multiblock/advanced_large_turbine"), false)
            .tooltips(
                    Component.translatable("gtceu.universal.tooltip.base_production_eut", V[HV] * 2),
                    Component.translatable("gtceu.multiblock.turbine.efficiency_tooltip", VNF[HV]))
            .compassSections(GTCompassSections.TIER[HV])
            .compassNode("large_turbine")
            .register();

    public static final MultiblockMachineDefinition ADVANCED_LARGE_COMBUSTION_TURBINE = REGISTRATE.multiblock("advanced_large_combustion_turbine", holder -> new LargeTurbineMachine(holder, HV))
            .rotationState(RotationState.ALL)
            .recipeType(ADVANCED_COMBUSTION_RECIPES)
            .generator(true)
            .recipeModifier(LargeTurbineMachine::recipeModifier, true)
            .appearanceBlock(CASING_STEEL_TURBINE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("GHHHHHHHH", "GHHHHHHHH", "G   H   H")
                    .aisle("GHHHHHHHH", "RCCCCCCCR", "GHHHHHHHH")
                    .aisle("GHHHHHHHH", "GSHHHHHHH", "G   H   H")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('G', blocks(CASING_STEEL_GEARBOX.get()))
                    .where('C', blocks(COIL_NICHROME.get()))
                    .where('R',
                            new TraceabilityPredicate(
                                    new SimplePredicate(
                                            state -> MetaMachine.getMachine(state.getWorld(),
                                                    state.getPos()) instanceof IRotorHolderMachine rotorHolder &&
                                                    state.getWorld()
                                                            .getBlockState(state.getPos()
                                                                    .relative(rotorHolder.self().getFrontFacing()))
                                                            .isAir(),
                                            () -> PartAbility.ROTOR_HOLDER.getAllBlocks().stream()
                                                    .map(BlockInfo::fromBlock).toArray(BlockInfo[]::new)))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.clear_amount_3"))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                            VN[HV]))
                                    .setExactLimit(1)
                                    .or(abilities(PartAbility.OUTPUT_ENERGY)).setExactLimit(1))
                    .where('H', blocks(CASING_STEEL_TURBINE.get())
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, false, false, true, true))
                            .or(autoAbilities(true, false, false)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/multiblock/advanced_large_turbine"), false)
            .tooltips(
                    Component.translatable("gtceu.universal.tooltip.base_production_eut", V[HV] * 2),
                    Component.translatable("gtceu.multiblock.turbine.efficiency_tooltip", VNF[HV]))
            .compassSections(GTCompassSections.TIER[HV])
            .compassNode("large_turbine")
            .register();

    public static final MultiblockMachineDefinition FLOTATION_CELL = REGISTRATE
            .multiblock("flotation_cell", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CGCRecipeTypes.FLOTATION_CELL_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_ALUMINIUM_FROSTPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("   B   ", "   B   ", "   B   ", "       ", "       ")
                    .aisle("  AAA  ", "  AAA  ", "  AAA  ", "  AKA  ", "  AAA  ")
                    .aisle(" AAAAA ", " ABBBA ", " ABBBA ", " ADDDA ", " A   A ")
                    .aisle("BAAAAAB", "BABBBAB", "BABBBAB", " ADBDA ", " A E A ")
                    .aisle(" AAAAA ", " ABBBA ", " ABBBA ", " ADDDA ", " A   A ")
                    .aisle("  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ", "  AAA  ")
                    .aisle("   B   ", "   B   ", "   S   ", "       ", "       ")
                    .where('S', controller(blocks(definition.get())))
                    .where('A', blocks(CASING_ALUMINIUM_FROSTPROOF.get()).setMinGlobalLimited(51).or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .where('D', blocks(CASING_FLOTATION.get()))
                    .where('B', blocks(CASING_STEEL_PIPE.get()))
                    .where('E', blocks(CASING_STEEL_GEARBOX.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_frost_proof"),
                    GTCEu.id("block/machines/multiblock/flotation_cell"), false)
            .compassSections(GTCompassSections.TIER[MV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition CLARIFICATION_PLANT = REGISTRATE
            .multiblock("clarification_plant", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CGCRecipeTypes.CLARIFICATION_PLANT_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("      AAAA      ", "      AAAA      ", "      AAAA      ", "                ")
                    .aisle("    AAAAAAAA    ", "    AADDDDAA    ", "    AA    AA    ", "                ")
                    .aisle("   AAAAAAAAAA   ", "   ADDDDDDDDA   ", "   A        A   ", "                ")
                    .aisle("  AAAAAAAAAAAA  ", "  ADDDDDDDDDDA  ", "  A          A  ", "                ")
                    .aisle(" AAAAAAAAAAAAAA ", " ADDDDDDDDDDDDA ", " A            A ", "                ")
                    .aisle(" AAAAAAAAAAAAAA ", " ADDDDDDDDDDDDA ", " A            A ", "                ")
                    .aisle("AAAAAAAAAAAAAAAA", "ADDDDDDDDDDDDDDA", "A              A", "                ")
                    .aisle("AAAAAAAAAAAAAAAA", "ADDDDDDEEDDDDDDA", "A      EE      A", "       EE       ")
                    .aisle("AAAAAAAAAAAAAAAA", "ADDDDDDEEDDDDDDA", "A      EEF     A", "       EE       ")
                    .aisle("AAAAAAAAAAAAAAAA", "ADDDDDDDDDDDDDDA", "A       FFF    A", "                ")
                    .aisle(" AAAAAAAAAAAAAA ", " ADDDDDDDDDDDDA ", " A       FFF  A ", "                ")
                    .aisle(" AAAAAAAAAAAAAA ", " ADDDDDDDDDDDDA ", " A        FFF A ", "                ")
                    .aisle("  AAAAAAAAAAAA  ", "  ADDDDDDDDDDA  ", "  A        FFA  ", "                ")
                    .aisle("   AAAAAAAAAC   ", "   ADDDDDDDDA   ", "   A        A   ", "                ")
                    .aisle("    AAAAAAAAC   ", "    AADDDDAA    ", "    AA    AA    ", "                ")
                    .aisle("      AAAA DKD  ", "      AAAA DSD  ", "      AAAA      ", "                ")
                    .where('S', controller(blocks(definition.get())))
                    .where('A', blocks(LIGHT_CONCRETE.get()).setMinGlobalLimited(250).or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .where('F', Predicates.frames(Steel))
                    .where('D', blocks(CASING_STEEL_SOLID.get()))
                    .where('C', blocks(CASING_STEEL_PIPE.get()))
                    .where('E', blocks(CASING_STEEL_GEARBOX.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/multiblock/clarification_plant"), false)
            .compassSections(GTCompassSections.TIER[LV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition CRUCIBLE = REGISTRATE
            .multiblock("crucible", CrucibleMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_INVAR_HEATPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AKA", "AAA", "AAA")
                    .aisle("AAA", "ALA", "A#A")
                    .aisle("ASA", "AAA", "AAA")
                    .where('S', controller(blocks(definition.get())))
                    .where('A', blocks(CASING_INVAR_HEATPROOF.get()))
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .where('L', blocks(Blocks.LAVA))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_heatproof"),
                    GTCEu.id("block/machines/multiblock/crucible"), false)
            .compassSections(GTCompassSections.TIER[LV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition ADVANCED_ELECTROLYZER = REGISTRATE
            .multiblock("advanced_electrolyzer", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CGCRecipeTypes.ADVANCED_ELECTROLYZER_RECIPES)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCC", "CCKCC", "CCCCC")
                    .aisle("CCCCC", "CPPPC", "CPPPC")
                    .aisle("CCCCC", "CPPPC", "CPPPC")
                    .aisle("CCCCC", "CCSCC", "CCCCC")
                    .where('S', controller(blocks(definition.get())))
                    .where('P', blocks(ELECTROLYTIC_CELL.get()))
                    .where('C', blocks(CASING_STEEL_SOLID.get()).or(autoAbilities()))
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/multiblock/advanced_electrolyzer"), false)
            .compassSections(GTCompassSections.TIER[MV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition ADVANCED_LARGE_CHEMICAL_REACTOR = REGISTRATE
            .multiblock("advanced_large_chemical_reactor", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(CGCRecipeTypes.CHEMICAL_PLANT_RECIPES)
            .recipeModifier(GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(CASING_INVAR_HEATPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XKX", "CCC", "CCC", "XXX")
                    .aisle("XXX", "C#C", "C#C", "XMX")
                    .aisle("XSX", "CCC", "CCC", "XXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(CASING_INVAR_HEATPROOF.get()).setMinGlobalLimited(9)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .where('C', heatingCoils())
                    .where('K', abilities(PartAbility.INPUT_KINETIC))
                    .where('#', air())
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("ISO", "CCC", "CCC", "XMX")
                        .aisle("FXD", "C#C", "C#C", "XHX")
                        .aisle("EKE", "CCC", "CCC", "XXX")
                        .where('X', CASING_INVAR_HEATPROOF.getDefaultState())
                        .where('S', definition, Direction.NORTH)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('F', FLUID_IMPORT_HATCH[GTValues.LV], Direction.WEST)
                        .where('D', FLUID_EXPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('H', MUFFLER_HATCH[GTValues.LV], Direction.UP)
                        .where('K', KINETIC_INPUT_BOX[LV].defaultBlockState())
                        .where('M', MAINTENANCE_HATCH, Direction.NORTH);
                GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(
                                coil -> shapeInfo.add(builder.shallowCopy().where('C', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_heatproof"),
                    GTCEu.id("block/machines/chemical_plant"), false)
            .tooltips(Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.0"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.1"),
                    Component.translatable("gtceu.machine.electric_blast_furnace.tooltip.2"))
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component
                                    .translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                                            100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) +
                                                    "K")
                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY))));
                }
            })
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static KineticMachineDefinition[] registerSimpleKineticElectricMachine(String name, GTRecipeType recipeType, Int2LongFunction tankSizeFunction, int... tiers) {
        return registerTieredMachines(name, (tier, id) -> new KineticMachineDefinition(id, false, GTValues.V[tier]),
                (holder, tier) -> new SimpleKineticElectricWorkableMachine(holder, tier, tankSizeFunction),
                (tier, builder) -> builder
                        .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                        .blockProp(BlockBehaviour.Properties::dynamicShape)
                        .blockProp(BlockBehaviour.Properties::noOcclusion)
                        .recipeType(recipeType)
                        .recipeModifier(
                                GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                        .renderer(() -> new KineticWorkableTieredHullMachineRenderer(tier,
                                GTCEu.id("block/machine/kinetic_electric_machine"), GTCEu.id("block/machines/" + name)))
                        .tooltips(explosion())
                        .tooltips(workableTiered(tier, V[tier], V[tier] * 64, recipeType,
                                tankSizeFunction.apply(tier), true))
                        .register(),
                () -> SplitShaftInstance::new, false, tiers);
    }

    public static KineticMachineDefinition[] registerKineticGenerator(int... tiers) {
        return registerTieredMachines("kinetic_generator",
                (tier, id) -> new KineticMachineDefinition(id, true, V[tier]),
                KineticGeneratorMachine::new, (tier, builder) -> builder
                        .langValue("%s %s Generator %s".formatted(VLVH[tier], "Kinetic Generator", VLVT[tier]))
                        .rotationState(RotationState.ALL)
                        .blockProp(BlockBehaviour.Properties::dynamicShape)
                        .blockProp(BlockBehaviour.Properties::noOcclusion)
                        .renderer(() -> new KineticGeneratorRenderer(tier,
                                GTCEu.id("block/machine/kinetic_electric_machine"), GTCEu.id("block/machines/kinetic_generator")))
                        .tooltips(explosion())
                        .register(),
                () -> SplitShaftInstance::new, false, tiers);
    }

    public static void init() {}
}
