package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat;
    Spinner spnSubCat;
    Button btnGo;
    ArrayList<String> alSubCat;
    ArrayAdapter<String> aaSubCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.spinner1);
        spnSubCat = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        // Initialise the ArrayList
        alSubCat = new ArrayList<>();

        // Create an ArrayAdapter using the default Spinner layout and teh ArrayList
        aaSubCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, alSubCat);

        // Add items to array - 2nd Way (Using string array created in strings.xml
        String[] strSubCat = getResources().getStringArray(R.array.sub_category_rp);

        // Convert Array to List and add to the ArrayList
        alSubCat.addAll(Arrays.asList(strSubCat));

        // Bind the ArrayAdapter to the Spinner
        spnSubCat.setAdapter(aaSubCat);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alSubCat.clear();

                switch (i) {

                    case 0:
                        String[] strRPsub = getResources().getStringArray(R.array.sub_category_rp);
                        alSubCat.addAll(Arrays.asList(strRPsub));
                        aaSubCat.notifyDataSetChanged();
                        break;

                    case 1:
                        String[] strSOIsub = getResources().getStringArray(R.array.sub_category_soi);
                        alSubCat.addAll(Arrays.asList(strSOIsub));
                        aaSubCat.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int catPos = spnCat.getSelectedItemPosition();
                int subcatPos = spnSubCat.getSelectedItemPosition();

                // 2-D Array
                String[][] sites = {
                                    {
                                        "https://www.rp.edu.sg/",
                                            "https://www.rp.edu.sg/student-life"
                                    },
                                    {
                                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                            "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                                    }
                                    };

                Intent intent = new Intent(getBaseContext(), ShowWebViewActivity.class);

                String url = sites[catPos][subcatPos];

                intent.putExtra("url", url);
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1b: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1c: Add the key-value pair
        int catPos = spnCat.getSelectedItemPosition();
        int subcatPos = spnSubCat.getSelectedItemPosition();

//        if (catPos == 0 && subcatPos == 0){
//            prefEdit.putInt("cat", 0);
//            prefEdit.putInt("subcat", 0);
//        }
//        else if (catPos == 0 && subcatPos == 1){
//            prefEdit.putInt("cat", 0);
//            prefEdit.putInt("subcat", 1);
//        }
//        else if (catPos == 1 && subcatPos == 0){
//            prefEdit.putInt("cat", 1);
//            prefEdit.putInt("subcat", 0);
//        }
//        else if (catPos == 1 && subcatPos == 1){
//            prefEdit.putInt("cat", 1);
//            prefEdit.putInt("subcat", 1);
//        }

        prefEdit.putInt("cat", catPos);
        prefEdit.putInt("subcat", subcatPos);

        //Step 1d: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting from the SharedPreferences object
        int cat = prefs.getInt("cat", 0);
        int subcat = prefs.getInt("subcat", 0);

//        if (cat == 0 && subcat == 0){
//            spnCat.setSelection(cat);
//            spnSubCat.setSelection(subcat);
//        }
//        else if (cat == 0 && subcat == 1){
//            spnCat.setSelection(cat);
//            spnSubCat.setSelection(subcat);
//        }
//        else if (cat == 1 && subcat == 0){
//            spnCat.setSelection(cat);
//            spnSubCat.setSelection(subcat);
//        }
//        else if (cat == 1 && subcat == 1){
//            spnCat.setSelection(cat);
//            spnSubCat.setSelection(subcat);
//        }

        spnCat.setSelection(cat);
        spnSubCat.setSelection(subcat);

    }
}
