package actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import inputdata.Input;
import inputdata.MovieList;
import inputdata.Notification;
import inputdata.User;
import outputdata.NodeChangePage;

public class Recommendation {
    /**
     * This function gets a specific recommendation to the current premium player
     * @param node - where the output is stored
     * @return
     */
    public static ObjectNode getRecommendation(final ObjectNode node) {
        UserActions.filteredMovieList = null;
        Notification notif = new Notification();

        if (Input.getInstance().getUsers().get(UserActions.positionUser).getCredentials().
                getAccountType().equals("premium")) {
            int i, ok = 0, like = -1;
            User user = Input.getInstance().getUsers().get(UserActions.positionUser);
            for (i = 0; i < user.getLikedGenres().size(); i++) {
                String genre = user.getLikedGenres().get(i).getGenre();
                for (MovieList movie : Input.getInstance().getMovies()) {
                    ok = 0;
                    for (String genreMovie : movie.getGenres()) {
                        if (genreMovie.equals(genre)) {
                            for (MovieList movieWatched : user.getWatchedMovies()) {
                                if (movie.getName().equals(movieWatched.getName())) {
                                    ok = 1;
                                    break;
                                }
                            }
                            if (ok == 0 && like < movie.getNumLikes()) {
                                notif = new Notification();
                                notif.setMessage("Recommendation");
                                notif.setMovieName(movie.getName());
                                like = movie.getNumLikes();
                            }
                            break;
                        }
                    }
                }
            }
            if (like != -1) {
                Input.getInstance().getUsers().get(UserActions.positionUser).
                        getNotifications().add(notif);
                return NodeChangePage.recommendationOutput(node);
            }

            notif = new Notification();
            notif.setMessage("Recommendation");
            notif.setMovieName("No recommendation");
            Input.getInstance().getUsers().get(UserActions.positionUser).
                    getNotifications().add(notif);
            return NodeChangePage.recommendationOutput(node);
        }
        return node;
    }
}
