package fosalgo;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class CraftingCode_048 extends Application implements Runnable {

    //Loop Parameters
    private final static int MAX_FPS = 60;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    //Thread
    private Thread thread;
    private volatile boolean running = true;

    //Canvas
    Canvas canvas = null;

    //KEYBOARD HANDLER
    ArrayList<String> inputKeyboard = new ArrayList<String>();

    //Lingkaran 1
    float cx1 = 100;
    float cy1 = 300;
    float r1 = 40;
    Color c1 = Color.DEEPSKYBLUE;

    //Lingkaran 2
    float cx2 = 400;
    float cy2 = 300;
    float r2 = 50;
    Color c2 = Color.valueOf("#d35400");

    //Collision
    boolean isCollide = false;
    
    public CraftingCode_048() {
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
        //lingkaran 1
        if (inputKeyboard.contains("W")) {
            cy1 -= 2;
        } else if (inputKeyboard.contains("S")) {
            cy1 += 2;
        }

        if (inputKeyboard.contains("A")) {
            cx1 -= 2;
        } else if (inputKeyboard.contains("D")) {
            cx1 += 2;
        }

        //lingkaran 2
        if (inputKeyboard.contains("UP")) {
            cy2 -= 2;
        } else if (inputKeyboard.contains("DOWN")) {
            cy2 += 2;
        }

        if (inputKeyboard.contains("LEFT")) {
            cx2 -= 2;
        } else if (inputKeyboard.contains("RIGHT")) {
            cx2 += 2;
        }
        
        collisionDetection();
    }
    
    /**
     * Method untuk Deteksi Tumbukan
     */
    private void collisionDetection() {
        Circle circle1 = new Circle(cx1, cy1, r1);
        Circle circle2 = new Circle(cx2, cy2, r2);
        Shape shape = Path.intersect(circle1, circle2);
        isCollide = false;
        if (shape.getLayoutBounds().getWidth() != -1
                || shape.getLayoutBounds().getHeight() != -1) {
            isCollide = true;
        }
    }

    //LOOP
    private void draw() {
        try {
            if (canvas != null) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Color bgColor = Color.WHITESMOKE;
                if(isCollide){
                	bgColor = Color.valueOf("#f1c40f");
                }
                gc.setFill(bgColor);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                //Lingkaran1 
                gc.setFill(c1);
                gc.fillOval(cx1-r1, cy1-r1, 2.0f*r1, 2.0f*r1);
                
                //Lingkaran2 
                gc.setFill(c2);
                gc.fillOval(cx2-r2, cy2-r2, 2.0f*r2, 2.0f*r2);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        //INISIALISASI canvas dan variabel gerak jatuh bebas
        canvas = new Canvas(480, 640);
        root.getChildren().add(canvas);

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

            }
        }
        );

        primaryStage.setTitle("Crafting Code");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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

}
