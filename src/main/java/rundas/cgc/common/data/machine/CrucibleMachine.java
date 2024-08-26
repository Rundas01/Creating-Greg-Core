package rundas.cgc.common.data.machine;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.gui.widget.TankWidget;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTMaterials.Lava;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrucibleMachine extends MultiblockControllerMachine implements IUIMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(CrucibleMachine.class,
            MultiblockControllerMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    private int temperatureModifier = 0;
    @Persisted
    private int stoneAmount = 0;
    private final int maxStoneAmount = 64000;
    @Persisted
    public final NotifiableItemStackHandler importItems;
    @Persisted
    public final NotifiableFluidTank importFluids;
    @Persisted
    public final NotifiableFluidTank exportFluids;
    private TickableSubscription consumeStoneSubscription;
    private TickableSubscription produceLavaSubscription;
    private TickableSubscription temperatureSubscription;

    public CrucibleMachine(IMachineBlockEntity holder) {
        super(holder);
        this.importItems = createImportItemHandler();
        this.importFluids = createImportFluidHandler();
        this.exportFluids = createExportFluidHandler();
    }

    protected NotifiableItemStackHandler createImportItemHandler() {
        return new NotifiableItemStackHandler(this, 1, IO.IN);
    }

    protected NotifiableFluidTank createImportFluidHandler() {
        return new NotifiableFluidTank(this, 1, FluidHelper.getBucket(), IO.IN);
    }

    protected NotifiableFluidTank createExportFluidHandler() {
        return new NotifiableFluidTank(this, 1, 32 * FluidHelper.getBucket(), IO.OUT);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        temperatureSubscription = subscribeServerTick(this::setTemperatureModifier);
        consumeStoneSubscription = subscribeServerTick(this::consumeStone);
        produceLavaSubscription = subscribeServerTick(this::produceLava);
        setTemperatureModifier();
        consumeStone();
        produceLava();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        resetState();
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
        resetState();
    }

    @Override
    public void onUnload() {
        super.onUnload();
        resetState();
    }

    private void resetState() {
        unsubscribe(temperatureSubscription);
        unsubscribe(consumeStoneSubscription);
        unsubscribe(produceLavaSubscription);
        stoneAmount = 0;
        temperatureModifier = 0;
    }

    private void consumeStone() {
        if (getOffsetTimer() % 20 != 0) {
            return;
        }
        if (!isFormed()) {
            return;
        }
        if (getMultiblockState().hasError()) {
            return;
        }
        if (importItems == null) {
            return;
        }
        if (maxStoneAmount - stoneAmount < 1000) {
            return;
        }
        if (importItems.getStackInSlot(0) == ItemStack.EMPTY) {
            return;
        }
        if (importItems.extractItem(0,1,true) != ItemStack.EMPTY){
            importItems.extractItem(0,1,false);
            stoneAmount += 1000;
        }
    }

    private void produceLava() {
        if (getOffsetTimer() % 20 != 0) {
            return;
        }
        if (!isFormed()) {
            return;
        }
        if (getMultiblockState().hasError()) {
            return;
        }
        if (exportFluids == null) {
            return;
        }
        if (stoneAmount < temperatureModifier) {
            return;
        }
        if (temperatureModifier <= 0) {
            return;
        }
        exportFluids.handleRecipe(IO.OUT, null, List.of(FluidIngredient.of(Lava.getFluid(temperatureModifier))), null, false);
    }

    private void setTemperatureModifier() {
        if (!isFormed()) {
            return;
        }
        if (getMultiblockState().hasError()) {
            return;
        }
        FluidStack stack = importFluids.getFluidInTank(0);
        if (stack != FluidStack.empty()) {
            temperatureModifier = Math.max(0,stack.getFluid().getFluidType().getTemperature() - Lava.getFluid().getFluidType().getTemperature() + 1);
        } else {
            temperatureModifier = 0;
        }
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(176, 166, this, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(new LabelWidget(5, 5, getBlockState().getBlock().getDescriptionId()))
                .widget(new LabelWidget(5, 20, "Stone Amount: " + stoneAmount/1000 + "k"))
                .widget(new LabelWidget(5, 35, "Rate: " + temperatureModifier + " mb/s"))
                .widget(new SlotWidget(importItems.storage, 0, 52, 45, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(GuiTextures.SLOT)))
                .widget(new TankWidget(exportFluids.getStorages()[0], 134, 13, 20, 58, true, false)
                        .setBackground(GuiTextures.FLUID_TANK_BACKGROUND)
                        .setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP)
                        .setShowAmount(false)
                        .setOverlay(GuiTextures.FLUID_TANK_OVERLAY))
                .widget(new TankWidget(importFluids.getStorages()[0], 104, 13, 20, 58, true, true)
                        .setBackground(GuiTextures.FLUID_TANK_BACKGROUND)
                        .setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP)
                        .setShowAmount(false)
                        .setOverlay(GuiTextures.FLUID_TANK_OVERLAY))
                .widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(), GuiTextures.SLOT, 7, 84, true));
    }
}
