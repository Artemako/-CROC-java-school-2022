package src.ru.croc.tasks.task16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // все таксисты
        Taxi person0 = new Taxi("Мельников Степан Михайлович", 10.0, 10.0, "Эконом", new String[]{"Детское кресло", "Домашнее животное"});
        Taxi person1 = new Taxi("Клюев Степан Даниилович", 50.0, 20.0, "Комфорт", new String[]{"Детское кресло", "Инвалидное кресло"});
        Taxi person2 = new Taxi("Мальцев Кирилл Андреевич", 40.0, 30.0, "Комфорт", new String[]{});
        Taxi person3 = new Taxi("Русаков Егор Георгиевич", 70.0, 40.0, "Комфорт", new String[]{"Детское кресло"});
        Taxi person4 = new Taxi("Родионов Марк Родионович", 80.0, 20.0, "Эконом", new String[]{"Детское кресло",});
        Taxi person6 = new Taxi("Нефедов Арсен Викторович", 70.0, 50.0, "Эконом", new String[]{"Детское кресло"});
        Taxi person7 = new Taxi("Алексеев Алексей Иванович", 30.0, 50.0, "Эконом", new String[]{});
        Taxi person8 = new Taxi("Казаков Артур Михайлович", 50.0, 50.0, "Комфорт", new String[]{"Домашнее животное"});
        Taxi person9 = new Taxi("Мельников Павел Степанович", 60.0, 60.0, "Комфорт", new String[]{"Домашнее животное"});
        Taxi person10 = new Taxi("Данилов Дмитрий Ильич", 70.0, 70.0, "Комфорт", new String[]{"Детское кресло", "Домашнее животное", "Инвалидное кресло"});

        // ввод (если массив данных, то через запятую C ПРОБЕЛОМ)
        String inputString = in.nextLine();
        double latitude = Double.parseDouble(inputString.split(", ")[0]);
        double longitude = Double.parseDouble(inputString.split(", ")[1]);

        String comfortClass = in.nextLine();

        // если особых пожеланий нет, то пустая строка (если массив данных, то через запятую C ПРОБЕЛОМ)
        inputString = in.nextLine();
        ArrayList<String> specialRequests = new ArrayList<>(Arrays.asList(inputString.split(", ")));

        Taxi.findNearestForThisPerson(latitude, longitude, comfortClass, specialRequests);
    }
}
