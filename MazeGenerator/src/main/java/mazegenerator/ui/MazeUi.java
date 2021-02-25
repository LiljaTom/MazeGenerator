package mazegenerator.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mazegenerator.domain.Kruskal;
import mazegenerator.domain.Prim;

public class MazeUi extends Application {

    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;
    private int n;
    private Prim prim;
    private Kruskal kruskal;

    @Override
    public void init() {
        this.n = 55;
        this.prim = new Prim(n, n, 1, 1);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane screen = new Pane();
        screen.setPrefSize(HEIGHT, WIDTH);

        double width = 10;
        Rectangle[][] rt = new Rectangle[n][n];

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                rt[y][x] = new Rectangle();
                rt[y][x].setX(y * width);
                rt[y][x].setY(x * width);
                rt[y][x].setWidth(width);
                rt[y][x].setHeight(width);
                rt[y][x].setStroke(Color.BLACK);

                if (prim.getPaths()[y][x]) {
                    rt[y][x].setFill(Color.WHITE);
                } else {
                    rt[y][x].setFill(Color.BLACK);
                }

                screen.getChildren().add(rt[y][x]);
            }
        }

        new AnimationTimer() {
            private long previous = 0;
            private long sleep = 100 * 1000000;

            @Override
            public void handle(long present) {
                if ((present - previous) < sleep) {
                    return;
                }

                for (int i = 0; i < prim.getNewPaths().size(); i++) {
                    int y = prim.getNewPaths().get(i).getY();
                    int x = prim.getNewPaths().get(i).getX();

                    rt[y][x].setFill(Color.WHITE);
                }
                
                for(int i = 0; i < prim.getNewFrontiers().size(); i++) {
                    int y = prim.getNewFrontiers().get(i).getY();
                    int x = prim.getNewFrontiers().get(i).getX();
                    
                    rt[y][x].setFill(Color.RED);
                }

                prim.buildMaze();
                

                previous = present;
            }

        }.start();

        Scene scene = new Scene(screen);

        primaryStage.setTitle("Maze");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
