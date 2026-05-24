package org.foodOrderingSystem;

import java.util.*;



/**
 * Problem Statement: Design a restaurant food ordering system like Zomato, Swiggy, DoorDash
 * <p>
 * Write code for low level design of a restaurant food ordering and rating system,
 * similar to food delivery apps like Zomato, Swiggy, Door Dash, Uber Eats etc.
 * <p>
 * There will be food items like 'Veg Burger', 'Veg Spring Roll', 'Ice Cream' etc.
 * And there will be restaurants from where you can order these food items.
 * <p>
 * Same food item can be ordered from multiple restaurants. e.g. you can order
 * 'food-1' 'veg burger' from burger king as well as from McDonald's.
 * <p>
 * Users can order food, rate orders, fetch restaurants with most rating and fetch
 * restaurants with most rating for a particular food item e.g. restaurants which
 * have the most rating for 'veg burger'.
 * <p>
 * Your solution should implement below methods:
 * <p>
 * Method : init(Helper05 helper)
 * - Use this method to initialize your instance variables
 * - use helper's methods for printing logs else logs will not be visible.
 * <p>
 * Method : orderFood(String orderId, String restaurantId, String foodItemId)
 * - Orders food item from a restaurant.
 * - for now lets assume for that only a single food item is purchased in one order.
 * - orderId, restaurantId, foodItemId will all be valid and available.
 * - PARAMETER : restaurantId is restaurant from where food is being ordered.
 * - PARAMETER : foodItemId is food item which is being ordered
 * <p>
 * Method : rateOrder(String orderId, Stringrating)
 * - Customers can rate their order.
 * - when you are giving rating an order e.g giving 4 stars to an order, then it means
 * you are assigning 4 stars to both the food item in that restaurant as well as 4
 * stars to the overall restaurant rating.
 * - PARAMETER : orderId is order which will be rated by customer, orderId will always
 * be valid i.e. order will always be created for an orderId before rateOrder() is called.
 * - PARAMETER : rating ranges from 1 to 5 stars in increasing order, 1 being the worst
 * and 5 being the best rating.
 * <p>
 * Method : List<String> gettop20RestaurantsByFood(String foodItemId)
 * - Fetches a list of top 20 restaurants based on strategy
 * - unrated restaurants will be at the bottom of list.
 * - restaurants will be sorted on the basis of strategy
 * - restaurants are sorted in descending order on average ratings of the food item and
 * then based on restaurant id lexicographically.
 * - e.g. veg burger is rated 4.3 in restaurant-4 and 4.6 in restaurant-6 then
 * we will return ['restaurant-6', 'restaurant-4']
 * - PARAMETER : foodItemId is food item for which restaurants need to be fetched.
 * <p>
 * Method : List<String> getTopRatedRestaurants()
 * - returns top 20 most rated restaurants ids sorted in descending order of their ratings.
 * - if two restaurants have the same rating then they will be ordered lexicographically
 * by their restaurantId.
 * - Here we are talking about restaurant's overall rating and NOT food item's rating.
 * - e.g. restaurant-2 is rated 4.6 while restaurant-3 is rated 4.2 and restaurant-5 is
 * rated 4.4 and restaurant-6 is rated 4.6, we will return ['restaurant-2','restaurant-6',
 * 'restaurant-5', 'restaurant-3']
 * - even though restaurant-2 and restaurant-6 have same rating , restaurant-6 came later
 * because it is lexicographically greater than restaurant-2
 * <p>
 * Note :
 * - There will be at max 50 food items, at max 10,000 restaurants,
 * and each restaurant can sell at max 25 food items
 * - Average ratings are rounded down to 1 decimal point,
 * i.e. 4.05, 4.08, 4.11, 4.12, 4.14 all become 4.1
 * and 4.15, 4.19, 4.22, 4.24 all become 4.2
 * - For Java, use the formula (double)((int)((rating+0.05)*10))/10.0 to round rating
 */


//////////////**////////////////////////////////////////////////
//**-------FOOD class-----------*///

class Food {
    String foodId;
    String foodName;
    double price;
    double rating;
    int review;

    public Food(String foodId, String foodName, double price, double rating, int review) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.rating = rating;
        this.review = review;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getAverageRating() {
        if (review == 0) return 0.0;
        double avgRating = this.rating / this.review;
        return (double) ((int) ((avgRating + 0.05) * 10)) / 10.0;
    }

    public void addRating(double rating) {
        this.rating += rating;
        this.review++;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(int review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        Food food=(Food)o;
        return this.foodId.equals(food.foodId);
    }

    @Override
    public int hashCode() {
        return foodId.hashCode();
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }
}

//**-------Order  class-----------*///

class Order {
    private String orderId;
    private Food food;

    private String restaurantId;


    public Order(String orderId, Food food, String restaurantId) {
        this.orderId = orderId;
        this.food = food;
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", food=" + food +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

//**-------Restaurant class-----------*///

class Restaurant {
    private String restaurantId;
    private double rating;
    private int reviews;
    private String name;
    private HashMap<String, Food> menu;

    public Restaurant(String restaurantId, double rating, int reviews, String name, HashMap<String, Food> menu) {
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.reviews = reviews;
        this.name = name;
        this.menu = menu;
    }

    public double getAverageRating() {
        if (this.reviews == 0) return 0.0;
        double averageRating = this.rating / this.reviews;
        return (double) ((int) ((rating + 0.05) * 10)) / 10.0;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", name='" + name +
                '}';
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(double rating) {
        this.rating += rating;
        this.reviews+=1;
    }

    public double getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Food> getMenu() {
        return menu;
    }

    public void printMenu() {
        for (String key : this.menu.keySet()) {
            System.out.println("Food id " + key + " " + this.menu.get(key));
        }
    }

}

//**-------User class-----------*///

class User {
    private String id;
    private String name;
    private String address;
    private String phoneNo;

    public User(String id, String name, String address, String phoneNo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}


//**-------respository-----------*///

class RestaurantRepository {

    HashMap<String, Restaurant> restaurantMap;

    public RestaurantRepository() {
        this.restaurantMap = new HashMap<>();
    }

    public Restaurant addRestaurant(String id, Restaurant restaurant) {
        if (restaurantMap.containsKey(id)) {
            restaurantMap.remove(id);
        }
        restaurantMap.put(id, restaurant);
        //System.out.println("Restaurant with "+id+" added");
        return restaurant;
    }

    public Restaurant getRestaurant(String id) {
        if (!restaurantMap.containsKey(id)) {
            return null;
        }
        return restaurantMap.get(id);
    }

    public Food addFoodInMenu(String id, Food food, String foodId) {
        Restaurant restaurant = restaurantMap.get(id);
        HashMap<String, Food> foodMap = restaurant.getMenu();
        if (foodMap.containsKey(foodId)) {
            foodMap.remove(foodId);
        }
        foodMap.put(foodId, food);
        return food;
    }

    public List<String> getTop20Restaurants() {
        List<Restaurant> topRestaurant = new ArrayList<>();
        for (String key : restaurantMap.keySet()) {
            topRestaurant.add(restaurantMap.get(key));

        }
        Collections.sort(topRestaurant, (a, b) -> {
            double aRating = a.getAverageRating();
            double bRating = b.getAverageRating();
            if (Double.compare(bRating, aRating) == 0) {
                return a.getRestaurantId().compareTo(b.getRestaurantId());
            }
            return Double.compare(bRating, aRating);
        });
        if(topRestaurant.size()>=20){
            topRestaurant= topRestaurant.subList(0,20);
        }
        List<String>topRestaurantNames=new ArrayList<>();
        for(Restaurant r:topRestaurant){
            topRestaurantNames.add(r.getName());
        }
        return topRestaurantNames;
    }

    public List<String> getTop20RestaurantByFoodRating(String foodId) {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (String key : restaurantMap.keySet()) {
            Restaurant restaurant = restaurantMap.get(key);
            HashMap<String, Food> menu = restaurant.getMenu();
            if(menu.containsKey(foodId)){
                restaurantList.add(restaurant);
            }
        }
        Collections.sort(restaurantList, (a, b) -> {
            double afoodRating = a.getMenu().get(foodId).getAverageRating();
            double bfoodRating = b.getMenu().get(foodId).getAverageRating();
            if (Double.compare(bfoodRating, afoodRating) == 0) return a.getRestaurantId().compareTo(b.getRestaurantId());
            return Double.compare(bfoodRating, afoodRating);
        });
        if(restaurantList.size()>=20){
            restaurantList=restaurantList.subList(0,20);
        }
       List<String>restaurantNameList=new ArrayList<>();
        for(Restaurant r:restaurantList){
            restaurantNameList.add(r.getName());
        }
        return restaurantNameList;
    }

}

class OrderRepository {

    HashMap<String, Order> orderMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
    }

    public Order addOrder(Order order, String orderId) {
        if (orderMap.containsKey(orderId)) {
            orderMap.remove(orderId);
        }
        orderMap.put(orderId, order);
        System.out.println("Order Added succesfully");
        return order;
    }

    public Order getOrderByOrderId(String orderId) {
        if (!orderMap.containsKey(orderId)) {
            return null;
        }
        return orderMap.get(orderId);
    }


}

class UserRepository {
    HashMap<String, User> userMap = new HashMap<>();

    public User addUser(String userId, User user) {
        if (userMap.containsKey(userId)) {
            userMap.remove(userId);
        }
        userMap.put(userId, user);
        return user;
    }

    public User getUser(String userId) {
        if (!userMap.containsKey(userId)) {
            return null;

        }
        return userMap.get(userId);
    }
}

//**------- Service -----------*///

interface UserService {
    User registerUser(String userId, String name, String phoneNo, String address);

    User getUser(String userId);
}

class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String id, String name, String phoneNo, String address) {
        User user = new User(id, name, phoneNo, address);
        return userRepository.addUser(id, user);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.getUser(userId);
        if (user == null) return null;
        return user;
    }
}

interface RestaurantService {
    Food addFoodInMenu(String restaurantId, String foodId, Food food);

    Restaurant addRestaurant(String restaurantId, Restaurant restaurant);

    List<String> getTop20RestaurantByFoodRating(String food);

    List<String> getTop20RestaurantByRating();

    Restaurant getRestaurant(String restaurantId);
}

class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private OrderRepository orderRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Food addFoodInMenu(String restaurantId, String foodId, Food food) {
        return restaurantRepository.addFoodInMenu(restaurantId, food, foodId);
    }

    @Override
    public Restaurant addRestaurant(String restaurantId, Restaurant restaurant) {
        return restaurantRepository.addRestaurant(restaurantId, restaurant);
    }

    @Override
    public List<String> getTop20RestaurantByFoodRating(String foodId) {
        return restaurantRepository.getTop20RestaurantByFoodRating(foodId);
    }

    @Override
    public List<String> getTop20RestaurantByRating() {
        return restaurantRepository.getTop20Restaurants();
    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantId);
        if (restaurant == null) {
            return null;
        }
        return restaurant;
    }

}

interface OrderService {
    Order orderFood(String foodId, String restaurantId,  String orderId);

    void rateFoodOrder(String orderId, double Rating);

}

class OrderServiceImpl implements OrderService {
    private UserService userService;
    private RestaurantService restaurantService;
    private OrderRepository orderRepository;

    public OrderServiceImpl(UserService userService, RestaurantService restaurantService, OrderRepository orderRepository) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order orderFood(String foodId, String restaurantId,  String orderId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        HashMap<String, Food> menu = restaurant.getMenu();
       if(restaurant==null){
           System.out.println("Restaurant not found");
           return null;
       }
        Order order=new Order(orderId,null,restaurantId);
       if(restaurant.getMenu().containsKey(foodId)){
           Food food=restaurant.getMenu().get(foodId);
           order.setFood(food);
       }
       else{
           System.out.println("food does not exist in the menu of the restaurant");
           return null;
       }
        return orderRepository.addOrder(order, orderId);
    }
    @Override
   public void rateFoodOrder(String orderId, double rating){
        Order order=orderRepository.getOrderByOrderId(orderId);
        if(order!=null){
            Restaurant restaurant=restaurantService.getRestaurant(order.getRestaurantId());
            Food food=order.getFood();
            restaurant.addRating(rating);
            food.addRating(rating);
            System.out.println("Food rating is : "+food.getAverageRating());
            System.out.println("Restaurant rating is :"+restaurant.getAverageRating());
        }
        else{
            return ;
        }
    }
}

class FoodOrderingSystem{
    private Helper helper;
    private RestaurantService restaurantService;
    private OrderService orderService;
    private UserService userService;
    public FoodOrderingSystem( ) {
        this.helper=new Helper();
        RestaurantRepository restaurantRepository=new RestaurantRepository();
        OrderRepository orderRepository=new OrderRepository();
        this.restaurantService=new RestaurantServiceImpl(restaurantRepository,orderRepository);
        this.orderService=new OrderServiceImpl(userService,restaurantService,orderRepository);
    }
    public void orderFood(String orderId,String foodId,String restaurantId){
        orderService.orderFood(foodId,restaurantId,orderId);

    }
    public void rateOrder(String orderId,double rating){
        orderService.rateFoodOrder(orderId,rating);

    }
    public List<String>getTopRestaurantByFood(String foodId){
        return restaurantService.getTop20RestaurantByFoodRating(foodId);
    }

    public List<String>getTopRestaurants(){
        return restaurantService.getTop20RestaurantByRating();
    }

    public void seedDataForTesting(){
        Food burger1=new Food("3245","Burger",90.02,0.0,0);
        Food burger2=new Food("4325","VegBurger",100.23,0,0);

        Restaurant restaurant1=new Restaurant("343",0.0,0,"ABC restaurant",new HashMap<>());
        Restaurant restaurant2=new Restaurant("648",0.0,0,"MOTI mahal",new HashMap<>());

        restaurantService.addRestaurant("343",restaurant1);
        restaurantService.addRestaurant("648",restaurant2);

        restaurantService.addFoodInMenu("343","4325",burger2);
        restaurantService.addFoodInMenu("648","3245",burger1);

        helper.print("Mock data seeded");

    }
}
class Helper{
    public void print(String message){
        System.out.println(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Helper helper=new Helper();

        FoodOrderingSystem foodOrderingSystem=new FoodOrderingSystem();

        helper.print("System Initialised");
        helper.print("------------------");
        foodOrderingSystem.seedDataForTesting();
        try{
            foodOrderingSystem.orderFood("123","4325","343");
            foodOrderingSystem.orderFood("235","3245","648");

            foodOrderingSystem.rateOrder("123",4.8);
            foodOrderingSystem.rateOrder("235",5.0);

            helper.print("Get top  restaurant by food Id");
            List<String>restaurantsForFood2=foodOrderingSystem.getTopRestaurantByFood("3245");
            helper.print("The list of restaurant "+ restaurantsForFood2);

            helper.print("---------------------");
            helper.print("Get top restaurant by food using next food id");

            List<String>restaurantsForFood1=foodOrderingSystem.getTopRestaurantByFood("4325");
            helper.print("The list of restaurant"+ restaurantsForFood1);
            helper.print(" Get top restaurant by rating");

            List<String>topRestaurantList=foodOrderingSystem.getTopRestaurants();
            helper.print("The list of top restaurant are "+ topRestaurantList);

        }
        catch (Exception e){
            helper.print("System initialising failed");
            e.printStackTrace();
        }



    }
}
