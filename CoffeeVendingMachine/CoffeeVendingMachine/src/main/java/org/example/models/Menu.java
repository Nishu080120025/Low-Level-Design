package org.example.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Menu {
    private Map<String, Coffee> menuMap;

    public Menu() {
        this.menuMap = new HashMap<>();
    }

    public void addCoffeeToMenu(Coffee coffee) {
        menuMap.put(coffee.getName(), coffee);
    }

    public void removeCoffeeFromMenu(String coffeeName) {
        menuMap.remove(coffeeName);
    }

    public String showMenu(){
        StringBuilder menuString = new StringBuilder("Menu:\n");
        for (Coffee coffee : menuMap.values()) {
            menuString.append(coffee.getName())
                    .append(" - $")
                    .append(coffee.getPrice())
                    .append("\n");
        }
        return menuString.toString();
    }

}
