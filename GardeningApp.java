
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.application.*;

public class GardeningApp extends Application
{
    private final Garden garden = new Garden();
    private final ObservableList<Plant> oList = garden.toObservableList();
    public void start(Stage mainStage)
    {
        // Main Layout and Scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600, 500);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Gardening App");
        
        // Tableview to display garden contents
        TableView<Plant> table = new TableView();
        table.setStyle("-fx-font-size: 15");
        mainLayout.getChildren().add(table);
        table.setItems(oList);
        
        // Plant name column
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(120);
        table.getColumns().add(nameCol);
        
        // Watered column
        TableColumn wateredCol = new TableColumn("Watered?");
        wateredCol.setCellValueFactory(new PropertyValueFactory("watered"));
        wateredCol.setMinWidth(120);
        table.getColumns().add(wateredCol);
        
        // Fertilized column
        TableColumn fetilizedCol = new TableColumn("Fertilized?");
        fetilizedCol.setCellValueFactory(new PropertyValueFactory("fertilized"));
        fetilizedCol.setMinWidth(120);
        table.getColumns().add(fetilizedCol);
        
        // remove extra columns/rows
        table.setMaxWidth(360);
        table.setMaxHeight(490);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu Item - add a new plant
        MenuItem plantItem = new MenuItem("Plant");
        plantItem.setOnAction((event)->
        {
            Plant planted = garden.plant_input();
            if (planted == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Plant Not Found");
                alert.setHeaderText("Plant Not Found");
                alert.setContentText("Plant name not valid to be planted.");
                alert.showAndWait();
            }
            else
            {
                oList.add(planted);
                table.refresh();
            }
        });
        fileMenu.getItems().add(plantItem);
        
        // Menu Item - water a new plant
        MenuItem waterItem = new MenuItem("Water a Plant");
        waterItem.setOnAction((event)->
        {
            Plant watered = garden.water_input();
            if (watered == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Plant Not Found");
                alert.setHeaderText("Plant Not Found");
                alert.setContentText("Plant name not available to water.");
                alert.showAndWait();
            }
            else
            {
                Iterator<Plant> iter = oList.iterator();
                while (iter.hasNext())
                {
                    Plant next = iter.next();
                    if (watered.getName().equals(next.getName()))
                    {
                        next.water();
                    }
                }
                table.refresh();
            }
        });
        fileMenu.getItems().add(waterItem);
        
        // Menu Item - water a new plant
        MenuItem fertilizeItem = new MenuItem("Fertilize a Plant");
        fertilizeItem.setOnAction((event)->
        {
            Plant fertilized = garden.fertilize_input();
            if (fertilized == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Plant Not Found");
                alert.setHeaderText("Plant Not Found");
                alert.setContentText("Plant name not available to fertilize.");
                alert.showAndWait();
            }
            else
            {
                Iterator<Plant> iter = oList.iterator();
                while (iter.hasNext())
                {
                    Plant next = iter.next();
                    if (fertilized.getName().equals(next.getName()))
                    {
                        next.fertilize();
                    }
                }
                table.refresh();
            }
        });
        fileMenu.getItems().add(fertilizeItem);
        
        // Menu Item - harvest a new plant
        MenuItem harvestItem = new MenuItem("Harvest a Plant");
        harvestItem.setOnAction((event)->
        {
            Plant harvested = garden.harvest_input();
            if (harvested == null)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Plant Not Found");
                alert.setHeaderText("Plant Not Found");
                alert.setContentText("Plant name not available to harvest.");
                alert.showAndWait();
            }
            else
            {
                oList.remove(harvested);
                table.refresh();
            }
        });
        fileMenu.getItems().add(harvestItem);
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
            System.exit(0);
        }));
        fileMenu.getItems().add(quitItem);
        
        mainStage.show();
    }
}
