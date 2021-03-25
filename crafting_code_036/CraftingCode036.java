package fosalgo;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CraftingCode036 extends Application implements Runnable {

    //Loop Parameters
    private final static int MAX_FPS = 1;
    private final static int MAX_FRAME_SKIPS = 10;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    //Thread
    private Thread thread;
    private volatile boolean running = true;

    //Canvas
    Canvas canvas = null;

    //KEYBOARD HANDLER
    ArrayList<String> inputKeyboard = new ArrayList<String>();

    //variabel untuk komponen-komponen canvas
    ArrayList<double[]> titik = new ArrayList<>();//titik[0] = x; titik[1]=y

    public CraftingCode036() {
        resume();
    }

    //THREAD
    private void resume() {
        reset();
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    //THREAD
    private void pause() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //LOOP
    private void reset() {

    }

    //LOOP
    private void update() {

    }

    //LOOP
    private void draw() {
        try {
            if (canvas != null) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                //Gambar Edge
                for (int i = 0; i < titik.size(); i++) {
                    double[] p = titik.get(i);
                    double x = p[0];
                    double y = p[1];
                    for (int j = 0; j < i; j++) {
                        double[] pj = titik.get(j);
                        double xj = pj[0];
                        double yj = pj[1];
                        gc.setStroke(Color.valueOf("#34495e"));
                        gc.strokeLine(x, y, xj, yj);
                    }
                }

                //Gambar Vertex
                for (int i = 0; i < titik.size(); i++) {
                    double[] p = titik.get(i);
                    double x = p[0];
                    double y = p[1];
                    gc.setFill(Color.valueOf("#f1c40f"));
                    double r = 10;
                    gc.fillOval(x - r, y - r, 2 * r, 2 * r);
                    gc.setStroke(Color.valueOf("#9b59b6"));
                    gc.strokeOval(x - r, y - r, 2 * r, 2 * r);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int framesSkipped;
        //LOOP WHILE running = true; 
        while (running) {
            try {
                synchronized (this) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;
                    update();
                    draw();
                }
                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int) (FRAME_PERIOD - timeDiff);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                }
                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                    update();
                    sleepTime += FRAME_PERIOD;
                    framesSkipped++;
                }
            } finally {

            }
        }
    }

    public void setHandling(Scene scene) {
        //HANDLING KEYBOARD EVENT
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!inputKeyboard.contains(code)) {
                    inputKeyboard.add(code);
                    System.out.println(code);
                }
            }
        }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                inputKeyboard.remove(code);
            }
        }
        );

        //HANDLING MOUSE EVENT
        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                double x = e.getX();
                double y = e.getY();
                double[] p = {x, y};
                titik.add(p);
            }
        }
        );
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        setHandling(scene);

        canvas = new Canvas(1000, 700);
        root.getChildren().add(canvas);

        primaryStage.setTitle("FOSALGO - Indonesia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
