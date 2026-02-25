package net.brown_bakers.bakers_transfurs.entity;

import net.brown_bakers.bakers_transfurs.init.IEntityInit;
import net.brown_bakers.bakers_transfurs.init.InitUtils;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.ltxprogrammer.changed.init.ChangedAttributes;
import net.ltxprogrammer.changed.init.ChangedEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import net.minecraftforge.registries.RegistryObject;

public class LatexFox extends ChangedEntity implements IEntityInit
{
	
	/***********************************************************
	 * Methods
	 * static Builder<LatexFox> getInitBuilder() - returns builder for the entity.
	 *
	 * (constructor)
	 * TransfurMode getTransfurMode() - returns transfur mode
	 * void setAttributes( AttributeMap attributes )
	 *
	 *
	 * Fields:
	 * static final String name - holds entity ID.
	 * static final int color1 - holds entity's 1st color
	 * static final int color2 - holds entity's 2nd color
	 *
	 ***********************************************************/
	
	public LatexFox(EntityType<? extends ChangedEntity> type, Level level) {
		super(type, level);
	}
	
	@Override
	public TransfurMode getTransfurMode() {
		return this.tf_mode;
	}
	
	//TODO Move this to an interface, and call it when needed with correct arguments.
	public static Builder<LatexFox> getInitBuilder() {
		
		return EntityType.Builder
			.of(LatexFox::new, MobCategory.MONSTER)
			.clientTrackingRange(10)
			.sized(0.7F, 1.93F);
	}

	public static final String name = "latex_fox";
	public static final int color1 = 0x624f13;
	public static final int color2 = 0xb4a165;
	
	public static final RegistryObject<EntityType<LatexFox>> LATEX_FOX = InitUtils.getInitRObject(
		   name,
		   color1,
		   color2,
		   getInitBuilder(),
		   ChangedEntities::overworldOnly,
		   SpawnPlacements.Type.ON_GROUND,
		   LatexFox::checkEntitySpawnRules,
		   ChangedEntity::createLatexAttributes
	);
	
	public TransfurMode tf_mode = TransfurMode.REPLICATION;
	
	@Override
	protected void setAttributes (AttributeMap attributes) {
		super.setAttributes(attributes);
		attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.05);
		attributes.getInstance(ChangedAttributes.SNEAK_SPEED.get()).setBaseValue(1.5D);
		attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
		attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get()).setBaseValue(1.25);
	}
}