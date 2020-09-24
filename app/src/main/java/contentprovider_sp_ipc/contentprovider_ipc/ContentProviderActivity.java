package contentprovider_sp_ipc.contentprovider_ipc;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.base.util.LogUtils;
import com.example.zhangtuo.learndeme.R;


public class ContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "ContentProviderActivity";

   private Uri bookUri =  MyContentProvider.BOOK_CONTENT_URI;
   private Uri userUri =  MyContentProvider.USER_CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);
//        getContentResolver().query(uri, null, null, null, null);

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(MyContentProvider.BOOK_CONTENT_URI, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            LogUtils.d(TAG, "query Book:" + book.toString());
        }
        bookCursor.close();

        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;
            LogUtils.d(TAG, "query user:" + user.toString());
        }
        userCursor.close();
    }
}
