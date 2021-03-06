package Loghme.entities;

public class Food {
    private int id;
    private String name;
    private String description;
    private float popularity;
    private int price;
    private String image;
    private String restaurantName;
    private String restaurantId;
    private boolean available;
    private boolean party;


    public Food() {
    }

    public Food(int id, String name, String description, float popularity, int price, String image, String restaurantId, String restaurantName, boolean _party) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.price = price;
        this.image = image;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.available = true;
        this.party = _party;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void decrement() {
        System.out.println("failed dec called ;((");
    }

    public void increment() {
        System.out.println("failed dec called ;((");
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {return true;}

    public void setCount(int count) {}

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public boolean isParty() {
        return this.party;
    }

    public int getCount() {
        return 0;
    }

    public void setParty(boolean val) {
        party = val;
    }

}

