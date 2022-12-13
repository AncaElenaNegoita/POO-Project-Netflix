package InputData;

import java.util.ArrayList;

public final class Input {
    private ArrayList<User> users;
    private ArrayList<MovieList> movies;
    private ArrayList<Action> actions;
    private static Input instance = null;


    public Input() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<MovieList> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieList> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    // Singleton Design Pattern
    public static Input getInstance() {
        return instance;
    }

    public static void setInstance(Input instance) {
        if (Input.getInstance() == null)
            Input.instance = instance;
    }

    @Override
    public String toString() {
        return "InputData.Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
