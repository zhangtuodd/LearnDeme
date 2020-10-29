package xuliehua;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 * 序列化反序列化（s和p区别）
 * 概念：内存可以保存对象，硬盘只能存储字节形式的数据。网络传输数据也只能以字节码序列.注：Android中要想传递对象(指内存)也必须序列化
 * 对象内部可能包括成员属性，成员方法等等，如果能通过一种映射关系将 内存中对象的各个部分和硬盘的字节码 对应起来。就可以将内存对象保存到硬盘。
 * 以上这个过程称之为序列化（将内存中的java对象或file转换为磁盘的字节码序列）
 * 有了映射关系就同样能反序列化（将磁盘的字节码序列 转换成 内存中的java对象或file）
 * 扩展：
 * 	serialVersionUID ：每次序列化会生成该ID，该ID是根据java对象中的各部分hash生成。对应序列化对象，当java对象发生变化后再访问序列化数据则会报错
 * 	序列化后磁盘文件会保存敏感信息，如果需要擦除则在java对象对应属性上添加transient字段
 * 将对象写入磁盘如果没有序列化 则报异常java.io.NotSerializableException:。但是也能存储到硬盘：只不过文件内容是异常堆栈信息而非原来的对象信息
 *
 * Json序列化和serizable序列化区别
 * Json序列化只是通过Gson或者java自带JSONObject工具将对象先转换成json字符串，再通过字符串来传递或者读写存储
 * Json序列化后会压缩的更小，另外json具有通用性可跨平台
 *
 * Serializable性能分析
 * Serializable 是 Java 中的序列化接口，其使用起来简单但开销较大(因为 Serializable 在序列化过程中使用了反射机制，故而会产生大量的临时变量，从而导致频繁的GC)，并且在读写数据过程中，它是通 过IO流的形式将数据写入到硬盘或者传输到网络上。
 * Parcelable性能分析
 * Parcelable 则是以 IBinder 作为信息载体，在内存上开销比较小，因此在内存之间进行数据传递时，推荐使用 Parcelable，而 Parcelable 对数据进行持久化或者网络传输时操作复杂，一般这个时候推荐使用 Serializable。
 *
 * @author zhangtuo
 * @date 2020-10-28
 */
public class MainClass {
    String mFileName;


    // 利用对象输出流把序列化对象写入文件
    public void writeObject(Context context) {
        // 下面创建可序列化的用户信息对象，并给予赋值
        UserInfo user = new UserInfo();
        user.setName("name");
        user.setPhone("15960238696");
        user.setPassword("111111");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        // 根据指定文件路径构建文件输出流对象，然后据此构建对象输出流对象
        File file = new File( context.getExternalCacheDir().getAbsolutePath() + File.separator + "user1.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file.toString());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 利用对象输入流从文件中读取序列化对象
    private void readObject(Context context) {
        mFileName = context.getCacheDir().getAbsolutePath() + "/user.txt";
        // 创建可序列化的用户信息对象
        UserInfo user = new UserInfo();
        // 根据指定文件路径构建文件输入流对象，然后据此构建对象输入流对象
        try (FileInputStream fos = new FileInputStream(mFileName);
             ObjectInputStream ois = new ObjectInputStream(fos);) {
            user = (UserInfo) ois.readObject(); // 从文件读取对象信息
            System.out.println("对象反序列化成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 注意用户信息的密码字段设置了禁止序列化，故而文件读到的密码字段为空
        String desc = String.format("姓名=%s,手机号=%s,密码=%s",
                user.getName(), user.getPhone(), user.getPassword());
        System.out.println("用户信息如下：" + desc);
    }
}
