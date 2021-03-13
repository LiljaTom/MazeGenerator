package mazegenerator.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mazegenerator.domain.Prim;
import mazegenerator.domain.DFS;

public class MazeUi extends Application {

    private int screenHeight;
    private int screenWidth;
    private int height;
    private int width;
    private int startY;
    private int startX;
    private double rectWidth;
    private double rectHeight;

    private Prim prim;
    private DFS dfs;

    private boolean primStatus;
    private boolean primBonusStatus;
    private boolean primDone;

    private boolean dfsStatus;
    private boolean dfsDone;

    @Override
    public void init() {
        this.screenHeight = 800;
        this.screenWidth = 1400;
        this.height = 251;
        this.width = 251;
        this.rectWidth = screenWidth / width;
        this.rectHeight = screenHeight / height;
        
        this.startY = 1;
        this.startX = 1;

        //Inits mazes
        this.prim = new Prim(width, height, this.startY, this.startX);
        this.dfs = new DFS(width, height, this.startY, this.startX);

        //Booleans to control animationtimer
        this.primStatus = false;
        this.primBonusStatus = false;
        this.primDone = false;

        this.dfsStatus = false;
        this.dfsDone = false;

    }

    @Override
    public void start(Stage primaryStage) {

        //Init layout components
        BorderPane layout = new BorderPane();
        VBox vbox = new VBox();
        HBox buttons = new HBox();
        HBox textFields = new HBox();

        buttons.setPadding(new Insets(10, 10, 10, 10));
        buttons.setSpacing(30);
        
        textFields.setPadding(new Insets(10, 10, 10, 10));
        textFields.setSpacing(30);

        //Create GUI components
        Canvas screen = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = screen.getGraphicsContext2D();

        Button startPrim = new Button("Start Prim");
        Button primBonus = new Button("PrimBonus");
        Button primDoneButton = new Button("Create Prim");

        Button startDfs = new Button("Start dfs");
        Button dfsDoneButton = new Button("Create dfs");

        Button newSizeButton = new Button("Set value");
        Button newValuesButton = new Button("Set new start");
        Button clear = new Button("Clear");

        TextField newSize = new TextField("251");
        TextField newXCoordinateLabel = new TextField("1");
        TextField newYCoordinateLabel = new TextField("1");


        buttons.getChildren().addAll(startPrim, primBonus, primDoneButton, startDfs, dfsDoneButton, newSizeButton, newSize, clear);
        textFields.getChildren().addAll(newYCoordinateLabel, newXCoordinateLabel, newValuesButton);

        vbox.getChildren().addAll(textFields, buttons);
        
        layout.setCenter(screen);
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MazeGenerator");
        primaryStage.setFullScreen(true);

        //Init screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, screenWidth, screenHeight);

        //Actions
        // Starts prim step by step
        startPrim.setOnAction((event) -> {
            if (primStatus) {
                startPrim.setText("Start Prim");
                this.primStatus = false;
            } else {
                startPrim.setText("Pause Prim");
                this.primStatus = true;
            }
        });

        //Start dfs step by step
        startDfs.setOnAction((event) -> {
            if (dfsStatus) {
                startDfs.setText("Start Dfs");
                this.dfsStatus = false;
            } else {
                startDfs.setText("Pause Dfs");
                this.dfsStatus = true;
            }
        });

        //Clears screen and resets mazes
        clear.setOnAction((event) -> {
            startPrim.setText("Start Prim");
            startDfs.setText("Start Dfs");
            this.primStatus = false;
            this.dfsStatus = false;

            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, screenWidth, screenHeight);

            this.prim = new Prim(width, height, this.startY, this.startX);
            this.dfs = new DFS(width, height, this.startY, this.startX);
        });

        //Sets bonusfunction to prim
        primBonus.setOnAction((event) -> {
            if (primBonusStatus) {
                this.primBonusStatus = false;
            } else {
                this.primBonusStatus = true;
            }
        });

        primDoneButton.setOnAction((event) -> {
            if (primDone) {
                this.primDone = false;
            } else {
                this.primDone = true;
                prim = new Prim(width, height, this.startY, this.startX);
            }
        });

        dfsDoneButton.setOnAction((event) -> {
            if (dfsDone) {
                this.dfsDone = false;
            } else {
                this.dfsDone = true;
                this.dfs = new DFS(width, height, this.startY, this.startX);
            }
        });

        //Sets new size to the maze
        newSizeButton.setOnAction((event) -> {
            this.height = Integer.valueOf(newSize.getText());
            this.width = Integer.valueOf(newSize.getText());

            this.prim = new Prim(width, height, this.startY, this.startX);
            this.dfs = new DFS(width, height, this.startY, this.startX);

            this.rectWidth = screenWidth / width;
            this.rectHeight = screenHeight / height;

        });
        
        newValuesButton.setOnAction((event) -> {
            this.startY = Integer.valueOf(newYCoordinateLabel.getText());
            this.startX = Integer.valueOf(newXCoordinateLabel.getText());
            
            this.prim = new Prim(width, height, startY, startX);
            this.dfs = new DFS(width, height, startY, startX);
            
            
        });
        

        new AnimationTimer() {
            private long previous = 0;
            private long sleep = 1000000;

            @Override
            public void handle(long present) {

                if ((present - previous) < sleep) {
                    return;
                }

                //Creates prim
                if (primDone) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, screenWidth, screenHeight);

                    prim.createMaze();
                    gc.setFill(Color.WHITE);

                    for (int a = 0; a < width; a++) {
                        for (int b = 0; b < height; b++) {
                            if (prim.getPaths()[b][a]) {
                                gc.fillRect(a * rectWidth, b * rectHeight, rectWidth, rectHeight);
                            }
                        }
                    }
                    primDone = false;
                }

                // Creates prim step by step
                if (primStatus) {
                    gc.setFill(Color.WHITE);

                    for (int i = 0; i < prim.getNewPaths().size(); i++) {
                        int y = prim.getNewPaths().get(i).getY();
                        int x = prim.getNewPaths().get(i).getX();

                        gc.fillRect(x * rectWidth, y * rectHeight, rectWidth, rectHeight);
                    }

                    if (primBonusStatus) {
                        for (int i = 0; i < prim.getNewFrontiers().size(); i++) {
                            int y = prim.getNewFrontiers().get(i).getY();
                            int x = prim.getNewFrontiers().get(i).getX();

                            gc.setFill(Color.GREY);
                            gc.fillRect(x * rectWidth, y * rectHeight, rectWidth, rectHeight);
                        }
                    }

                    prim.buildMaze();
                }
                
                //Creates dfs step by step
                if(dfsStatus) {
                    gc.setFill(Color.WHITE);
                    
                    for (int i = 0; i < dfs.getNewPaths().size(); i++) {
                        int y = dfs.getNewPaths().get(i).getY();
                        int x = dfs.getNewPaths().get(i).getX();

                        gc.fillRect(x * rectWidth, y * rectHeight, rectWidth, rectHeight);
                    }
                    
                    for(int i = 0; i < dfs.getCompletePaths().size(); i++) {
                        int y = dfs.getCompletePaths().get(i).getY();
                        int x = dfs.getCompletePaths().get(i).getX();
                        
                        gc.setFill(Color.RED);
                        gc.fillRect(x * rectWidth, y * rectHeight, rectWidth, rectHeight);
                    }
                    
                    dfs.buildMaze();
                }

                //Creates done dfs
                if (dfsDone) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, screenWidth, screenHeight);

                    dfs.createMaze();
                    gc.setFill(Color.WHITE);
                    for (int a = 0; a < width; a++) {
                        for (int b = 0; b < height; b++) {
                            if (dfs.getPaths()[b][a]) {
                                gc.fillRect(a * rectWidth, b * rectHeight, rectWidth, rectHeight);
                            }
                        }
                    }

                    dfsDone = false;
                }

                previous = present;
            }

        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
