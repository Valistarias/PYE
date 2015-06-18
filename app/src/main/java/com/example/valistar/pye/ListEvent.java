package com.example.valistar.pye;

/**
 * Created by Valistar on 18/06/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valistar.pye.model.EventsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.example.valistar.pye.model.Event;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ListEvent extends ActionBarActivity {

    Button logout;
    private Button btnCreate;
    private EventsAdapter adapter;
    private ArrayList<Event> arrayOfEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);


        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                Intent LoginSignup = new Intent(getApplicationContext(), LoginSignupActivity.class);
                startActivity(LoginSignup);
            }
        });

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent createActivity = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(createActivity);

            }
        });

        arrayOfEvents = new ArrayList<Event>();
        adapter = new EventsAdapter(this, arrayOfEvents);
        ListView listView = (ListView) findViewById(R.id.lv_event);
        listView.setAdapter(adapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> eventList, ParseException e) {
                if (e == null) {
                    for (ParseObject events : eventList) {

                        Event event = new Event();

                        event.setEventId(events.getObjectId());
                        event.setName(events.getString("name"));

                        Log.d("name", event.getName());
                        adapter.add(event);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = adapter.getItem(position);

                Intent intent = new Intent(getBaseContext(), UpdateEvent.class);
                intent.putExtra("objectId",  event.getEventId());
                startActivity(intent);
            }
        });

    }
}
