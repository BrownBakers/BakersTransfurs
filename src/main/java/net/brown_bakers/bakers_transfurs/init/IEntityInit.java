package net.brown_bakers.bakers_transfurs.init;

import net.minecraft.world.entity.EntityType;
import net.ltxprogrammer.changed.entity.ChangedEntity;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

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
