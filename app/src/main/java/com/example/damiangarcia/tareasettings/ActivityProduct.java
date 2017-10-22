package com.example.damiangarcia.tareasettings;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.damiangarcia.tareasettings.Beans.Category;
import com.example.damiangarcia.tareasettings.Beans.ItemProduct;
import com.example.damiangarcia.tareasettings.Beans.Store;
import com.example.damiangarcia.tareasettings.database.CategoryControl;
import com.example.damiangarcia.tareasettings.database.DataBaseHandler;
import com.example.damiangarcia.tareasettings.database.ItemProductControl;
import com.example.damiangarcia.tareasettings.database.StoreControl;

import java.util.ArrayList;

public class ActivityProduct extends AppCompatActivity {

    protected Spinner stores;
    protected Spinner categories;
    protected Spinner images;
    protected EditText id;
    protected EditText title;
    protected EditText description;
    protected ArrayAdapter<Store> storesAdapter;
    protected ArrayAdapter<Category> categoriesAdapter;
    protected ArrayAdapter<String> imagesAdapter;
    protected DataBaseHandler dh; //DataBase Instance
    protected Store storeSelected; //Store selected in spinner
    protected Category categorySelected; //Category selected in spinner
    protected int imageSelected; //Image selected in spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        stores = (Spinner) findViewById(R.id.activity_product_store);
        categories = (Spinner) findViewById(R.id.activity_product_category);
        images = (Spinner) findViewById(R.id.activity_product_image);
        id = (EditText) findViewById(R.id.activity_product_id);
        title = (EditText) findViewById(R.id.activity_product_title);
        description = (EditText) findViewById(R.id.activity_product_description);

        storeSelected = null;
        categorySelected = null;
        imageSelected = -1;

        //DataBase Objects
        dh = DataBaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();
//Fill info from Database
        ArrayList<Store> storesList = storeControl.getStoresWhere(null, null, dh);
        ArrayList<Category> categoriesList = categoryControl.getAllCategories(dh);

        //Create Adapter to show into Spinner, ListView or GridLayout
        storesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storesList);
        stores.setAdapter(storesAdapter);
        categoriesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoriesList);
        categories.setAdapter(categoriesAdapter);

        ArrayList<String> myimages = new ArrayList<>();
        myimages.add("Mac");
        myimages.add("Alienware");
        imagesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myimages);
        images.setAdapter(imagesAdapter);

        stores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeSelected = storesAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                storeSelected = storesAdapter.getItem(0);

            }
        });

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = categoriesAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categorySelected = categoriesAdapter.getItem(0);

            }
        });

        images.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageSelected = 0;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_product_menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            if(isValidProduct()){
                ItemProduct itemProduct = new ItemProduct();

                itemProduct.setTitle(title.getText().toString().trim());
                itemProduct.setDescription(description.getText().toString().trim());
                itemProduct.setStore(storeSelected);
                itemProduct.setCategory(categorySelected);
                itemProduct.setImage(imageSelected);
                ItemProductControl itemProductControl = new ItemProductControl();

                int int_id = -1;
                if(id.getText() != null)
                    try {
                        int_id = Integer.parseInt(id.getText().toString().trim());
                    }catch (Exception e){}

                long res;
                if(itemProductControl.getProductById(int_id, dh) == null) {
                    res = itemProductControl.addItemProduct(itemProduct, dh);
                }else{
                    itemProduct.setCode(int_id);
                    itemProductControl.updateProduct(itemProduct,dh);
                }

                Intent intent = new Intent();
                intent.putExtra("ITEM", itemProduct);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValidProduct() {
        return true;
    }

    public void setCategorySelected(int categoryId){
        for (int position = 0; position < categoriesAdapter.getCount(); position++)
        {
            if(((Category)categoriesAdapter.getItem(position)).getIdCategory() == categoryId)
            {
                categories.setSelection(position);
                return;
            }
        }
    }
    public void setStoreSelected(int storeId){
        for (int position = 0; position < storesAdapter.getCount(); position++)
        {
            if(((Store)storesAdapter.getItem(position)).getId() == storeId)
            {
                stores.setSelection(position);
                return;
            }
        }
    }
    public void setImageSelected(int imageId){
        images.setSelection(imageId);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_product_search:
                int idProduct = 0;
                try {
                    idProduct = Integer.parseInt(id.getText().toString().trim());
                }catch(NumberFormatException e){ return; }
                ItemProductControl itemProductControl = new ItemProductControl();
                ItemProduct itemProduct = itemProductControl.getProductById(idProduct, dh);
                if(itemProduct != null) {
                    title.setText(itemProduct.getTitle());
                    description.setText(itemProduct.getDescription());
                    if(itemProduct.getCategory() != null) {
                        setCategorySelected(itemProduct.getCategory().getIdCategory());}
                    if(itemProduct.getStore()!= null) {
                        setStoreSelected(itemProduct.getStore().getId());}
                    setImageSelected(itemProduct.getImage());

                }
                break;
        }
    }

}