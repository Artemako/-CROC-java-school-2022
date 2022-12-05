package src.ru.croc.tasks.task15;

import java.util.Comparator;

public class ComparatorForRespondent implements Comparator<Respondent> {

    @Override
    public int compare(Respondent respondent1, Respondent respondent2) {
        if (respondent1.getAge() == respondent2.getAge())
            return (respondent1.getFullName().compareTo(respondent2.getFullName()));
        return (-Integer.compare(respondent1.getAge(), respondent2.getAge()));
    }
}
