package src.ru.croc.tasks.task15;

import java.util.Comparator;

public class ComparatorForRespondent implements Comparator<Respondent> {

    @Override
    public int compare(Respondent respondent1, Respondent respondent2) {
        if (respondent1.getAge() == respondent2.getAge())
            for (int i = 0; i < respondent1.getFullName().length(); i++) {
                if (respondent1.getFullName().charAt(i) != respondent2.getFullName().charAt(i))
                    return (Character.compare(respondent1.getFullName().charAt(i), respondent2.getFullName().charAt(i)));
            }
        else {
            // минус для убывания
            return (- Integer.compare(respondent1.getAge(), respondent2.getAge()));
        }
        return 0;
    }
}
