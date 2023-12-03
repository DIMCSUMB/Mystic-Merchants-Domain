package com.example.mystic_merchants_domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DeleteUserActivity extends AppCompatActivity {

    private ListView deleteListView;
    private List<Users> userList;
    private UserAdapter userAdapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteListView = findViewById(R.id.delete_user_ListView);
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "merchants_database").build();
        new GetUsersTask().execute();
    }

    private class GetUsersTask extends AsyncTask<Void, Void, List<Users>> {
        @Override
        protected List<Users> doInBackground(Void... voids) {
            return database.usersDAO().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            userList = users;
            userAdapter = new UserAdapter(DeleteUserActivity.this, userList); // Adapt this line as per your adapter
            deleteListView.setAdapter(userAdapter);
            setupListViewListener();
        }
    }

    private void setupListViewListener() {
        deleteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmDeletion(userList.get(position));
            }
        });
    }

    private void confirmDeletion(final Users user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteUserTask().execute(user);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private class DeleteUserTask extends AsyncTask<Users, Void, Void> {
        @Override
        protected Void doInBackground(Users... users) {
            database.usersDAO().deleteUser(users[0].getUsername()); // Adapt this line as per your Users class
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(DeleteUserActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
            new GetUsersTask().execute(); // Refresh the list
        }
    }
}