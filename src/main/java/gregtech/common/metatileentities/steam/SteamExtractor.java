package gregtech.common.metatileentities.steam;

import gregtech.apiOld.capability.impl.NotifiableItemStackHandler;
import gregtech.apiOld.gui.GuiTextures;
import gregtech.apiOld.gui.ModularUI;
import gregtech.apiOld.gui.widgets.ProgressWidget.MoveType;
import gregtech.apiOld.metatileentity.MetaTileEntity;
import gregtech.apiOld.metatileentity.SteamMetaTileEntity;
import gregtech.apiOld.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.apiOld.recipes.RecipeMaps;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SteamExtractor extends SteamMetaTileEntity {

    public SteamExtractor(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, RecipeMaps.EXTRACTOR_RECIPES, Textures.EXTRACTOR_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new SteamExtractor(metaTileEntityId, isHighPressure);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(1, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 53, 25, GuiTextures.SLOT_STEAM.get(isHighPressure), GuiTextures.EXTRACTOR_OVERLAY_STEAM.get(isHighPressure))
                .progressBar(workableHandler::getProgressPercent, 79, 25, 20, 18,
                        GuiTextures.PROGRESS_BAR_EXTRACT_STEAM.get(isHighPressure), MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .slot(this.exportItems, 0, 107, 25, true, false, GuiTextures.SLOT_STEAM.get(isHighPressure))
                .build(getHolder(), player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected void randomDisplayTick(float x, float y, float z, EnumParticleTypes flame, EnumParticleTypes smoke) {
        getWorld().spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0, 0, 0);
    }
}
