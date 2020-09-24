package contentprovider_sp_ipc.contentprovider_ipc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.base.util.LogUtils;

/**
 * LogUtils需要到对应进程去看
 *
 * 特别注意：本例子将contentProvider定义为单独的进程
 *
 * 我们在主进程activity进行操作另一个进程的数据(provider)，最终在主进程获取到查找数据。这里解释来进程间通信
 *
 * onCreate()是在provider进程的主线程执行
 * insert delete update query getType增删改查以及获取类型 是在provider进程的子线程执行
 * 所以onCreate不能执行耗时操作
 *
 * 另外insert delete update query 存在多线程并发问题，本例中SQLiteDatabase内部对数据库操作有做同步处理，
 * 如果换成list就需要去做多线程同步处理
 *
 * 疑问：mainActivity打印的主线程 和 contentProvider打印的主线程都是 thread:main
 * thread:main 中创建SQLiteDatabase，
 * thread:main 在不同进程间起什么作用？
 *
 * contentProvider就是一个媒介，通信的媒介，数据还是有db提供的，数据也可以换成sp等其他形式
 */
public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";

    public static final String AUTHORITY = "com.example.zhangtuo.learndeme.MyContentProvider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        //为的是sUriMatcher.match(uri)找到对应的code
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {

        mContext = getContext();
        LogUtils.d(TAG, "onCreate, current thread:"
                + Thread.currentThread().getName() +"    context："+mContext.hashCode());
        initProviderData();
        return true;
    }

    /**
     * onCreate 是运行在主线程中的，因此不应该在内部执行耗时操作。而且标准写法也是通过增删改查函数来操作
     * …… 以下只是为了模拟添加数据
     */
    private void initProviderData() {
        mDb = new MyDbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + MyDbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from " + MyDbOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(4,'Ios');");
        mDb.execSQL("insert into book values(5,'Html5');");
        mDb.execSQL("insert into user values(1,'jake',1);");
        mDb.execSQL("insert into user values(2,'jasmine',0);");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        LogUtils.d(TAG, "query, current thread:" + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public String getType(Uri uri) {
        LogUtils.d(TAG, "getType");
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        LogUtils.d(TAG, "insert");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        LogUtils.d(TAG, "delete");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        LogUtils.d(TAG, "update");
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = MyDbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = MyDbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }

        return tableName;
    }
}
