package rundas.cgc.common.data.machine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.common.machine.kinetic.IKineticMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.simibubi.create.content.kinetics.KineticNetwork;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class KineticGeneratorMachine extends TieredEnergyMachine implements IKineticMachine, IFancyUIMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            KineticGeneratorMachine.class, TieredEnergyMachine.MANAGED_FIELD_HOLDER);

    public KineticGeneratorMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier, Math.min(1, 2 * tier));
    }

    //////////////////////////////////////
    // ***** Initialization *****//
    //////////////////////////////////////

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    protected NotifiableEnergyContainer createEnergyContainer(Object... args) {
        var tierVoltage = GTValues.V[tier];
        var amps = (int) args[0];
        NotifiableEnergyContainer container = NotifiableEnergyContainer.emitterContainer(this, tierVoltage * 64L, tierVoltage, amps);
        container.setCapabilityValidator(dir -> dir.getAxis() != getRotationFacing().getAxis());
        container.setSideOutputCondition(side -> !hasFrontFacing() || side == getFrontFacing());
        return container;
    }

    @Override
    protected boolean isEnergyEmitter() {
        return true;
    }

    @Override
    protected long getMaxInputOutputAmperage() {
        return 1;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        subscribeServerTick(this::convertRotationalEnergy);
    }

    @Override
    public void onRotated(Direction oldFacing, Direction newFacing) {
        super.onRotated(oldFacing, newFacing);
        if (!isRemote()) {
            if (oldFacing.getAxis() != newFacing.getAxis()) {
                var holder = getKineticHolder();
                if (holder.hasNetwork()) {
                    holder.getOrCreateNetwork().remove(holder);
                }
                holder.detachKinetics();
                holder.removeSource();
            }
        }
    }

    //////////////////////////////////////
    // ***** Rotation Logic *****//
    //////////////////////////////////////

    @Override
    public float getRotationSpeedModifier(Direction direction) {
        if (direction == getRotationFacing().getOpposite())
            return -1;
        return 1;
    }

    protected void convertRotationalEnergy() {
        var holder = getKineticHolder();
        var container = this.energyContainer;
        long stressValue = (long) (Math.min(2 * container.getEnergyCanBeInserted(), Math.max(64 * Math.pow(4,tier-1), holder.getSpeed())));
        long euValue = stressValue/2;
        if (container.getEnergyCanBeInserted() > 0){
            if (holder.hasNetwork()){
                KineticNetwork network = holder.getOrCreateNetwork();
                holder.scheduleWorking(stressValue);
                network.updateStressFor(holder, stressValue);
                container.addEnergy(euValue);
                network.updateStress();
            }
        }else{
            holder.stopWorking();
        }
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return false;
    }

    @Override
    public boolean hasPlayerInventory() {
        return false;
    }
}

