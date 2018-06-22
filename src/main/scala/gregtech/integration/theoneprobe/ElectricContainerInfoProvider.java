package gregtech.integration.theoneprobe;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IWorkable;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ElectricContainerInfoProvider extends CapabilityInfoProvider<IEnergyContainer> {

    @Override
    protected Capability<IEnergyContainer> getCapability() {
        return IEnergyContainer.CAPABILITY_ENERGY_CONTAINER;
    }

    @Override
    public String getID() {
        return "gregtech:energy_container_provider";
    }

    @Override
    protected void addProbeInfo(IEnergyContainer capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        long energyStored = capability.getEnergyStored();
        long maxStorage = capability.getEnergyCapacity();
        if(maxStorage == 0) return; //do not add empty max storage progress bar
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        String additionalSpacing = tileEntity.hasCapability(IWorkable.CAPABILITY_WORKABLE, sideHit) ? "   " : "";
        horizontalPane.text(TextStyleClass.INFO + "{*gregtech.top.energy_stored*} " + additionalSpacing);
        horizontalPane.progress(energyStored, maxStorage, probeInfo.defaultProgressStyle()
            .suffix("/" + maxStorage + " EU")
            .borderColor(0x00000000)
            .backgroundColor(0x00000000)
            .filledColor(0xFFFFE000)
            .alternateFilledColor(0xFFEED000));
    }

}
