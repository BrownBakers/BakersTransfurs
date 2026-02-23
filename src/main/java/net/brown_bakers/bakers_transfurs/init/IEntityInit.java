package net.brown_bakers.bakers_transfurs.init;

import net.minecraft.world.entity.EntityType;
import net.ltxprogrammer.changed.init.ChangedTransfurVariants;
import net.ltxprogrammer.changed.entity.ChangedEntity;

public interface IEntityInit {

	public static EntityType.Builder<? extends ChangedEntity> initEntity();
	//TODO
	//public static ChangedTransfurVariants.Builder<? extends ChangedEntity> initVariant();
	

}
