package src.ru.croc.tasks.task4;

public class Annotation {
    private Figure figure;
    private String caption;

    public Annotation(Figure figure, String caption) {
        this.figure = figure;
        this.caption = caption;
    }

    @Override
    public String toString() {
        return figure.toString() + " " + caption;
    }
}
