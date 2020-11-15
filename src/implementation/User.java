package implementation;

import java.io.*;

public class User implements Externalizable {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String position;

    public User(){}

    public User(String username, String password, String first_name, String last_name, String patronymic, String position) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(username);
        out.writeObject(password);
        out.writeObject(first_name);
        out.writeObject(last_name);
        out.writeObject(patronymic);
        out.writeObject(position);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        username = (String) in.readObject();
        password = (String) in.readObject();
        first_name = (String) in.readObject();
        last_name = (String) in.readObject();
        patronymic = (String) in.readObject();
        position = (String) in.readObject();
    }

    public static void save(String path, User user) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));) {
            objectOutputStream.writeObject(user);
        }
    }

    public static User load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            return (User) objectInputStream.readObject();
        }
    }
}
