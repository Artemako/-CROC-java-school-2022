package src.ru.croc.tasks.task15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // ввод последовательности чисел, задающих границы возрастных групп
        ArrayList<Integer> ageGroups = new ArrayList<Integer>();
        ageGroups.add(-1);

        String inputString = in.nextLine();
        for (String elem : inputString.split(" ")) {
            int input = Integer.parseInt(elem);
            if (input < 123 && input >= 0) {
                ageGroups.add(input);
            } else {
                System.out.println("Неправильный ввод!");
                return;
            }
        }
        //System.out.println(ageGroups);

        // ввод респондентов
        ArrayList<Respondent> respondents = new ArrayList<>();

        // для себя, ради проверки
        /*
        respondents.add(new Respondent("Дьячков Нисон Иринеевич", 88));
        respondents.add(new Respondent("Старостин Ростислав Ермолаевич", 50));
        respondents.add(new Respondent("Иванов Варлам Якунович", 88));
        respondents.add(new Respondent("Ярилова Розалия Трофимовна", 29));
        respondents.add(new Respondent("Соколов Андрей Сергеевич", 15));
        respondents.add(new Respondent("Егоров Алан Петрович", 7));
        respondents.add(new Respondent("Кошельков Захар Брониславович", 105));
        */

        String fullName = "";
        int age = 0;

        boolean globalInputFlag = true;

        while (globalInputFlag) {
            while (true) {

                String input = in.next();
                if (input.equals("END")) {
                    globalInputFlag = false;
                    break;
                }

                fullName = input + " " + in.next() + " " + in.next().replace(",", "");
                age = in.nextInt();
                if (!(age < 123 && age >= 0)) {
                    System.out.println("Неправильный ввод возраста! Введите респондента заново!");
                    break;
                }

                Respondent newRespondent = new Respondent(fullName, age);

                if (!newRespondent.checkingItAmong(respondents)) {
                    System.out.println("У вас уже есть такой респондент с таким же ФИО и возрастом!");
                    break;
                } else {
                    respondents.add(newRespondent);
                }
            }
        }

        // обработка и вывод респондентов
        for (int i = ageGroups.size() - 1; i >= 0; i--) {
            // добавление всех респондентов в группу
            ArrayList<Respondent> respondentsInAgeGroup = new ArrayList<>();
            Iterator<Respondent> it = respondents.iterator();
            while (it.hasNext()) {
                Respondent respondent = it.next();
                if (respondent.getAge() > ageGroups.get(i)) {
                    respondentsInAgeGroup.add(respondent);
                    it.remove();
                }
            }

            // вывод респондентов группы
            if (respondentsInAgeGroup.size() > 0) {
                respondentsInAgeGroup.sort(new ComparatorForRespondent());
                String stringRespondentsInAgeGroup = respondentsInAgeGroup.toString().replace("[", "").replace("]", "");
                try {
                    System.out.println((ageGroups.get(i) + 1) + "-" + ageGroups.get(i + 1) + ": " + stringRespondentsInAgeGroup);
                } catch (Exception e) {
                    System.out.println((ageGroups.get(i) + 1) + "+: " + stringRespondentsInAgeGroup);
                }
            }
        }
    }
}
