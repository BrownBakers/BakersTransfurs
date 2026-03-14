package net.kjentytek303.additional_transfurs;

import net.kjentytek303.additional_transfurs.init.InitEntities;
import net.kjentytek303.additional_transfurs.init.InitItems;
import net.kjentytek303.additional_transfurs.init.InitTransfurs;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AdditionalTransfurs.MODID)
public class AdditionalTransfurs
{
    public static final String MODID = "additional_transfurs";
    private static final Logger LOGGER = LogUtils.getLogger();


    public AdditionalTransfurs(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        InitEntities.ENTITY_REGISTRY.register(modEventBus);
        InitItems.ITEM_REGISTRY.register(modEventBus);
        InitTransfurs.TF_REGISTRY.register(modEventBus);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    
    }
    
    
    public static ResourceLocation modResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}