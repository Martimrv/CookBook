package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String shortDescription;
    private List<Ingredient> ingredients;
    private String detailedDescription;
    private List<tag> tags;
    private List<Comment> comments;
    private List<user> users;
    private int servings;

    public Recipe(String name, String shortDescription, List<Ingredient> ingredients, String detailedDescription,
            int servings) {
        user.getAllRecipes().add(this);
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.ingredients = ingredients;
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.users = new ArrayList<>();
        this.servings = servings;
    }

    public Recipe(String name, String shortDescription, String detailedDescription) {
        this(name, shortDescription, new ArrayList<>(), detailedDescription, 4); // Assuming default servings as 4
    }

    public String getName() {
        return this.name;
    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDetailedDescription() {
        return this.detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public List<tag> getTags() {
        return this.tags;
    }

    public void setTags(List<tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<user> getUsers() {
        return this.users;
    }

    public void setUsers(List<user> users) {
        this.users = users;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addTag(tag tag) {
        tags.add(tag);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addUser(user user) {
        users.add(user);
    }

    public static List<Recipe> searchByName(String name, List<Recipe> recipes) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }

    public static List<Recipe> getAllRecipes(List<Recipe> recipes) {
        return recipes;
    }

    public static List<Recipe> searchByIngredient(String ingredient, List<Recipe> recipes) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (Ingredient i : recipe.getIngredients()) {
                if (i.getName().equalsIgnoreCase(ingredient)) {
                    result.add(recipe);
                    break;
                }
            }
        }
        return result;
    }

    public List<Ingredient> adjustIngredientsForServings(int desiredServings) {
        List<Ingredient> adjustedIngredients = new ArrayList<>();
        double ratio = (double) desiredServings / this.servings;

        for (Ingredient ingredient : ingredients) {
            double adjustedQuantity = ingredient.getQuantity() * ratio;
            Ingredient adjustedIngredient = new Ingredient(ingredient.getName(), adjustedQuantity,
                    ingredient.getUnit());
            adjustedIngredients.add(adjustedIngredient);
        }

        return adjustedIngredients;
    }

    public static List<Recipe> searchByTag(String tagName, List<Recipe> recipes) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (tag t : recipe.getTags()) {
                if (t.getName().equalsIgnoreCase(tagName)) {
                    result.add(recipe);
                    break;
                }
            }
        }
        return result;
    }

    public static List<Recipe> filterRecipesByMultipleTags(List<tag> tags, List<Recipe> recipes) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean allTagsMatch = true;
            for (tag t : tags) {
                if (!recipe.getTags().contains(t)) {
                    allTagsMatch = false;
                    break;
                }
            }
            if (allTagsMatch) {
                result.add(recipe);
            }
        }
        return result;
    }

    public Object getId() {
        return null;
    }

}
