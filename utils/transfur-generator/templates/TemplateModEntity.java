package net.brown_bakers.bakers_transfurs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

public class /*PERL_ENTITY_CLASS_NAME*/ extends /*PERL_EXTEND*/ /*PERL_IMPLEMENTS*/ {
    public /*PERL_ENTITY_CLASS_NAME*/(EntityType<? extends /*PERL_EXTEND*/> type, Level level) {
        super(type, level);
    }

    @Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        /*PERL_ATTRIBUTE_OVERRIDE*/
    }

    @Override
    public LatexType getLatexType() {
        return LatexType./*PERL_LATEX_TYPE*/;
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode./*PERL_TRANSFUR_MODE*/;
    }
}