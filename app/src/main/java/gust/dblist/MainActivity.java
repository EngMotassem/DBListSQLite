package gust.dblist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ArrayList<Contact> contacts=new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBAdapter DB=new DBAdapter(MainActivity.this);

        Button add= (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name= (EditText) findViewById(R.id.name);
                EditText phone= (EditText) findViewById(R.id.phone);
                String n=name.getText().toString();
                int num=     Integer.parseInt(   phone.getText().toString());
                DB.open();
                Contact c1=new Contact(n,num);
                DB.add(c1);
                DB.retreiveAll();
                DB.close();
            }
        });

//contacts.add(new Contact("ahmad",333444));
        //      contacts.add(new Contact("ali",555666));


        DB.open();
        contacts=DB.retreiveAll();
        DB.close();

        ListView mylist= (ListView) findViewById(R.id.mylist);
        MyOwnAdapter myadapter= new MyOwnAdapter(MainActivity.this,R.layout.item,contacts);

        mylist.setAdapter(myadapter);




    }

    public class MyOwnAdapter extends ArrayAdapter<Contact>{

        public MyOwnAdapter(Context context, int resource, List<Contact> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=getLayoutInflater().inflate(R.layout.item,null);

            TextView name= (TextView) v.findViewById(R.id.thename);
            name.setText(contacts.get(position).name);
            TextView thenumber= (TextView) v.findViewById(R.id.thenumber);
            thenumber.setText(contacts.get(position).number +"");

            return v;



        }
    }

}
