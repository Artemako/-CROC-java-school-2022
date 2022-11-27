package src.ru.croc.tasks.task13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class User {
    private final String idUser;
    private ArrayList<String> idUserWatchedFilms = new ArrayList<>();

    private static ArrayList<User> allActiveUsers = new ArrayList<>();

    private final File fileIdUsers = new File("src/ru/croc/tasks/task13/id_users.txt");
    private final File fileIdFilms = new File("src/ru/croc/tasks/task13/films.txt");

    public User(String idUser) {
        this.idUser = idUser;
        allActiveUsers.add(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", idUserWatchedFilms=" + Arrays.toString(new List[]{idUserWatchedFilms}) +
                '}';
    }

    public void reloadDataUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileIdUsers));
            String line = reader.readLine();
            while (line != null) {
                String[] idAndFilms = line.split(" ");
                if (idAndFilms[0].equals(idUser)) {
                    idUserWatchedFilms = new ArrayList<>();
                    for (String film : idAndFilms[1].split(",")) {
                        idUserWatchedFilms.add(film);
                    }
                    break;
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            //System.out.println("Файл id_users.txt не был найден/прочитан.");
        }
    }

    public void recommendation() {
        List<User> usersWatchedSameFilms = new ArrayList<>();
        for (User user : allActiveUsers) {
            if (compareFilmsOfUsers(this, user) && this != user)
                usersWatchedSameFilms.add(user);
        }
        //System.out.println(usersWatchedSameFilms);

        HashMap<String, Integer> countFilms = new HashMap<>();
        for (User user : usersWatchedSameFilms) {
            for (String idUserFilm : user.idUserWatchedFilms) {
                if (!this.idUserWatchedFilms.contains(idUserFilm)) {
                    Integer count = countFilms.get(idUserFilm);
                    if (count == null) {
                        count = 0;
                    }
                    count++;
                    countFilms.put(idUserFilm, count);
                }
            }
        }

        Set<String> allMaybeRecommendIdFilms = countFilms.keySet();
        ArrayList<String> recommendIdFilm = new ArrayList<>();
        int maxCountFilm = 0;

        for (String idFilm : allMaybeRecommendIdFilms) {
            int count = countFilms.get(idFilm);
            if (count > maxCountFilm) {
                recommendIdFilm.clear();
                maxCountFilm = count;
                recommendIdFilm.add(idFilm);
            } else if (count == maxCountFilm) {
                recommendIdFilm.add(idFilm);
            }
        }

        //System.out.println(recommendIdFilm);

        if (recommendIdFilm.size() > 0) {
            int rnd = new Random().nextInt(recommendIdFilm.size());
            String randomRecommendIdFilm = recommendIdFilm.get(rnd);
            System.out.println(getNameFilmByIdFilm(randomRecommendIdFilm));
        } else {
            System.out.println("Наш сервис не может вам что-то порекомендовать :(");
        }


    }

    private String getNameFilmByIdFilm(String idFilm) {
        String nameFilmByIdFilm = new String();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileIdFilms));
            String line = reader.readLine();
            while (line != null) {
                String[] idFilmAndNameFilm = line.split(",");
                if (idFilmAndNameFilm[0].equals(idFilm)) {
                    nameFilmByIdFilm = idFilmAndNameFilm[1];
                    break;
                }
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            //System.out.println("Файл films.txt не был найден/прочитан.");
        }
        return nameFilmByIdFilm;
    }

    private boolean compareFilmsOfUsers(User firstUser, User secondUser) {
        HashSet<String> sameFilms = new HashSet<>();
        for (String idFirstUserFilm : firstUser.idUserWatchedFilms) {
            for (String idSecondUserFilm : secondUser.idUserWatchedFilms) {
                if (idFirstUserFilm.equals(idSecondUserFilm))
                    sameFilms.add(idSecondUserFilm);
            }
        }
        if (sameFilms.size() >= Math.round(firstUser.idUserWatchedFilms.size() / 2.0)) {
            return true;
        }
        return false;
    }
}
