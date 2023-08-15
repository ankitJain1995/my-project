package org.foodie.utility;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.foodie.pojo.ProductPojo;

/**
 *
 * @author AFROZ
 */
public interface FoodApi {

    public ArrayList<ProductPojo> getFoodItemList(String searchKey);
    public ImageIcon getFoodItemById(int foodId);
    
}
