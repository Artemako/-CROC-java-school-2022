package src.ru.croc.tasks.task15;

import java.util.ArrayList;
import java.util.Objects;

public class Respondent {
    private String fullName;
    private int age;

    public Respondent(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    @Override
    public String toString() {
        return fullName + " (" + age + ")";
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Respondent respondent))
            return false;
        return age == respondent.age && fullName.equals(respondent.fullName);
    }

    public boolean checkingItAmong(ArrayList<Respondent> respondents) {
        for (Respondent respondent : respondents) {
            if (respondent.equals(this)) {
                return false;
            }
        }
        return true;
    }
}
