package Game;

public class User {
    private String name;
    private int score;

    public User(String name){
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
