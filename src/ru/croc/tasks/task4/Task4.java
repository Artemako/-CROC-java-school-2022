package src.ru.croc.tasks.task4;

public class Task4 {
    public static void main(String[] args) {
        Annotation[] annotations = new Annotation[3];

        Figure figure0 = new Circle(-5, -7, 5);
        Annotation annotation0 = new Annotation(figure0, "Something");
        annotations[0] = annotation0;

        Figure figure1 = new Circle(5, 7, 3);
        Annotation annotation1 = new Annotation(figure1, "This is Circle");
        annotations[1] = annotation1;

        Figure figure2 = new Rectangle(-10, -5, 3, 5);
        Annotation annotation2 = new Annotation(figure2, "This is Rectangle");
        annotations[2] = annotation2;

        AnnotatedImage annotatedImage = new AnnotatedImage("Path/alsoPath", annotations);
        System.out.println(annotatedImage);
    }
}
