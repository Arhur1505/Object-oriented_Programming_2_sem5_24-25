public class User{
    private boolean isStudent;
    private String name;

    public User(boolean isStudent, String name){
        this.isStudent = isStudent;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean getIsStudent(){
        return isStudent;
    }
}