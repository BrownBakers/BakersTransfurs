package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.minecraft.world.entity.EntityType;
import net.ltxprogrammer.changed.init.ChangedTransfurVariants;
import net.ltxprogrammer.changed.entity.ChangedEntity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.brown_bakers.bakers_transfurs.init.InitItems.ITEM_REGISTRY;

public interface IEntityInit {

	default public static EntityType.Builder<? extends ChangedEntity> initEntity() {
		throw new NotImplementedException("Bad Usage - Entity not registered properly");
	}
	
	//TODO
	//public static ChangedTransfurVariants.Builder<? extends ChangedEntity> initVariant();
	public static <T extends ChangedEntity> RegistryObject<EntityType<T>> registerSpawning(
		   String name,
		   int eggBack,
		   int eggHighlight,
		   EntityType.Builder<T> builder,
		   Predicate<Level> dimension,
		   SpawnPlacements.Type spawnType,
		   SpawnPlacements.SpawnPredicate<T> spawnPredicate,
		   Supplier<AttributeSupplier.Builder> attributes
	) {
		ENTITY_COLOR_MAP.put(BakersTransfurs.modResource(name), new Pair<>(eggBack, eggHighlight));
		String regName = Changed.modResource(name).toString();
		RegistryObject<EntityType<T>> entityType = REGISTRY.register(name, () -> builder.build(regName));
		INIT_FUNC_REGISTRY.add(ChangedEntity.getInit(entityType, spawnType, spawnPredicate));
		ATTR_FUNC_REGISTRY.add(new Pair<>(entityType::get, attributes));
		RegistryObject<ForgeSpawnEggItem> spawnEggItem = ITEM_REGISTRY.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, eggBack, eggHighlight, new Item.Properties()));
		SPAWN_EGGS.put(entityType, spawnEggItem);
		DIMENSION_RESTRICTIONS.put(entityType, dimension);
		return entityType;
	}

}
