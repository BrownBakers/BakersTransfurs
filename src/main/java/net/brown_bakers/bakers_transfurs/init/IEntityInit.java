package net.brown_bakers.bakers_transfurs.init;

import com.mojang.datafixers.util.Pair;

import net.brown_bakers.bakers_transfurs.BakersTransfurs;
import net.minecraft.world.entity.EntityType;
import net.ltxprogrammer.changed.entity.ChangedEntity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.Nullable;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.brown_bakers.bakers_transfurs.init.InitEntities.*;
import static net.brown_bakers.bakers_transfurs.init.InitItems.ITEM_REGISTRY;
import static net.brown_bakers.bakers_transfurs.init.InitItems.SPAWN_EGGS;

public interface IEntityInit {
	
	/**
	 *Methods:
	 *
	 *
	 */
	
	static Supplier <AttributeSupplier.Builder> getAttributes() {
		throw new NotImplementedException("Bad usage");
	}
	
	static EntityType.Builder<? extends ChangedEntity> getInitBuilder() {
		throw new NotImplementedException("Bad Usage");
	}
	


}
