package net.brown_bakers.bakers_transfurs.entity;

import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class LatexFox extends ChangedEntity implements net.brown_bakers.bakers_transfurs.init.IEntityInit {
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level)
	{
		super(type, level);
	}
	
	@Override
	public TransfurMode getTransfurMode()
	{
		return TransfurMode.REPLICATION;
	}
	
	public static EntityType.Builder<? extends ChangedEntity> initEntity() {
	
	}
}