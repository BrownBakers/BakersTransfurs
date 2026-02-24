package net.brown_bakers.bakers_transfurs.entity;

import net.brown_bakers.bakers_transfurs.init.IEntityInit;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class LatexFox extends ChangedEntity implements IEntityInit
{
	
	/***********************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * static RegistryObject<EntityType<?>> getInitRObject() -
	 *
	 **/
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level) {
		super(type, level);
	}
	
	@Override
	public TransfurMode getTransfurMode()
	{
		return TransfurMode.REPLICATION;
	}
	
	//TODO Move this to an interface, and call it when needed with correct arguments.
	public static Builder<? extends ChangedEntity> getInitBuilder() {
		
		return EntityType.Builder
			.of(LatexFox::new, MobCategory.MONSTER)
			.clientTrackingRange(10)
			.sized(0.7F, 1.93F);
	}
	
	
	/***********************************************************
	 * Fields
	 ***********************************************************/
	public static final RegistryObject<EntityType<LatexFox>> LATEX_FOX = getInitRObject();
	public static final String name = "latex_fox";
}