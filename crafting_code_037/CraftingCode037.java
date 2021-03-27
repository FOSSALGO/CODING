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

public class CraftingCode037 extends Application {

    //KEYBOARD HANDLER
    ArrayList<String> inputKeyboard = new ArrayList<String>();

    //Canvas
    Canvas canvas = null;
    double canvasWidth = 1280;
    double canvasHeight = 720;

    //TRANSFORM
    double sceneX, sceneY;
    double translateX, translateY;

    //variabel untuk komponen-komponen canvas
    ArrayList<double[]> titik = new ArrayList<>();//titik[0] = x; titik[1]=y 

    //DRAW
    private void draw() {
        try {
            if (canvas != null) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.valueOf("#ecf0f1"));
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                gc.setStroke(Color.valueOf("#c0392b"));
                gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

    //HANDLER
    public void setSceneHandling(Scene scene) {
        //HANDLING KEYBOARD EVENT
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!inputKeyboard.contains(code)) {
                    inputKeyboard.add(code);
                    System.out.println(code);
                }
            }
        }
        );

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                inputKeyboard.remove(code);
            }
        }
        );
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (inputKeyboard.contains("CONTROL")) {
                    double x = t.getSceneX() - canvas.getTranslateX() - canvas.getLayoutX();
                    double y = t.getSceneY() - canvas.getTranslateY() - canvas.getLayoutY();
                    double[] p = {x, y};
                    titik.add(p);
                    draw();
                }
            }
        });

    }
    
    public void setCanvasHandling(Canvas canvas){
         canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (!inputKeyboard.contains("CONTROL")) {
                    sceneX = t.getSceneX();
                    sceneY = t.getSceneY();
                    translateX = ((Canvas) (t.getSource())).getTranslateX();
                    translateY = ((Canvas) (t.getSource())).getTranslateY();
                }
            }
        });
         
         canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (!inputKeyboard.contains("CONTROL")) {
                    double offsetX = t.getSceneX() - sceneX;
                    double offsetY = t.getSceneY() - sceneY;
                    double newTranslateX = translateX + offsetX;
                    double newTranslateY = translateY + offsetY;
                    ((Canvas) (t.getSource())).setTranslateX(newTranslateX);
                    ((Canvas) (t.getSource())).setTranslateY(newTranslateY);
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Scene scene = new Scene(root);
        setSceneHandling(scene);

        canvas = new Canvas(canvasWidth, canvasHeight);
        setCanvasHandling(canvas);
        draw();
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
