package net.cpacm.acgkoto.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * 手机联系人工具类,使用前需要检查是否有相应权限
 * Created by Lion on 2015/7/9.
 */
public class ContactsManager {

    private Context context;

    private static ContactsManager instance = null;

    private ContactsManager(Context context) {
        this.context = context;
    }

    public static ContactsManager getInstance(Context context) {
        if (instance == null) {
            instance = new ContactsManager(context);
        }
        return instance;
    }

    //查询所有联系人的姓名，电话，邮箱
    public String getContacts() {
        long start = System.currentTimeMillis();
        List<Contact> contacts = new ArrayList<>();

        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            int contractID = cursor.getInt(0);
            uri = Uri.parse("content://com.android.contacts/contacts/" + contractID + "/data");
            Cursor cursor1 = resolver.query(uri, new String[]{"mimetype", "data1", "data2"}, null, null, null);
            while (cursor1.moveToNext()) {
                String data1 = cursor1.getString(cursor1.getColumnIndex("data1"));
                String mimeType = cursor1.getString(cursor1.getColumnIndex("mimetype"));
                if ("vnd.android.cursor.item/name".equals(mimeType)) { //是姓名
                    contact.setName(data1);
                } else if ("vnd.android.cursor.item/email_v2".equals(mimeType)) { //邮箱
                    contact.setEmail(data1);
                } else if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) { //手机
                    contact.setPhone(data1);
                }

            }
            contacts.add(contact);
            cursor1.close();
        }
        cursor.close();
        return list2Json(contacts);
    }

    private String list2Json(List<Contact> contacts) {
        StringBuilder builder = new StringBuilder();
        if (contacts.size() == 0)
            return "[]";
        for (int i = 0; i < contacts.size(); i++) {
            builder.append(contacts.get(i).toString());
            if (i != contacts.size() - 1) {
                builder.append(",");
            }
        }
        Log.d("ifen", "list2Json:" + builder.toString());
//        IFENLogger.d("list2Json:" + builder.toString());
        return "[" + builder.toString() + "]";
    }

    /**
     * 自定义联系人类
     */
    class Contact {
        private String name;
        private String phone;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"name\":" + "\"" + name + "\"" +
                    ", \"phone\":" + "\"" + phone + '\"' +
                    ", \"email\":" + "\"" + email + '\"' +
                    "}";
        }

    }
}
