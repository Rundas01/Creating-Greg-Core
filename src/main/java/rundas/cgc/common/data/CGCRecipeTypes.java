package rundas.cgc.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.sound.ExistingSoundEntry;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.recipe.RPMCondition;
import com.gregtechceu.gtceu.common.recipe.RockBreakerCondition;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.misc.FluidStorage;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.simibubi.create.AllBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluids;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.KINETIC;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.UP_TO_DOWN;
import static rundas.cgc.CreatingGregCore.id;

public class CGCRecipeTypes {

    public static final GTRecipeType ORE_CRACKER_RECIPES = register("ore_cracking", KINETIC)
            .setMaxIOSize(2, 6, 1, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.ORE_CRACKER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType BATCH_REACTOR_RECIPES = register("batch_reacting", KINETIC)
            .setMaxIOSize(6, 6, 6, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.BATCH_REACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType GAS_BUBBLE_REACTOR_RECIPES = register("gas_bubble_reacting", KINETIC)
            .setMaxIOSize(0, 2, 2, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.GAS_BUBBLE_REACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType MIXING_REACTOR_RECIPES = register("mixing_reacting", KINETIC)
            .setMaxIOSize(0, 2, 2, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.MIXING_REACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType DISSOLUTION_REACTOR_RECIPES  = register("dissolution_reacting", KINETIC)
            .setMaxIOSize(1, 2, 1, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.DISSOLUTION_REACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType CATALYTIC_REACTOR_RECIPES = register("catalytic_reacting", KINETIC)
            .setMaxIOSize(3, 2, 3, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.CATALYTIC_REACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType POLYMERIZER_RECIPES = register("polymerizing", KINETIC)
            .setMaxIOSize(0, 0, 2, 3).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.POLYMERIZER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    //Furnace
    public static GTRecipeType KINETIC_FURNACE_RECIPES = register("kinetic_smelting", KINETIC)
            .setMaxIOSize(1, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_FURNACE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FURNACE);
    //Arc
    public static GTRecipeType KINETIC_ARC_RECIPES = register("kinetic_arc_smelting", KINETIC)
            .setMaxIOSize(2, 2, 2, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_ARC_FURNACE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ARC);
    //Autoclave
    public static GTRecipeType KINETIC_AUTOCLAVE_RECIPES = register("kinetic_autoclaving", KINETIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_AUTOCLAVE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.BATH);
    //Canner
    public static GTRecipeType KINETIC_CANNER_RECIPES = register("kinetic_canning", KINETIC)
            .setMaxIOSize(2, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CANNER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_CANNER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.MOTOR);
    //Cutter
    public static GTRecipeType KINETIC_CUTTER_RECIPES = register("kinetic_cutting", KINETIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_CUTTER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CUT);
    //Aloy
    public static final GTRecipeType KINETIC_ALLOY_SMELTER_RECIPES = register("kinetic_alloying", KINETIC)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_ALLOY_SMELTER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FURNACE);
    //Hammer
    public static final GTRecipeType KINETIC_HAMMER_RECIPES = register("kinetic_hammering", KINETIC)
            .setMaxIOSize(1, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, UP_TO_DOWN)
            .setIconSupplier(() -> CGCMachines.KINETIC_HAMMER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FORGE_HAMMER);
    //Extractor
    public static final GTRecipeType KINETIC_EXTRACTOR_RECIPES = register("kinetic_extracting", KINETIC)
            .setMaxIOSize(1, 1, 0, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_EXTRACTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CENTRIFUGE);
    //Compressor
    public static final GTRecipeType KINETIC_COMPRESSOR_RECIPES = register("kinetic_compressing", KINETIC)
            .setMaxIOSize(1, 1, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_COMPRESSOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.COMPRESSOR);
    //Macerator
    public static final GTRecipeType KINETIC_MACERATOR_RECIPES = register("kinetic_macerating", KINETIC)
            .setMaxIOSize(1, 4, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_MACERATOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.MACERATOR);
    //Lathe
    public static final GTRecipeType KINETIC_LATHE_RECIPES = register("kinetic_lathing", KINETIC)
            .setMaxIOSize(1, 2, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_LATHE, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_LATHE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CUT);
    //Engraver
    public static final GTRecipeType KINETIC_ENGRAVER_RECIPES = register("kinetic_engraving", KINETIC)
            .setMaxIOSize(1, 1, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_ENGRAVER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ELECTROLYZER);
    //Washer
    public static final GTRecipeType KINETIC_WASHER_RECIPES = register("kinetic_washing", KINETIC)
            .setMaxIOSize(1, 3, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_WASHER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.BATH);
    //Bath
    public static final GTRecipeType KINETIC_BATH_RECIPES = register("kinetic_bathing", KINETIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_BATH[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.BATH);
    //Wiremill
    public static final GTRecipeType KINETIC_WIREMILL_RECIPES = register("kinetic_wiremilling", KINETIC)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_WIREMILL, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_WIREMILL[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.MOTOR);
    //Separator
    public static final GTRecipeType KINETIC_SEPARATOR_RECIPES = register("kinetic_separating", KINETIC)
            .setMaxIOSize(1, 3, 1, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_SEPARATOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ELECTROLYZER);
    //Elec
    public static final GTRecipeType KINETIC_ELECTROLYZER_RECIPES = register("kinetic_electrolyzing", KINETIC)
            .setMaxIOSize(1, 6, 1, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_ELECTROLYZER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ELECTROLYZER);
    //Thermal
    public static final GTRecipeType KINETIC_THERMAL_CENTRIFUGE_RECIPES = register("kinetic_thermal_centrifuging", KINETIC)
            .setMaxIOSize(1, 3, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_THERMAL_CENTRIFUGE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CENTRIFUGE);
    //Cent
    public static final GTRecipeType KINETIC_CENTRIFUGE_RECIPES = register("kinetic_centrifuging", KINETIC)
            .setMaxIOSize(1, 6, 1, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_CENTRIFUGE[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CENTRIFUGE);
    //Yeeter
    public static final GTRecipeType KINETIC_HEATER_RECIPES = register("kinetic_heating", KINETIC)
            .setMaxIOSize(0, 0, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_HEATER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.BOILER);
    //Fermenter
    public static final GTRecipeType KINETIC_FERMENTER_RECIPES = register("kinetic_fermenting", KINETIC)
            .setMaxIOSize(1, 0, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_FERMENTER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);
    //Distillery
    public static final GTRecipeType KINETIC_DISTILLERY_RECIPES = register("kinetic_distilling", KINETIC)
            .setMaxIOSize(1, 1, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_DISTILLERY[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);
    //Ass
    public static final GTRecipeType KINETIC_ASSEMBLER_RECIPES = register("kinetic_assembling", KINETIC)
            .setMaxIOSize(9, 1, 3, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_ASSEMBLER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ASSEMBLER);
    //Circuit Ass
    public static final GTRecipeType KINETIC_CIRCUIT_ASSEMBLER_RECIPES = register("kinetic_circuit_assembling", KINETIC)
            .setMaxIOSize(9, 1, 3, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_CIRCUIT_ASSEMBLER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ASSEMBLER);
    //Bender
    public static final GTRecipeType KINETIC_BENDER_RECIPES = register("kinetic_bending", KINETIC)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_BENDER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.MOTOR);
    //Solidifier
    public static final GTRecipeType KINETIC_SOLIDIFIER_RECIPES = register("kinetic_solidifying", KINETIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_SOLIDIFIER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.COOLING);
    //Press
    public static final GTRecipeType KINETIC_PRESS_RECIPES = register("kinetic_pressing", KINETIC)
            .setMaxIOSize(6, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_PRESS[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.COMPRESSOR);
    //Extruder
    public static final GTRecipeType KINETIC_EXTRUDER_RECIPES = register("kinetic_extruding", KINETIC)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_EXTRUDER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.MOTOR);
    //Packer
    public static final GTRecipeType KINETIC_PACKER_RECIPES = register("kinetic_packing", KINETIC)
            .setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_PACKER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ASSEMBLER);
    //Polarizer
    public static final GTRecipeType KINETIC_POLARIZER_RECIPES = register("kinetic_polarizing", KINETIC)
            .setMaxIOSize(1, 1, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_POLARIZER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.ELECTROLYZER);
    //Sifter
    public static final GTRecipeType KINETIC_SIFTER_RECIPES = register("kinetic_sifting", KINETIC)
            .setMaxIOSize(1, 9, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, UP_TO_DOWN)
            .setIconSupplier(() -> CGCMachines.KINETIC_SIFTER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(new ExistingSoundEntry(SoundEvents.SAND_PLACE, SoundSource.BLOCKS));
    //Gas Collector
    public static final GTRecipeType KINETIC_COLLECTOR_RECIPES = register("kinetic_gas_collecting", KINETIC)
            .setMaxIOSize(1, 0, 0, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_COLLECTOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.COOLING);
    //Cock Breaker
    public static final GTRecipeType KINETIC_BREAKER_RECIPES = register("kinetic_breaking", KINETIC)
            .setMaxIOSize(1, 4, 0, 0).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_CRUSHER[GTValues.LV].asStack())
            .prepareBuilder(recipeBuilder -> recipeBuilder.addCondition(RockBreakerCondition.INSTANCE))
            .setUiBuilder((recipe, widgetGroup) -> {
                var fluidA = BuiltInRegistries.FLUID.get(new ResourceLocation(recipe.data.getString("fluidA")));
                var fluidB = BuiltInRegistries.FLUID.get(new ResourceLocation(recipe.data.getString("fluidB")));
                if (fluidA != Fluids.EMPTY) {
                    widgetGroup.addWidget(new TankWidget(new FluidStorage(FluidStack.create(fluidA, 1000)),
                            widgetGroup.getSize().width - 30, widgetGroup.getSize().height - 30, false, false)
                            .setBackground(GuiTextures.FLUID_SLOT).setShowAmount(false));
                }
                if (fluidB != Fluids.EMPTY) {
                    widgetGroup.addWidget(new TankWidget(new FluidStorage(FluidStack.create(fluidB, 1000)),
                            widgetGroup.getSize().width - 30 - 20, widgetGroup.getSize().height - 30, false, false)
                            .setBackground(GuiTextures.FLUID_SLOT).setShowAmount(false));
                }
            })
            .setMaxTooltips(5)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FIRE);
    //Brewer
    public static final GTRecipeType KINETIC_BREWER_RECIPES = register("kinetic_brewing", KINETIC)
            .setMaxIOSize(1, 0, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_BREWER[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);
    //Mass Fap
    public static final GTRecipeType KINETIC_MASS_FABRICATOR_RECIPES = register("kinetic_mass_fabricating", KINETIC)
            .setMaxIOSize(1, 0, 0, 2).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_FABRICATOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.SCIENCE);
    //Replicator
    public static final GTRecipeType KINETIC_REPLICATOR_RECIPES = register("kinetic_replicating", KINETIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, LEFT_TO_RIGHT)
            .setIconSupplier(() -> CGCMachines.KINETIC_REPLICATOR[GTValues.LV].asStack())
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.SCIENCE);

    public final static GTRecipeType REACTION_FURNACE_RECIPES = register("reaction_furnace", MULTIBLOCK)
            .setMaxIOSize(6, 6, 6, 6)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE, LEFT_TO_RIGHT)
            .setMaxTooltips(5)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FURNACE);

    public final static GTRecipeType ADVANCED_COMBUSTION_RECIPES = register("advanced_combustion_turbine", MULTIBLOCK)
            .setMaxIOSize(0, 0, 2, 3)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.FURNACE);

    public static final GTRecipeType CHEMICAL_PLANT_RECIPES = register("chemical_plant", MULTIBLOCK)
            .setMaxIOSize(6, 6, 6, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setMaxTooltips(5)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType FLOTATION_CELL_RECIPES = register("flotating", MULTIBLOCK)
            .setMaxIOSize(0, 0, 3, 6).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType CLARIFICATION_PLANT_RECIPES = register("clarifying", MULTIBLOCK)
            .setMaxIOSize(0, 1, 1, 1).setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setSound(GTSoundEntries.CHEMICAL);

    public static final GTRecipeType HEAT_EXCHANGER_RECIPES = register("heat_exchanging", MULTIBLOCK)
            .setMaxIOSize(0, 0, 2, 2).setEUIO(IO.IN)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType ADVANCED_ELECTROLYZER_RECIPES = register("advanced_electrolysis", MULTIBLOCK)
            .setMaxIOSize(3, 3, 3, 3).setEUIO(IO.IN)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (!recipe.conditions.isEmpty() && recipe.conditions.get(0) instanceof RPMCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30,
                            group.getSize().height - 30, false, false));
                }
            })
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ELECTROLYZER);

    public static GTRecipeType register(String name, String group, RecipeType<?>... proxyRecipes) {
        var recipeType = new GTRecipeType(id(name), group, proxyRecipes);
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeType.registryName, new GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }

    public static void init() {}
}
