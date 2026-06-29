Requirements
The coffee vending machine should support different types of coffee, such as espresso, cappuccino, and latte.
Each type of coffee should have a specific price and recipe (ingredients and their quantities).
The machine should have a menu to display the available coffee options and their prices.
Users should be able to select a coffee type and make a payment.
The machine should dispense the selected coffee and provide change if necessary.
The machine should track the inventory of ingredients and notify when they are running low.



Classes, Interfaces and Enumerations
The Coffee class represents a coffee type with its name, price, and recipe (ingredients and their quantities).
The Ingredient class represents an ingredient used in making coffee, with its name and quantity. It provides a synchronized method to update the quantity.
The Payment class represents a payment made by a user, with the amount paid.
The CoffeeMachine class is the main class that manages the coffee vending machine. It follows the Singleton pattern to ensure a single instance of the machine.
The CoffeeMachine class initializes the coffee menu and ingredients in its constructor. It provides methods to display the menu, select a coffee, dispense coffee, and update ingredient quantities.
The hasEnoughIngredients method checks if there are sufficient ingredients to make a selected coffee, while the updateIngredients method updates the ingredient quantities after dispensing a coffee.