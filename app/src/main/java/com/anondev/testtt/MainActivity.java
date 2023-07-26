package com.anondev.testtt;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.anondev.testtt.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemTouchHelper.SwipedCallback {
    private static final int SELECT_PICTURE = 111;
    private static final int CODE_READ_DATE = 1111;
    private ActivityMainBinding binding;
    private Adapter adapter;
    private List<Item> list;
    private List<Item> listBackup;
    private List<String> listStrings;
    private ItemTouchHelper.SimpleCallback itemTouchHelper;
    private Snackbar snackbar;
    private Map<Integer, Item> objectMap;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        initListeners();
    }

    private void initViews() {
        adapter = new Adapter();
        list = new ArrayList<>();
        objectMap = new TreeMap<>();
        listStrings = new ArrayList<>();
        listBackup = new ArrayList<>();
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        list.add(new Item("A", listStrings));
        list.add(new Item("B", listStrings));
        list.add(new Item("C", listStrings));
        list.add(new Item("D", listStrings));
        list.add(new Item("E", listStrings));
        listBackup.addAll(list);
        adapter.setData(list);
        binding.rv.setAdapter(adapter);
        itemTouchHelper = new RecyclerViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rv);
    }

    private void initListeners() {
        snackbar = Snackbar.make(binding.getRoot(), "Xoa " + num + " item", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.setAction("Hoàn tác", view -> {
            for (Map.Entry<Integer, Item> entry : objectMap.entrySet()) {
                list.add(entry.getKey(), entry.getValue());
                adapter.notifyItemInserted(entry.getKey());
            }
            objectMap.clear();
            num = 0;
        });
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT || event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {
                    num = 0;
                    Log.d("test123", "onDismissed: ");
                }
            }
        });
    }

    @Override
    public void setSwipedListener(RecyclerView.ViewHolder viewHolder) {
        int pos = viewHolder.getAdapterPosition();
        Item item = list.get(pos);
        int position = listBackup.indexOf(item);
        num++;
        objectMap.put(position, item);
        list.remove(pos);
        adapter.notifyItemRemoved(pos);
        snackbar.setText("Xoa " + num + " item");
        snackbar.show();
    }
}