package src.ru.croc.tasks.task15;

import java.util.ArrayList;

public class ageGroup {
    private final int lowerBorder;
    private ArrayList<Respondent> respondents = new ArrayList<>();

    @Override
    public String toString() {
        return this.respondents.toString();
    }

    public ageGroup(int lowerBorder, ArrayList<Respondent> respondents) {
        this.lowerBorder = lowerBorder;
        this.respondents = respondents;
    }

    public int getAge() {
        return this.lowerBorder;
    }

    public void add(Respondent respondent) {
        this.respondents.add(respondent);
    }

    public int size() {
        return this.respondents.size();
    }

    public void sort(ComparatorForRespondent comparatorForRespondent) {
        this.respondents.sort(new ComparatorForRespondent());
    }
}
