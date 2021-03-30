package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EditItems extends AppCompatActivity {
    private Item item;
    private TextView editName, editLocation, editDate;
    private ImageView imageViewEdit;
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<Item> realList = MainActivity.getList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        item = (Item) getIntent().getSerializableExtra("Item");
        editName = findViewById(R.id.editName);
        editName.setText(item.getItemName());
        editDate = findViewById(R.id.editDate);
        editDate.setText(item.getItemAddedDate().toString());
        editLocation = findViewById(R.id.editLocation);
        editLocation.setText(item.getLocation());
        imageViewEdit = findViewById(R.id.imageViewEdit);
        int id = getResources().getIdentifier(item.getImage(), "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imageViewEdit.setImageDrawable(drawable);
    }
    public void changeImage(View view){
        Intent intent = new Intent(this, ChangePicture.class);
        startActivity(intent);
    }
    public void saveEdit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        for(int i = 0; i<realList.size(); i++){
            if(realList.get(i).getItemName().equals(item.getItemName())) {
                System.out.println(realList.get(i).getItemName());
                editName = findViewById(R.id.editName);
                realList.get(i).setItemName(editName.getText().toString());
                System.out.println(realList.get(i).getItemName());
            }
        }
//        iAdapter.notifyDataSetChanged();

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }
}