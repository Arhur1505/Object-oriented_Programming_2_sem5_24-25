import java.util.List;
import java.util.ArrayList;

class User {
    private String name;
    private boolean isStudent;

    public User(String name, boolean isStudent) {
        this.name = name;
        this.isStudent = isStudent;
    }

    public String getName() {
        return name;
    }

    public boolean getIsStudent() {
        return isStudent;
    }
}
