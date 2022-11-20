package src.ru.croc.tasks.task5;

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

    @Override
    public String toString() {
        System.out.println("Start AnnotatedImage.");
        int i = 1;
        for (Annotation annotation : annotations) {
            System.out.println(i + ") " + annotation);
            i++;
        }
        return "End AnnotatedImage.";
    }

    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().checkByPoint(x, y)) {
                return annotation;
            }
        }
        return null;
    }

    public Annotation findByLabel(String label) {
        for (Annotation annotation : annotations) {
            if (annotation.checkByCaption(label)) {
                return annotation;
            }
        }
        return null;
    }


}
