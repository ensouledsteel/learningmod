package net.ensouledsteel.learning_mod;

import net.ensouledsteel.learning_mod.item.AdvancedItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearningMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(Constants.NAMESPACE);

	public static final Block LEARNING_BLOCK = new PillarBlock(QuiltBlockSettings.create().strength(4.0f));
	/*
	public static final Block STORAGE_BLOCK = new StorageBlock();

	public static final BlockEntityType<StorageBlockEntity> STORAGE_BLOCK_ENTITY =
		Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier("learningmod", "storage_block_entity"),
			FabricBlockEntityTypeBuilder.create(StorageBlockEntity::new, STORAGE_BLOCK).build()
		);
	 */

	public static final Item LEARNING_ITEM = new Item(
		new QuiltItemSettings()
			.food(new FoodComponent.Builder().hunger(2).saturationModifier(6f).build())
	);
	public static final Item ADVANCED_ITEM = new AdvancedItem(new QuiltItemSettings().maxCount(16));
	public static final Item LEARNING_BLOCK_ITEM = new BlockItem(LEARNING_BLOCK, new QuiltItemSettings());

	public static final ItemGroup LEARNING_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(LEARNING_ITEM))
		.name(Text.translatable("itemgroup.learningmod.learning_group"))
		.entries((context, entries) -> {
			entries.addItem(LEARNING_ITEM);
			entries.addItem(ADVANCED_ITEM);
			entries.addItem(LEARNING_BLOCK_ITEM);
		})
		.build();

	private void registerItem(String itemName, Item item){
		Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, itemName), item);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Registry.register(
			Registries.ITEM_GROUP,
			new Identifier(Constants.NAMESPACE, "learning_group"),
			LEARNING_GROUP
		);

		Registry.register(
			Registries.BLOCK, new Identifier(Constants.NAMESPACE, "learning_block"), LEARNING_BLOCK
		);

		registerItem("learning_item", LEARNING_ITEM);
		registerItem("advanced_item", ADVANCED_ITEM);
		registerItem("learning_block", LEARNING_BLOCK_ITEM);
	}
}
