package com.example.monresto.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.monresto.entities.Item;
import com.example.monresto.entities.Restaurant;

import java.util.ArrayList;

public class MonRestoDbHelper extends SQLiteOpenHelper {

    public MonRestoDbHelper(@Nullable Context context) {
        super(context, "MonResto.db", null, 1);
        System.out.println("*** MonRestoDbHelper ***");
        System.out.println("*** Database name : " + getDatabaseName() + " ***");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("*** onCreate ***");

        db.execSQL("CREATE TABLE CATEGORIES (" +
                "ID_CATEGORY INTEGER PRIMARY KEY," +
                "NAME TEXT" +
                ");");
        System.out.println("[CATEGORIES] table was created successfully");

        db.execSQL("CREATE TABLE LOCATIONS (" +
                "ID_LOCATION INTEGER PRIMARY KEY," +
                "LATITUDE TEXT," +
                "LONGITUDE TEXT" +
                ");");
        System.out.println("[LOCATIONS] table was created successfully");

        db.execSQL("CREATE TABLE RESTAURANTS (" +
                "ID_RESTAURANT INTEGER PRIMARY KEY," +
                "NAME TEXT," +
                "PHONE TEXT," +
                "CATEGORY_ID INTEGER NOT NULL," +
                "LOCATION_ID INTEGER NOT NULL," +
                "FOREIGN KEY (CATEGORY_ID)" +
                "REFERENCES CATEGORIES(ID_CATEGORY)," +
                "FOREIGN KEY (LOCATION_ID)" +
                "REFERENCES LOCATIONS(ID_LOCATION)" +
                ");");
        System.out.println("[RESTAURANTS] table was created successfully");

        db.execSQL("CREATE TABLE ITEMS (" +
                "ID_ITEM INTEGER PRIMARY KEY," +
                "NAME TEXT," +
                "PRICE TEXT," +
                "RESTAURANT_ID INTEGER NOT NULL," +
                "FOREIGN KEY (RESTAURANT_ID)" +
                "REFERENCES RESTAURANTS(ID_RESTAURANT)" +
                ");");
        System.out.println("[ITEMS] table was created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*** onUpgrade ***");
        db.execSQL("DROP TABLE IF EXISTS CATEGORIES;");
        db.execSQL("DROP TABLE IF EXISTS LOCATIONS;");
        db.execSQL("DROP TABLE IF EXISTS RESTAURANTS;");
        db.execSQL("DROP TABLE IF EXISTS ITEMS;");
        onCreate(db);
    }

    public void insert()
    {
        System.out.println("*** insert ***");

        String categories_insert_query = "INSERT INTO `categories` (`ID_CATEGORY`, `NAME`) VALUES" +
                "(1, 'Pizza')," +
                "(2, 'Fast Food')," +
                "(3, 'Tacos')," +
                "(4, 'Marocain')," +
                "(5, 'Asiatique')," +
                "(6, 'Italien');";

        String locations_insert_query = "INSERT INTO `locations` (`ID_LOCATION`, `LATITUDE`, `LONGITUDE`) VALUES" +
                "(1, '34.27016858535932', '-6.601325320868133')," +
                "(2, '34.261570901646195', '-6.5918746925908165')," +
                "(3, '34.254324120783814', '-6.589061940587806')," +
                "(4, '34.2536556223252', '-6.580656523887046')," +
                "(5, '34.260503231842726', '-6.587378284127675')," +

                "(6, '34.25920259054789', '-6.587811521308972')," +
                "(7, '34.017331801004964', '-6.8303579019230485')," +
                "(8, '34.250401654423285', '-6.5726321811992525')," +
                "(9, '34.261560021852034', '-6.583620448492014')," +
                "(10, '34.25742112941994', '-6.61441348450955')," +

                "(11, '34.25988503294969', '-6.5881210019188')," +
                "(12, '34.25958261537533', '-6.585395484127763')," +
                "(13, '34.25991792479524', '-6.583347221506029')," +
                "(14, '34.26062515628621', '-6.587161025205378')," +
                "(15, '34.26188296373043', '-6.5844052673735325')," +

                "(16, '34.009840296450726', '-6.863060780888361')," +
                "(17, '33.5944855813317', '-7.614612830572416')," +
                "(18, '34.01549342655382', '-6.837305303471454')," +
                "(19, '34.26293513875883', '-6.592435768592499')," +
                "(20, '33.57724851936283', '-7.6074138197098184')," +

                "(21, '34.26101372526142', '-6.5890907398523915')," +
                "(22, '34.25637313433879', '-6.576596484127783')," +
                "(23, '33.95399075075762', '-6.874477182097452')," +
                "(24, '34.001915244968906', '-6.846501415874145')," +
                "(25, '34.02146703834298', '-6.8484273019229605')," +

                "(26, '34.25531594732753', '-6.583556747541067')," +
                "(27, '34.259710888791965', '-6.583252878263394')," +
                "(28, '34.26024820355465', '-6.584991492548347')," +
                "(29, '34.25501904834146', '-6.5818544782594675')," +
                "(30, '34.25586517206026', '-6.593728483643854');";

        String restaurants_insert_query = "INSERT INTO `restaurants` (`ID_RESTAURANT`, `NAME`, `PHONE`, `CATEGORY_ID`, `LOCATION_ID`) VALUES" +
                "(1, 'Sopizza', '0707079996', 1, 1)," +
                "(2, 'Aji Naklo Pizzeria', '0628470050', 1, 2)," +
                "(3, 'Pasta Pizza', '0537373940', 1, 3)," +
                "(4, 'Domino s', '0537394242', 1, 4)," +
                "(5, 'So Tasty', '0777994252', 1, 5)," +

                "(6, 'Chez Sahbi', '0619034236', 2, 6)," +
                "(7, 'Itry FastFood', '0678983114', 2, 7)," +
                "(8, 'McDonald s', '0537395583', 2, 8)," +
                "(9, 'Ricos Chicken', '0688598850', 2, 9)," +
                "(10, 'ICH-KA Food', '0649712553', 2, 10)," +

                "(11, 'Kwik One Tacos', '0537361215', 3, 11)," +
                "(12, 'Anzi Tacos', '0698654096', 3, 12)," +
                "(13, 'Tacos de Lyon', '0537720328', 3, 13)," +
                "(14, 'So Tasty', '0777994252', 3, 14)," +
                "(15, 'Step Burger', '0689079645', 3, 15)," +

                "(16, 'Moroccan Food Tour', '0657832662', 4, 16)," +
                "(17, 'Le Riad', '0676979034', 4, 17)," +
                "(18, 'Tajine Wa Tanjia', '0537729797', 4, 18)," +
                "(19, 'Dar Tajine', '0605268860', 4, 19)," +
                "(20, 'Zayna', '0522542596', 4, 20)," +

                "(21, 'Little Japan', '0537363534', 5, 21)," +
                "(22, 'Kensuthai', '0537327887', 5, 22)," +
                "(23, 'As iam', '0537565630', 5, 23)," +
                "(24, 'Eathai', '0537770301', 5, 24)," +
                "(25, 'Tian An Men', '0662277747', 5, 25)," +

                "(26, 'IL Forno', '0537394922', 6, 26)," +
                "(27, 'Molly s Diner', '0537362209', 6, 27)," +
                "(28, 'Monalisa café & restaurant', '0537379383', 6, 28)," +
                "(29, 'L Authentik', '0537327539', 6, 29)," +
                "(30, 'Mini World', '0537361254', 6, 30);";

        String items_insert_query = "INSERT INTO `items` (`ID_ITEM`, `NAME`, `PRICE`, `RESTAURANT_ID`) VALUES" +
                "(1, 'Pizza Margarita', '20', 1)," +
                "(2, 'Pizza Starsbourg', '20', 1)," +
                "(3, 'Pizza Thon', '20', 1)," +
                "(4, 'Pizza Mixte', '25', 1)," +
                "(5, 'Pizza Fruits de Mer', '30', 1)," +

                "(6, 'Pizza Margarita', '20', 2)," +
                "(7, 'Pizza Starsbourg', '20', 2)," +
                "(8, 'Pizza Thon', '20', 2)," +
                "(9, 'Pizza Mixte', '25', 2)," +
                "(10, 'Pizza Fruits de Mer', '30', 2)," +

                "(11, 'Pizza Margarita', '20', 3)," +
                "(12, 'Pizza Starsbourg', '20', 3)," +
                "(13, 'Pizza Thon', '20', 3)," +
                "(14, 'Pizza Mixte', '25', 3)," +
                "(15, 'Pizza Fruits de Mer', '30', 3)," +

                "(16, 'Pizza Margarita', '20', 4)," +
                "(17, 'Pizza Starsbourg', '20', 4)," +
                "(18, 'Pizza Thon', '20', 4)," +
                "(19, 'Pizza Mixte', '25', 4)," +
                "(20, 'Pizza Fruits de Mer', '30', 4)," +

                "(21, 'Pizza Margarita', '20', 5)," +
                "(22, 'Pizza Starsbourg', '20', 5)," +
                "(23, 'Pizza Thon', '20', 5)," +
                "(24, 'Pizza Mixte', '25', 5)," +
                "(25, 'Pizza Fruits de Mer', '30', 5)," +

                //

                "(26, 'Hamburger', '30', 6)," +
                "(27, 'Cheese Burger', '35', 6)," +
                "(28, 'Double Cheese', '40', 6)," +
                "(29, 'Chiken Burger', '40', 6)," +
                "(30, 'Rosti', '50', 6)," +

                "(31, 'Hamburger', '30', 7)," +
                "(32, 'Cheese Burger', '35', 7)," +
                "(33, 'Double Cheese', '40', 7)," +
                "(34, 'Chiken Burger', '40', 7)," +
                "(35, 'Rosti', '50', 7)," +

                "(36, 'Hamburger', '30', 8)," +
                "(37, 'Cheese Burger', '35', 8)," +
                "(38, 'Double Cheese', '40', 8)," +
                "(39, 'Chiken Burger', '40', 8)," +
                "(40, 'Rosti', '50', 8)," +

                "(41, 'Hamburger', '30', 9)," +
                "(42, 'Cheese Burger', '35', 9)," +
                "(43, 'Double Cheese', '40', 9)," +
                "(44, 'Chiken Burger', '40', 9)," +
                "(45, 'Rosti', '50', 9)," +

                "(46, 'Hamburger', '30', 10)," +
                "(47, 'Cheese Burger', '35', 10)," +
                "(48, 'Double Cheese', '40', 10)," +
                "(49, 'Chiken Burger', '40', 10)," +
                "(50, 'Rosti', '50', 10)," +

                //

                "(51, 'Tacos Hot Dog', '25', 11)," +
                "(52, 'Tacos Mixte', '30', 11)," +
                "(53, 'Tacos Potaitos', '35', 11)," +
                "(54, 'Tacos Steak', '40', 11)," +
                "(55, 'Tacos Royal', '40', 11)," +

                "(56, 'Tacos Hot Dog', '25', 12)," +
                "(57, 'Tacos Mixte', '30', 12)," +
                "(58, 'Tacos Potaitos', '35', 12)," +
                "(59, 'Tacos Steak', '40', 12)," +
                "(60, 'Tacos Royal', '40', 12)," +

                "(61, 'Tacos Hot Dog', '25', 13)," +
                "(62, 'Tacos Mixte', '30', 13)," +
                "(63, 'Tacos Potaitos', '35', 13)," +
                "(64, 'Tacos Steak', '40', 13)," +
                "(65, 'Tacos Royal', '40', 13)," +

                "(66, 'Tacos Hot Dog', '25', 14)," +
                "(67, 'Tacos Mixte', '30', 14)," +
                "(68, 'Tacos Potaitos', '35', 14)," +
                "(69, 'Tacos Steak', '40', 14)," +
                "(70, 'Tacos Royal', '40', 14)," +

                "(71, 'Tacos Hot Dog', '25', 15)," +
                "(72, 'Tacos Mixte', '30', 15)," +
                "(73, 'Tacos Potaitos', '35', 15)," +
                "(74, 'Tacos Steak', '40', 15)," +
                "(75, 'Tacos Royal', '40', 15)," +

                //

                "(76, 'Poulet aux Olives', '40', 16)," +
                "(77, 'Sauce Kebab', '45', 16)," +
                "(78, 'Tagin Agneau', '50', 16)," +
                "(79, 'Tangiya', '55', 16)," +
                "(80, 'Mrozia', '55', 16)," +

                "(81, 'Poulet aux Olives', '40', 17)," +
                "(82, 'Sauce Kebab', '45', 17)," +
                "(83, 'Tagin Agneau', '50', 17)," +
                "(84, 'Tangiya', '55', 17)," +
                "(85, 'Mrozia', '55', 17)," +

                "(86, 'Poulet aux Olives', '40', 18)," +
                "(87, 'Sauce Kebab', '45', 18)," +
                "(88, 'Tagin Agneau', '50', 18)," +
                "(89, 'Tangiya', '55', 18)," +
                "(90, 'Mrozia', '55', 18)," +

                "(91, 'Poulet aux Olives', '40', 19)," +
                "(92, 'Sauce Kebab', '45', 19)," +
                "(93, 'Tagin Agneau', '50', 19)," +
                "(94, 'Tangiya', '55', 19)," +
                "(95, 'Mrozia', '55', 19)," +

                "(96, 'Poulet aux Olives', '40', 20)," +
                "(97, 'Sauce Kebab', '45', 20)," +
                "(98, 'Tagin Agneau', '50', 20)," +
                "(99, 'Tangiya', '55', 20)," +
                "(100, 'Mrozia', '55', 20)," +

                //

                "(101, 'Roll Eby Tempura', '40', 21)," +
                "(102, 'Roll Saumon Cuit', '50', 21)," +
                "(103, 'Boston Roll', '50', 21)," +
                "(104, 'Aromaki Crevette', '60', 21)," +
                "(105, 'Sushi Sucre', '60', 21)," +

                "(106, 'Roll Eby Tempura', '40', 22)," +
                "(107, 'Roll Saumon Cuit', '50', 22)," +
                "(108, 'Boston Roll', '50', 22)," +
                "(109, 'Aromaki Crevette', '60', 22)," +
                "(110, 'Sushi Sucre', '60', 22)," +

                "(111, 'Roll Eby Tempura', '40', 23)," +
                "(112, 'Roll Saumon Cuit', '50', 23)," +
                "(113, 'Boston Roll', '50', 23)," +
                "(114, 'Aromaki Crevette', '60', 23)," +
                "(115, 'Sushi Sucre', '60', 23)," +

                "(116, 'Roll Eby Tempura', '40', 24)," +
                "(117, 'Roll Saumon Cuit', '50', 24)," +
                "(118, 'Boston Roll', '50', 24)," +
                "(119, 'Aromaki Crevette', '60', 24)," +
                "(120, 'Sushi Sucre', '60', 24)," +

                "(121, 'Roll Eby Tempura', '40', 25)," +
                "(122, 'Roll Saumon Cuit', '50', 25)," +
                "(123, 'Boston Roll', '50', 25)," +
                "(124, 'Aromaki Crevette', '60', 25)," +
                "(125, 'Sushi Sucre', '60', 25)," +

                //

                "(126, 'Crispy Surimi', '25', 26)," +
                "(127, 'Sushi Pizza', '35', 26)," +
                "(128, 'Aromaké Pané', '50', 26)," +
                "(129, 'Dragon Eye', '55', 26)," +
                "(130, 'Canchy Saumon', '65', 26)," +

                "(131, 'Crispy Surimi', '25', 27)," +
                "(132, 'Sushi Pizza', '35', 27)," +
                "(133, 'Aromaké Pané', '50', 27)," +
                "(134, 'Dragon Eye', '55', 27)," +
                "(135, 'Canchy Saumon', '65', 27)," +

                "(136, 'Crispy Surimi', '25', 28)," +
                "(137, 'Sushi Pizza', '35', 28)," +
                "(138, 'Aromaké Pané', '50', 28)," +
                "(139, 'Dragon Eye', '55', 28)," +
                "(140, 'Canchy Saumon', '65', 28)," +

                "(141, 'Crispy Surimi', '25', 29)," +
                "(142, 'Sushi Pizza', '35', 29)," +
                "(143, 'Aromaké Pané', '50', 29)," +
                "(144, 'Dragon Eye', '55', 29)," +
                "(145, 'Canchy Saumon', '65', 29)," +

                "(146, 'Crispy Surimi', '25', 30)," +
                "(147, 'Sushi Pizza', '35', 30)," +
                "(148, 'Aromaké Pané', '50', 30)," +
                "(149, 'Dragon Eye', '55', 30)," +
                "(150, 'Canchy Saumon', '65', 30);";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(categories_insert_query);
        db.execSQL(locations_insert_query);
        db.execSQL(restaurants_insert_query);
        db.execSQL(items_insert_query);
    }

    public ArrayList<String> read_categories()
    {
        System.out.println("*** read_categories ***");
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("CATEGORIES",null,null,null,null,null,null);
        ArrayList<String> categories = new ArrayList<>();

        while (cursor.moveToNext())
        {
            String category = cursor.getString(cursor.getColumnIndex("NAME"));
            categories.add(category);
        }

        return categories;
    }

    public ArrayList<ArrayList<String>> read_locations()
    {
        System.out.println("*** read_locations ***");
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("LOCATIONS",null,null,null,null,null,null);
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        while (cursor.moveToNext())
        {
            ArrayList<String> row = new ArrayList<>();
            row.add(cursor.getString(cursor.getColumnIndex("ID_LOCATION")));
            row.add(cursor.getString(cursor.getColumnIndex("LATITUDE")));
            row.add(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
            rows.add(row);
        }

        return rows;
    }

    public ArrayList<Restaurant> read_restaurants(String search, String category)
    {
        System.out.println("*** read_restaurants ***");
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor;

        if (search.isEmpty())
            cursor = db.rawQuery("SELECT ID_RESTAURANT, RESTAURANTS.NAME, PHONE, CATEGORY_ID, LOCATION_ID FROM RESTAURANTS INNER JOIN CATEGORIES ON RESTAURANTS.CATEGORY_ID = CATEGORIES.ID_CATEGORY WHERE CATEGORIES.NAME = '" + category + "';", null);
        else
            cursor = db.rawQuery("SELECT ID_RESTAURANT, RESTAURANTS.NAME, PHONE, CATEGORY_ID, LOCATION_ID FROM RESTAURANTS INNER JOIN CATEGORIES ON RESTAURANTS.CATEGORY_ID = CATEGORIES.ID_CATEGORY WHERE CATEGORIES.NAME = '" + category + "' AND RESTAURANTS.NAME LIKE '%" + search + "%';", null);

        while (cursor.moveToNext()) {
            int id_restaurant = cursor.getInt(cursor.getColumnIndex("ID_RESTAURANT"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
            int category_id = cursor.getInt(cursor.getColumnIndex("CATEGORY_ID"));
            int location_id = cursor.getInt(cursor.getColumnIndex("LOCATION_ID"));

            Restaurant restaurant = new Restaurant(id_restaurant, name, phone, category_id, location_id);

            restaurants.add(restaurant);
        }

        return restaurants;
    }

    public ArrayList<Item> read_items(int id_restaurant)
    {
        System.out.println("*** read_items ***");
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM ITEMS WHERE RESTAURANT_ID = '" + id_restaurant + "';", null);
        ArrayList<Item> items = new ArrayList<>();

        while (cursor.moveToNext())
        {
            ArrayList<String> row = new ArrayList<>();
            int id_item = cursor.getInt(cursor.getColumnIndex("ID_ITEM"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String price = cursor.getString(cursor.getColumnIndex("PRICE"));
            int restaurant_id = cursor.getInt(cursor.getColumnIndex("RESTAURANT_ID"));

            Item item = new Item(id_item, name, price, restaurant_id);

            items.add(item);
        }

        return items;
    }

    public String getPhoneNumber(int id_restaurant)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT PHONE FROM RESTAURANTS WHERE ID_RESTAURANT = '" + id_restaurant + "';", null);
        String phoneNumber = null;
        while (cursor.moveToNext()) {
            phoneNumber = cursor.getString(cursor.getColumnIndex("PHONE"));
        }
        return phoneNumber;
    }

    public Double[] getRestaurantLocation(int id_location)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LOCATIONS WHERE id_location = '" + id_location + "';", null);
        Double[] restaurentLocation = new Double[2];
        while (cursor.moveToNext()) {
            restaurentLocation[0] = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LATITUDE")));
            restaurentLocation[1] = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
        }
        return restaurentLocation;
    }

    public Double[] getRestaurantLocationById(int id_restaurant)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT LATITUDE, LONGITUDE FROM RESTAURANTS INNER JOIN LOCATIONS ON RESTAURANTS.LOCATION_ID = LOCATIONS.ID_LOCATION WHERE RESTAURANTS.ID_RESTAURANT = '" + id_restaurant + "'", null);
        Double[] restaurentLocation = new Double[2];
        while (cursor.moveToNext()) {
            restaurentLocation[0] = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LATITUDE")));
            restaurentLocation[1] = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
        }
        return restaurentLocation;
    }
}