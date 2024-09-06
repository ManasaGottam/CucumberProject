package org.example.cucumber;

import java.util.ArrayList;

public class RestaurantMenu {
    ArrayList<RestaurantMenuItem> menuItems = new ArrayList<>();

    public boolean addMenuItem(RestaurantMenuItem menuItemToAdd) {
        if (isItemPresentInMenu(menuItemToAdd.getItemName())) {
            throw new IllegalArgumentException("Duplicate Item: " + menuItemToAdd.getItemName());
        }
        return menuItems.add(menuItemToAdd);
    }

    public boolean isItemPresentInMenu(String menuItemName) {
        for (RestaurantMenuItem item : menuItems) {
            if (item.getItemName().equalsIgnoreCase(menuItemName)) {
                return true;
            }
        }
        return false;
    }
}
