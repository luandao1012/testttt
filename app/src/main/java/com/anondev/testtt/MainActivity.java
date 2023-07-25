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
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemTouchHelper.SwipedCallback {
    private static final int SELECT_PICTURE = 111;
    private static final int CODE_READ_DATE = 1111;
    private ActivityMainBinding binding;
    private Adapter adapter;
    private List<Item> list;
    private List<String> listStrings;
    private ItemTouchHelper.SimpleCallback itemTouchHelper;
    private Snackbar snackbar;
    private List<Integer> posList;
    private List<Item> objectList;
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
        posList = new ArrayList<>();
        objectList = new ArrayList<>();
        listStrings = new ArrayList<>();
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        listStrings.add("A");
        list.add(new Item("A", listStrings));
        list.add(new Item("A", listStrings));
        list.add(new Item("A", listStrings));
        list.add(new Item("A", listStrings));
        list.add(new Item("A", listStrings));
        adapter.setData(list);
        binding.rv.setAdapter(adapter);
        itemTouchHelper = new RecyclerViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rv);
    }

    private void initListeners() {
        snackbar = Snackbar.make(binding.getRoot(), "Xoa " + num + " item", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.setAction("Hoàn tác", view -> {
            for (int i = 0; i < posList.size(); i++) {
                list.add(posList.get(i), objectList.get(i));
                adapter.notifyItemInserted(posList.get(i));
            }
            num = 0;
            posList.clear();
            objectList.clear();
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
        num++;
        posList.add(pos);
        objectList.add(list.get(pos));
        list.remove(pos);
        adapter.notifyItemRemoved(pos);
        snackbar.setText("Xoa " + num + " item");
        snackbar.show();
    }
}