package contentprovider_sp_ipc.contentprovider_ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2020-09-23
 */
public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public Book() {

    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(bookId);
        out.writeString(bookName);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

}