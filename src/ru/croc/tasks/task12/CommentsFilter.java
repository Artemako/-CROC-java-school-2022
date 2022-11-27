package src.ru.croc.tasks.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommentsFilter implements BlackListFilter {

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        // скорее всего, можно было решить при помощи regex, но мне ЛЕНЬ

        // список очищенных коментариев
        ArrayList<String> cleanedUpComments = new ArrayList<>();

        for (String comment : comments) {
            // будущий чистый комментарий
            StringBuilder cleanComment = new StringBuilder(comment);

            // копия комментария для поиска слов
            String forSearchComment = ' ' + comment;
            // заменяем пункт. знаки на пробелы и заменяем на lower
            forSearchComment = forSearchComment.replaceAll("[,.!?\\-]", " ");
            forSearchComment = forSearchComment.toLowerCase();

            // поиск текущего банслова и замена его
            for (String banWord : blackList) {
                boolean flag = true;
                int index = 0;
                while (flag) {
                    // поиск банслова
                    index = forSearchComment.indexOf(' ' + banWord + ' ', index);
                    if (index != -1) {
                        for (int i = 1; i <= banWord.length(); i++) {
                            // замена банслова на *
                            cleanComment.setCharAt(index + i - 1, '*');
                        }
                        index++;
                    } else {
                        flag = false;
                    }
                }
            }
            // добавление очищенного комментария для вывода
            cleanedUpComments.add(String.valueOf(cleanComment));
        }

        // вывод
        for (String comment : cleanedUpComments) {
            System.out.println(comment);
        }

    }
}
