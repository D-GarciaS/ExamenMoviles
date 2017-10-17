package com.example.damiangarcia.tareasettings;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.damiangarcia.tareasettings.Beans.ItemProduct;
import com.example.damiangarcia.tareasettings.database.DataBaseHandler;
import com.example.damiangarcia.tareasettings.database.ItemProductControl;

import java.util.ArrayList;

import static com.example.damiangarcia.tareasettings.database.DataBaseHandler.KEY_CATEGORY_NAME;
import static com.example.damiangarcia.tareasettings.database.DataBaseHandler.KEY_PRODUCT_CATEGORY;
import static com.example.damiangarcia.tareasettings.database.DataBaseHandler.TABLE_CATEGORY;
import static com.example.damiangarcia.tareasettings.database.DataBaseHandler.TABLE_PRODUCT;

public class FragmentHome extends android.support.v4.app.Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ItemProduct> myDataSet = new ArrayList<ItemProduct>();

    public FragmentHome() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_home_recycler_view);
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        ItemProductControl itemProductControl = new ItemProductControl();
        myDataSet = itemProductControl.getProductsWhere(
                "C."+KEY_CATEGORY_NAME+" = 'HOME' " ,
                DataBaseHandler.KEY_PRODUCT_ID + " ASC",
                DataBaseHandler.getInstance(getActivity()));

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        itemProductControl = null;
        return view;
    }

    public void notifyDataSetChanged(ItemProduct itemProduct){
        boolean bandera = false;
        for(ItemProduct p : myDataSet){
            if(p.getCode() == itemProduct.getCode()){
                bandera = true;
                p.setCategory(itemProduct.getCategory());
                p.setStore(itemProduct.getStore());
                p.setImage(itemProduct.getImage());
                p.setDescription(itemProduct.getDescription());
                p.setTitle(itemProduct.getTitle());
            }
        }
        if(bandera == false)
            myDataSet.add(itemProduct);
        mAdapter.notifyDataSetChanged();
    }

}
