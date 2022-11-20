package src.ru.croc.tasks.task5;

public class Annotation {
    private Figure figure;
    private String caption;

    public Annotation(Figure figure, String caption) {
        this.figure = figure;
        this.caption = caption;
    }

    public Figure getFigure() {
        return figure;
    }

    public boolean checkByCaption(String label){
        return caption.contains(label);
    }

    @Override
    public String toString() {
        return figure.toString() + " " + caption;
    }
}
