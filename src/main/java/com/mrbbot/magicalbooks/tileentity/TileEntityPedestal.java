package com.mrbbot.magicalbooks.tileentity;

import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPedestal extends TileEntity {
    public float rotation = 0f;

    private ItemStack itemStack;

    public TileEntityPedestal() {
        super();
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        writeSyncableDataToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        readSyncableDataFromNBT(data);
    }

    public void writeSyncableDataToNBT(NBTTagCompound data) {
        NBTTagCompound itemData = new NBTTagCompound();
        if(itemStack != null)
            itemStack.writeToNBT(itemData);
        data.setTag(Names.NBT.PEDESTAL_ITEM, itemData);
        data.setFloat(Names.NBT.PEDESTAL_ROTATION, rotation);
    }

    public void readSyncableDataFromNBT(NBTTagCompound data) {
        NBTTagCompound itemData = data.getCompoundTag(Names.NBT.PEDESTAL_ITEM);
        itemStack = ItemStack.loadItemStackFromNBT(itemData);
        rotation = data.getFloat(Names.NBT.PEDESTAL_ROTATION);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(pos, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readSyncableDataFromNBT(pkt.getNbtCompound());
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        markForUpdate();
        this.itemStack = itemStack;
    }

    public void markForUpdate() {
        worldObj.markBlockForUpdate(pos);
        markDirty();
    }

    public void dropItemStack() {
        if(itemStack != null) {
            EntityItem item = new EntityItem(worldObj, pos.getX(), pos.getY() + 1, pos.getZ(), itemStack);
            worldObj.spawnEntityInWorld(item);
            setItemStack(null);
        }
    }
}
