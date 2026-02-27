package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;

import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.init.ChangedEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InitEntities
{
	//Global Entity registry
	public static final DeferredRegister<EntityType<?>> ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BakersTransfurs.MODID);
	
	public static final HashMap<String, Pair<Integer, Integer>> ENTITY_COLORS = new HashMap<>();
	
	public static final List<ChangedEntities.VoidConsumer> INIT_FUNCTIONS = new ArrayList<>();
	
	public static final List<Pair<Supplier<EntityType<? extends ChangedEntity>>, Supplier<AttributeSupplier.Builder>>> INIT_ATTRIBS = new ArrayList<>();
	
	public static final Map<Supplier<? extends EntityType<?>>, Predicate<Level>> DIMENSION_RESTRICTIONS = new HashMap<>();
	
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork( () -> INIT_FUNCTIONS.forEach(ChangedEntities.VoidConsumer::accept) );
	}
	

	

	
	
}
