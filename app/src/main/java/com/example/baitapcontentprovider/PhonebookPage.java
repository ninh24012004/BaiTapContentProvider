package com.example.baitapcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitapcontentprovider.Models.Contact;

import java.util.ArrayList;

public class PhonebookPage extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1000;
    private ListView lvPhonebook;
    private ArrayList<Contact> listPhonebook;
    private ArrayAdapter<Contact> adapterPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phonebook_page);
        addControls();
    }

    private void addControls() {
        initUI();
        listPhonebook = new ArrayList<>();
        adapterPhonebook = new ArrayAdapter<>(PhonebookPage.this, android.R.layout.simple_list_item_1, listPhonebook);
        lvPhonebook.setAdapter(adapterPhonebook);
        showAllContacts();
    }

    private void initUI() {
        lvPhonebook = findViewById(R.id.lv_phonebook);
    }

    private void showAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if (cursor != null) {
            listPhonebook.clear();
            while (cursor.moveToNext()) {
                String nameContact = ContactsContract.Contacts.DISPLAY_NAME;
                String phoneNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;

                int namePosition = cursor.getColumnIndex(nameContact);
                int phoneNumberPosition = cursor.getColumnIndex(phoneNumber);

                String name = cursor.getString(namePosition);
                String phone = cursor.getString(phoneNumberPosition);

                Contact contact = new Contact(name, phone);
                listPhonebook.add(contact);
            }
            adapterPhonebook.notifyDataSetChanged();
            cursor.close();
        } else {
            Toast.makeText(this, "Không thể truy cập danh bạ", Toast.LENGTH_SHORT).show();
        }
    }
}