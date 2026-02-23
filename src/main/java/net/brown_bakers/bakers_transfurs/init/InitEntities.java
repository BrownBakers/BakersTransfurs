package net.brown_bakers.bakers_transfurs.init;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;

import net.minecraft.world.entity.EntityType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class InitEntities
{
	//Global Entity registry
	public static DeferredRegister<EntityType<?>> ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BakersTransfurs.MODID);
	
	//Global Entity Object map. Accessed by every single class within the entity package.
	public static Map<String, RegistryObject<EntityType<?>>> ENTITY_OBJECTS;
	
	

	

	
	
}
