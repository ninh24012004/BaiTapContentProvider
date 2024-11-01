package com.example.baitapcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitapcontentprovider.Models.Message;

import java.util.ArrayList;

public class MessagePage extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1000;
    private ListView lvMessage;
    private ArrayList<Message> listMessage;
    private ArrayAdapter<Message> adapterMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message_page);
        addControls();
    }

    private void addControls() {
        initUI();
        listMessage = new ArrayList<>();
        adapterMessage = new ArrayAdapter<>(MessagePage.this, android.R.layout.simple_list_item_1, listMessage);
        lvMessage.setAdapter(adapterMessage);
        showAllMessages();
    }

    private void initUI() {
        lvMessage = findViewById(R.id.lv_message);
    }

    private void showAllMessages() {
        Uri uri = Telephony.Sms.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if (cursor != null) {
            listMessage.clear();
            while (cursor.moveToNext()) {
                String nameContact = Telephony.Sms.ADDRESS;
                String bodySms = Telephony.Sms.BODY;

                int namePosition = cursor.getColumnIndex(nameContact);
                int bodySmsPosition = cursor.getColumnIndex(bodySms);

                String name = cursor.getString(namePosition);
                String message = cursor.getString(bodySmsPosition);

                Message newMessage = new Message(name,message);
                listMessage.add(newMessage);
            }
            adapterMessage.notifyDataSetChanged();
            cursor.close();
        } else {
            Toast.makeText(this, "Không thể truy cập vào tin nhắn", Toast.LENGTH_SHORT).show();
        }
    }
}