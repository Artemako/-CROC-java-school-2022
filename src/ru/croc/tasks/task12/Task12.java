package src.ru.croc.tasks.task12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task12 {

    public static void main(String[] args) {
        // комментарии
        ArrayList<String> comments = new ArrayList<>();
        comments.add("Далеко-далеко за словесными горами в стране гласных и согласных живут рыбные тексты. Вдали от всех живут они в буквенных домах на берегу Семантика большого языкового океана.");
        comments.add("Душа моя озарена неземной радостью, как эти чудесные весенние утра, которыми я наслаждаюсь от всего сердца. Я совсем один и блаженствую в здешнем краю, словно созданном для таких, как я.");
        comments.add("Проснувшись однажды утром после беспокойного сна, Грегор Замза обнаружил, что он у себя в постели превратился в страшное насекомое.");

        // черный список (бан-слова с маленькой буквы)
        Set<String> blackList = new HashSet<>();
        blackList.add("далеко");
        blackList.add("а");
        blackList.add("что");
        blackList.add("буквенных");
        blackList.add("он");
        blackList.add("на");
        blackList.add("моя");

        // запуск фильтра
        CommentsFilter myCommentsFilter = new CommentsFilter();
        myCommentsFilter.filterComments(comments, blackList);


    }

}
