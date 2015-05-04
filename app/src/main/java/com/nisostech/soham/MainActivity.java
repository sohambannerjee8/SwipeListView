package com.nisostech.soham;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soham on 3/5/15.
 */

public class MainActivity extends Activity {

    OnSwipeTouchListener onSwipeTouchListener;
    List<String> list = new ArrayList<String>();
    ImageView delete;
    CustomAdapter adapter;
    ListView listView;
    int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSwipeTouchListener = new OnSwipeTouchListener();

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);
        listView = (ListView) findViewById(R.id.listview);
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new CustomAdapter(this, list);

        /** Defining a click event listener for the button "Add" */
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };
        listView.setOnTouchListener(new OnSwipeTouchListener(this, listView) {

            @Override
            public void onSwipeRight(int pos) {

                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_LONG).show();
                showDeleteButton(pos);
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_LONG).show();
            }
        });
        btn.setOnClickListener(listener);
        /** Setting the adapter to the ListView */
        listView.setAdapter(adapter);

    }

    private boolean showDeleteButton(int pos) {
        position = pos;
        View child = listView.getChildAt(pos - listView.getFirstVisiblePosition());
        if (child != null) {

            delete = (ImageView) child.findViewById(R.id.delete);
            if (delete != null) {
                if (delete.getVisibility() == View.INVISIBLE) {
                    Animation animation =
                            AnimationUtils.loadAnimation(this,
                                    R.anim.slide_out_left);
                    delete.startAnimation(animation);
                    delete.setVisibility(View.VISIBLE);
                } else {
                    Animation animation =
                            AnimationUtils.loadAnimation(this,
                                    R.anim.slide_in_right);
                    delete.startAnimation(animation);
                    delete.setVisibility(View.INVISIBLE);
                }
            }
            return true;
        }
        return false;
    }

    public void deleteItem(View view) {
        //   delete = (ImageView) view.findViewById(R.id.delete);
        list.remove(position);
        delete.setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }
}