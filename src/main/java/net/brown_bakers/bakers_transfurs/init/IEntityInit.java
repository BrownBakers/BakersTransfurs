package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.minecraft.world.entity.EntityType;
import net.ltxprogrammer.changed.entity.ChangedEntity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.brown_bakers.bakers_transfurs.init.InitEntities.ENTITY_COLORS;
import static net.brown_bakers.bakers_transfurs.init.InitEntities.ENTITY_REGISTRY;
import static net.brown_bakers.bakers_transfurs.init.InitItems.ITEM_REGISTRY;

public interface IEntityInit {
	
	/**
	 * Methods:
	 * static EntityType.Builder<? extends ChangedEntity> initEntity() - Initializes entity.
	 *
	 */
	
	public static EntityType.Builder<? extends ChangedEntity> initEntity() {

	
	};
	
	public static EntityType.Builder<? extends ChangedEntity> getInitBuilder() {
		throw new NotImplementedException("Bad Usage");
	}
	
	//TODO
	public static <T extends ChangedEntity> RegistryObject<EntityType<T>> getInitRObject(
		   String name,
		   int eggBack,
		   int eggHighlight,
		   EntityType.Builder<T> builder,
		   @Nullable Predicate<Level> dimension,
		   @Nullable SpawnPlacements.Type spawnType,
		   @Nullable SpawnPlacements.SpawnPredicate<T> spawnPredicate,
		   Supplier<AttributeSupplier.Builder> attributes
	) {
		//TODO - FIX
		ENTITY_COLORS.put(name, new Pair<>(eggBack, eggHighlight));
		String regName = BakersTransfurs.modResource(name).toString();
		RegistryObject<EntityType<T>> entityType = ENTITY_REGISTRY.register(name, () -> builder.build(regName));
		SpawnPlacements.register(registryObject.get(), spawnPlacement, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, spawnPredicate);
		ATTR_FUNC_REGISTRY.add(new Pair<>(entityType::get, attributes));
		RegistryObject<ForgeSpawnEggItem> spawnEggItem = ITEM_REGISTRY.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, eggBack, eggHighlight, new Item.Properties()));
		SPAWN_EGGS.put(entityType, spawnEggItem);
		DIMENSION_RESTRICTIONS.put(entityType, dimension);
		return entityType;
	}

}
