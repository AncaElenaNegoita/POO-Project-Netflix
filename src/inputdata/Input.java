package inputdata;

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

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<MovieList> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<MovieList> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

    // Singleton Design Pattern
    public static Input getInstance() {
        return instance;
    }

    /**
     * This function sets the instance of the class Input in order to be more accessible for
     * the other classes
     * @param instance
     */
    public static void setInstance(final Input instance) {
        if (Input.getInstance() == null) {
            Input.instance = instance;
        }
    }

    /**
     * This function resets the instance to null when a new file is generated
     * @param instance - instance of class Input
     */
    public static void resetInstance(final Input instance) {
        Input.instance = instance;
    }

    @Override
    public String toString() {
        return "InputData.Input{"
                + "users=" + users
                + ", movies=" + movies
                + ", actions=" + actions
                + '}';
    }
}
