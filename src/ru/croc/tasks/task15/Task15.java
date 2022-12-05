package src.ru.croc.tasks.task15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {

        // ввод последовательности чисел, задающих границы возрастных групп
        ArrayList<ageGroup> ageGroups = new ArrayList<ageGroup>();
        ageGroups.add(new ageGroup(-1, new ArrayList<Respondent>()));

        for (String elem : args) {
            int input = Integer.parseInt(elem);
            if (input < 123 && input >= 0) {
                ageGroups.add(new ageGroup(input, new ArrayList<Respondent>()));
            } else {
                System.out.println("Неправильный ввод!");
                return;
            }
        }
        //System.out.println(ageGroups);

        // ввод респондентов

        Scanner in = new Scanner(System.in);

        ArrayList<Respondent> respondents = new ArrayList<>();

        // для себя, ради проверки

        respondents.add(new Respondent("Дьячков Нисон Иринеевич", 88));
        respondents.add(new Respondent("Старостин Ростислав Ермолаевич", 50));
        respondents.add(new Respondent("Иванов Варлам Якунович", 88));
        respondents.add(new Respondent("Ярилова Розалия Трофимовна", 29));
        respondents.add(new Respondent("Соколов Андрей Сергеевич", 15));
        respondents.add(new Respondent("Егоров Алан Петрович", 7));
        respondents.add(new Respondent("Кошельков Захар Брониславович", 105));
        respondents.add(new Respondent("Лаврова Ева Романовна", 26));
        respondents.add(new Respondent("Афанасьев Александр Фёдорович", 29));
        respondents.add(new Respondent("Коновалова Таисия Данииловна", 115));
        respondents.add(new Respondent("Никитина Ева Львовна", 91));
        respondents.add(new Respondent("Тарасов Арсений Никитич", 34));
        respondents.add(new Respondent("Абрамов Артём Германович", 72));
        respondents.add(new Respondent("Федоров Артур Глебович", 48));


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
        for (int indexAgeGroup = ageGroups.size() - 1; indexAgeGroup >= 0; indexAgeGroup--) {
            // добавление всех респондентов в группу
            Iterator<Respondent> it = respondents.iterator();
            while (it.hasNext()) {
                Respondent respondent = it.next();
                if (respondent.getAge()  > ageGroups.get(indexAgeGroup).getAge()) {
                    ageGroups.get(indexAgeGroup).add(respondent);
                    it.remove();
                }
            }

            // вывод респондентов группы
            if (ageGroups.get(indexAgeGroup).size() > 0) {
                ageGroups.get(indexAgeGroup).sort(new ComparatorForRespondent());
                String stringRespondentsInAgeGroup = ageGroups.get(indexAgeGroup).toString().replace("[", "").replace("]", "");
                try {
                    System.out.println((ageGroups.get(indexAgeGroup).getAge() + 1) + "-" + ageGroups.get(indexAgeGroup + 1).getAge() + ": " + stringRespondentsInAgeGroup);
                } catch (Exception e) {
                    System.out.println((ageGroups.get(indexAgeGroup).getAge() + 1) + "+: " + stringRespondentsInAgeGroup);
                }
            }
        }
    }
}
