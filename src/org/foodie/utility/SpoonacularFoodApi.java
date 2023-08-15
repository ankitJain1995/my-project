package org.foodie.utility;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.foodie.pojo.ProductPojo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author AFROZ
 */
public class SpoonacularFoodApi implements FoodApi {

    private ArrayList<ProductPojo> productList = new ArrayList<>();
    private ProductPojo product;

    @Override
    public ArrayList<ProductPojo> getFoodItemList(String searchKey) {
        //String apiKey = "e7a4cd2c04ae406b8e60b76614aee48d";
        String apiKey = "9763f81b0cb4487285d9cd5624c14bdd";
        String apiUrl = " https://api.spoonacular.com/food/search?query=" + searchKey + "&number=2&apiKey=" + apiKey;

        try {
            HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                    .header("accept", "application/json")
                    .asJson();

            JSONObject jsonResponse = response.getBody().getObject();
            JSONArray searchResultsArray = jsonResponse.getJSONArray("searchResults");

            for (int i = 0; i < searchResultsArray.length(); i++) {
                JSONObject searchResult = searchResultsArray.getJSONObject(i);
                JSONArray resultsArray = searchResult.getJSONArray("results");

                // Do something with the resultsArray, such as display the name and image for each result
                for (int j = 0; j < resultsArray.length(); j++) {
                    JSONObject result = resultsArray.getJSONObject(j);
//                    System.out.println("has key name : "+result.has("name"));
//                    System.out.println("has key image : "+result.has("image"));
                    if (!result.has("name") || !result.has("image")) {
                        continue;
                    }
                    String foodName = result.getString("name");
                    String imageUrl = result.getString("image");
                    String imageType = imageUrl.substring(imageUrl.lastIndexOf(".")+1, imageUrl.length());


                    if (foodName.isEmpty() || imageUrl.isEmpty()) {
                        continue;
                    }

                    HttpURLConnection url = (HttpURLConnection) new URL(imageUrl).openConnection();
                    url.addRequestProperty("user-agent", "mozilla");

                    if (url.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        continue;
                    }

                    Image image = ImageIO.read(url.getInputStream());
                    System.out.println("i can print befroe expection" + image);
                    
                    System.out.println(foodName + " - " + imageUrl);
                    System.out.println("image type is "+imageType);
                    // add HttpURLConnection url and product name in the hashmap of arraylist
                    
                    product = new ProductPojo();
                    product.setProductName(foodName);
                    product.setProductImage(image);
                    product.setProductImageType(imageType);
                    
                    productList.add(product);
                }
            }
            

        } catch (UnirestException | IOException ex) {
            ex.printStackTrace();
        }

        return productList;

    }

    @Override
    public ImageIcon getFoodItemById(int foodId) {
        String foodIdUrl = "https://api.spoonacular.com/food/menuItems/" + foodId + "?apiKey=9763f81b0cb4487285d9cd5624c14bdd";
        HttpResponse<JsonNode> response;
        ImageIcon imageIcon = null;
        try {
            response = Unirest.get(foodIdUrl)
                    .header("accept", "application/json")
                    .asJson();

            JSONObject jsonResponse = response.getBody().getObject();
            System.out.println("JSON Response : "+jsonResponse);
//        int foodId = jsonResponse.getInt("id");
            String foodName = jsonResponse.getString("title");
            String imageUrl = jsonResponse.getString("image");

            System.out.println("Food ID: " + foodId);
            System.out.println("Food Name: " + foodName);
            System.out.println("Image URL: " + imageUrl);

            HttpURLConnection url = (HttpURLConnection) new URL(imageUrl).openConnection();
            url.addRequestProperty("user-agent", "mozilla");

            Image image = ImageIO.read(url.getInputStream());
            System.out.println("i can print befroe expection" + image);
            imageIcon = new ImageIcon(image);
        } catch (UnirestException ex) {
            Logger.getLogger(SpoonacularFoodApi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SpoonacularFoodApi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SpoonacularFoodApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageIcon;
    }

}

/* 
 to get the data from the food api using id the query will be

https://api.spoonacular.com/food/menuItems/419357?apiKey=e7a4cd2c04ae406b8e60b76614aee48d


================================================================================

public ArrayList<ProductItemPojo> getFoodItemList(String searchKey) {
        String apiKey = "e7a4cd2c04ae406b8e60b76614aee48d";
        String apiUrl = " https://api.spoonacular.com/food/search?query=" + searchKey + "&number=1&apiKey=" + apiKey;

        try {
            HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                    .header("accept", "application/json")
                    .asJson();

            JSONObject jsonResponse = response.getBody().getObject();
            JSONArray searchResultsArray = jsonResponse.getJSONArray("searchResults");

            for (int i = 0; i < searchResultsArray.length(); i++) {
                JSONObject searchResult = searchResultsArray.getJSONObject(i);
                JSONArray resultsArray = searchResult.getJSONArray("results");

                // Do something with the resultsArray, such as display the name and image for each result
                for (int j = 0; j < resultsArray.length(); j++) {
                    JSONObject result = resultsArray.getJSONObject(j);
                    int foodId = result.optInt("id");
                    String foodName = result.getString("name");
                    String imageUrl = result.getString("image");
                    String imageType = imageUrl.substring(imageUrl.lastIndexOf(".")+1, imageUrl.length());
//                    Display the name and image for this result
//                    System.out.println(foodName + " - " + imageUrl);
//                    System.out.println("image type is "+imageType);
//                    System.out.println("resutl object is "+result);
//                    System.out.println("does resutl have id"+result.has("id"));
//                    System.out.println("Food id is "+result.optInt("id"));
//                    System.out.println("json object is "+result);

                    if (foodName.isEmpty() || imageUrl.isEmpty()) {
                        continue;
                    }

                    HttpURLConnection url = (HttpURLConnection) new URL(imageUrl).openConnection();
                    url.addRequestProperty("user-agent", "mozilla");

                    if (url.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        continue;
                    }

                    Image image = ImageIO.read(url.getInputStream());
                    System.out.println("i can print befroe expection" + image);
                    ImageIcon imageIcon = new ImageIcon(image);
                    
                    System.out.println(foodName + " - " + imageUrl);
                    System.out.println("image type is "+imageType);
                    // add HttpURLConnection url and product name in the hashmap of arraylist
                    foodItem = new ProductItemPojo();
                    foodItem.setFoodId(foodId);
                    foodItem.setFoodName(foodName);
                    foodItem.setImageIcon(imageIcon);
                    foodItemList.add(foodItem);
                }
            }
            

        } catch (UnirestException | IOException ex) {
            ex.printStackTrace();
        }

        return foodItemList;

    }



 */
