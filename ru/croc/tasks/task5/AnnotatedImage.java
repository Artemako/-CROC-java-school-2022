package ru.croc.tasks.task5;

public class AnnotatedImage {
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public void printAnnotations() {
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().checkByPoint(x, y)) {
                return annotation;
            }
        }
        System.out.println("No results.");
        return new Annotation(new Figure(), "");
    }

    public Annotation findByLabel(String label) {
        for (Annotation annotation : annotations) {
            if (annotation.checkByCaption(label)) {
                return annotation;
            }
        }
        System.out.println("No results.");
        return new Annotation(new Figure(), "");
    }


}
