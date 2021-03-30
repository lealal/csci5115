package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class EditItems extends AppCompatActivity {
    private Item item;
    private TextView editName, editDate;
    public static ImageView imageViewEdit;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<Item> realList = MainActivity.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        setTitle("Edit Item");
        item = (Item) getIntent().getSerializableExtra("Item");
        editName = findViewById(R.id.editName);
        editName.setText(item.getItemName());
        editDate = findViewById(R.id.editDate);
        editDate.setText(item.getItemAddedDate().toString());
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        imageViewEdit = findViewById(R.id.imageViewEdit);
        int id = getResources().getIdentifier(item.getImage(), "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imageViewEdit.setImageDrawable(drawable);
    }
    public void changeImage(View view){
        int id = getResources().getIdentifier("orange", "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imageViewEdit.setImageDrawable(drawable);
        item.setImage("orange");
        Intent intent = new Intent(this, ChangePicture.class);
        startActivity(intent);
    }
    public void saveEdit(View view){
        for(int i = 0; i<realList.size(); i++){
            if(realList.get(i).getItemName().equals(item.getItemName())) {
                System.out.println(realList.get(i).getItemName());
                editName = findViewById(R.id.editName);
                realList.get(i).setItemName(editName.getText().toString());
                realList.get(i).setItemAddedDate(editDate.getText().toString());
                realList.get(i).setLocation(spinner.getSelectedItem().toString());
                realList.get(i).setImage(item.getImage());
                System.out.println(realList.get(i).getItemName());
            }
        }
        MainActivity.setList(realList);
//        iAdapter.notifyDataSetChanged();

        finish();
    }
    public void editDelete(View view){
        for(int i = 0; i<realList.size(); i++){
            if(realList.get(i).getItemName().equals(item.getItemName())) {
                System.out.println(realList.get(i).getItemName());
                realList.remove(realList.get(i));
                System.out.println(realList.get(i).getItemName());
            }
        }
        MainActivity.setList(realList);
//        iAdapter.notifyDataSetChanged();

        finish();
    }
}