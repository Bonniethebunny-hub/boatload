package com.teamabnormals.boatload.core.other;

import com.google.common.collect.Lists;
import com.teamabnormals.blueprint.common.entity.BlueprintBoat;
import com.teamabnormals.blueprint.common.entity.BlueprintChestBoat;
import com.teamabnormals.blueprint.core.registry.BoatTypeRegistry;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.stream.Collectors;

public class BoatloadUtil {

	public static List<Item> getItems() {
		List<Item> items = Lists.newArrayList();
		Boatload.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(registryObject -> items.add(registryObject.get()));
		return items;
	}

	public static List<FurnaceBoatItem> getFurnaceBoats() {
		return (List<FurnaceBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof FurnaceBoatItem).collect(Collectors.toList());
	}

	public static List<LargeBoatItem> getLargeBoats() {
		return (List<LargeBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof LargeBoatItem).collect(Collectors.toList());
	}

	public static boolean isNetherBoat(Entity entity) {
		String type = null;
		if (entity instanceof BlueprintBoat boat) {
			type = BoatTypeRegistry.getNameForData(boat.getBoatTypeData());
		} else if (entity instanceof BlueprintChestBoat boat) {
			type = BoatTypeRegistry.getNameForData(boat.getBoatTypeData());
		} else if (entity instanceof BoatloadBoat boat) {
			type = boat.getBoatloadBoatType().registryName().toString();
		}

		return type.equals(Boatload.MOD_ID + ":crimson") || type.equals(Boatload.MOD_ID + ":warped");
	}
}
