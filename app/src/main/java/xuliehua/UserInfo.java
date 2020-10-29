package xuliehua;

import java.io.Serializable;

//定义一个可序列化的用户信息类。实现Serializable接口表示当前类支持序列化
public class UserInfo {
    private static final long serialVersionUID = 3416834382343988007L;
    private String name; // 用户名
    private String phone; // 手机号码

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password; // 密码

    public UserInfo() {
        name = "";
        phone = "";
        password = "";
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    // 以下省略各字段的get***/set***方法
}