package net.kjentytek303.additional_transfurs.init;

import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class InitItems
{
	public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AdditionalTransfurs.MODID);
	public static final HashMap<RegistryObject<? extends EntityType<?>>, RegistryObject<ForgeSpawnEggItem> > SPAWN_EGGS = new HashMap<>();
}
