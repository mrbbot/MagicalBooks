package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.item.*;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModItems {
    public static final ItemMagicalBooks itemActivationRod = new ItemActivationRod();
    public static final ItemMagicalBooks bookGrowth = new ItemBookGrowth();
    public static final ItemMagicalBooks bookCraft = new ItemBookCraft();
    public static final ItemMagicalBooks bookChest = new ItemBookChest();
    public static final ItemMagicalBooks bookStar = new ItemBookNetherStar();
    public static final ItemMagicalBooks bookKnowledge = new ItemBookKnowledge();
    public static final ItemMagicalBooks itemActivationStick = new ItemActivationStick();

    public static final ArrayList<ItemStack> advancedItems = new ArrayList<ItemStack>();

    public static ItemStack fireActivationRod;

    public static void init() {
        fireActivationRod = new ItemStack(ModItems.itemActivationRod);
        fireActivationRod.addEnchantment(Enchantment.fireAspect, 2);

        GameRegistry.registerItem(itemActivationStick, Names.Items.ACTIVATION_STICK);
        GameRegistry.registerItem(itemActivationRod, Names.Items.ACTIVATION_ROD);

        GameRegistry.registerItem(bookCraft, Names.Items.BOOK_CRAFT);
        GameRegistry.registerItem(bookChest, Names.Items.BOOK_CHEST);

        GameRegistry.registerItem(bookStar, Names.Items.BOOK_NETHER_STAR);
        advancedItems.add(new ItemStack(bookStar));
        GameRegistry.registerItem(bookGrowth, Names.Items.BOOK_GROWTH);
        advancedItems.add(new ItemStack(bookGrowth));

        GameRegistry.registerItem(bookKnowledge, Names.Items.BOOK_KNOWLEDGE);
    }

    public static void registerRenderers() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(ModItems.bookCraft, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_CRAFT, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookChest, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_CHEST, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookGrowth, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_GROWTH, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookStar, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_NETHER_STAR, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookKnowledge, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_KNOWLEDGE, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.itemActivationRod, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.ACTIVATION_ROD, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.itemActivationStick, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.ACTIVATION_STICK, "inventory"));
    }
}
