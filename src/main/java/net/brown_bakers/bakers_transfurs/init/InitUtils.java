package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;
import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.brown_bakers.bakers_transfurs.init.InitEntities.*;
import static net.brown_bakers.bakers_transfurs.init.InitEntities.DIMENSION_RESTRICTIONS;
import static net.brown_bakers.bakers_transfurs.init.InitEntities.INIT_ATTRIBS;
import static net.brown_bakers.bakers_transfurs.init.InitItems.ITEM_REGISTRY;
import static net.brown_bakers.bakers_transfurs.init.InitItems.SPAWN_EGGS;

public class InitUtils
{
	public static <T extends ChangedEntity> RegistryObject<EntityType<T>> getInitRObject(String name, int eggBack, int eggHighlight, EntityType.Builder<T> builder, @Nullable Predicate<Level> dimension, @Nullable SpawnPlacements.Type spawnType, @Nullable SpawnPlacements.SpawnPredicate<T> spawnPredicate, Supplier<AttributeSupplier.Builder> attributes) {
		ENTITY_COLORS.put(name, new Pair<>(eggBack, eggHighlight));
		String regName = BakersTransfurs.modResource(name).toString();
		RegistryObject<EntityType<T>> entityType = ENTITY_REGISTRY.register(name, () -> builder.build(regName));
		if ( spawnType != null && dimension != null ) {
			INIT_FUNCTIONS.add(ChangedEntity.getInit(entityType, spawnType, spawnPredicate));
		}
		INIT_ATTRIBS.add(new Pair<>(entityType::get, attributes));
		RegistryObject<ForgeSpawnEggItem> spawnEggItem = ITEM_REGISTRY.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, eggBack, eggHighlight, new Item.Properties()));
		SPAWN_EGGS.put(entityType, spawnEggItem);
		if ( dimension != null )	{
			DIMENSION_RESTRICTIONS.put(entityType, dimension);
		}
		return entityType;
	}
}
