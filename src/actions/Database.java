package actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import inputdata.*;
import outputdata.NodeChangePage;

public class Database {
    /**
     * This function adds a new movie in the database if it isn't inserted already
     * @param action - the action that contains the added movie
     * @param node - the node that has the information to be printed in the output file
     * @return - the node
     */
    public static ObjectNode databaseAdd(final Action action, final ObjectNode node) {
        String movieName = action.getAddedMovie().getName();

        for (MovieList movie : Input.getInstance().getMovies()) {
            if (movie.getName().equals(movieName)) {
                return NodeChangePage.changePageError(node);
            }
        }
        Input.getInstance().getMovies().add(action.getAddedMovie());

        for (int i = 0; i < Input.getInstance().getUsers().size(); i++) {
            int verifyExist = 0;
            User user = Input.getInstance().getUsers().get(i);
            for (String genre : action.getAddedMovie().getGenres()) {
                for (String subscribedGenre : user.getSubscribedGenres()) {
                    if (genre.equals(subscribedGenre)) {
                        int ok = 0;
                        for (String country : action.getAddedMovie().getCountriesBanned()) {
                            if (country.equals(user.getCredentials().getCountry())) {
                                ok = 1;
                                break;
                            }
                        }
                        if (ok == 0) {
                            Notification notif = new Notification();
                            notif.setMessage("ADD");
                            notif.setMovieName(movieName);
                            Input.getInstance().getUsers().get(i).getNotifications().add(notif);
                            verifyExist = 1;
                            break;
                        }
                    }
                }
                if (verifyExist == 1) {
                    break;
                }
            }
        }
        return node;
    }

    /**
     * This function delets a new movie in the database if it exists in the movie database
     * @param action - the action that contains the movie that needs to be deleted
     * @param node - the node that has the information to be printed in the output file
     * @return - the node
     */
    public static ObjectNode databaseDelete(final Action action, final ObjectNode node) {
        String movieName = action.getDeletedMovie();

        for (int i = 0; i < Input.getInstance().getMovies().size(); i++) {
            MovieList movie = Input.getInstance().getMovies().get(i);

            if (movie.getName().equals(movieName)) {
                for (String genre : movie.getGenres()) {
                    for (User user : Input.getInstance().getUsers()) {
                        for (String subscribedGenre : user.getSubscribedGenres()) {
                            if (genre.equals(subscribedGenre)) {
                                int ok = 0;
                                for (String country : movie.getCountriesBanned()) {
                                    if (country.equals(user.getCredentials().getCountry())) {
                                        ok = 1;
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    Notification notif = new Notification();
                                    notif.setMessage("DELETE");
                                    notif.setMovieName(movieName);
                                    Input.getInstance().getUsers().get(UserActions.positionUser).
                                            getNotifications().add(notif);
                                    break;
                                }
                            }
                        }
                    }
                }
                Input.getInstance().getMovies().remove(i);
                return node;
            }
        }
        return NodeChangePage.changePageError(node);
    }
}
