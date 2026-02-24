package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;
import net.brown_bakers.bakers_transfurs.BakersTransfurs;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class InitEntities
{
	//Global Entity registry
	public static final DeferredRegister<EntityType<?>> ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BakersTransfurs.MODID);
	
	public static final HashMap<String, Pair<Integer, Integer>> ENTITY_COLORS = new HashMap<>();
	
	
	

	

	
	
}
