import javafx.collections.*;
import java.util.*;
import javafx.scene.control.*;
public class Garden extends TreeMap<Integer, Plant>
{
    private String[] plantList =
    {
        "Carrots",
        "Strawberries",
        "Blueberries",
        "Spinach",
        "Watermelon",
        "Tomatoes",
        "Eggplant",
        "Lettuce",
        "Potatoes",
        "Peppers",
        "Peas",
        "Cucumbers",
        "Squash"
    };
    public Plant plant_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name of Plant");
        dialog.setHeaderText("Name of Plant");
        dialog.setContentText("Name of plant to place in garden:");
        Optional<String> opt = dialog.showAndWait();
        String name = opt.get();
        Plant planted = plant(name);
        return planted;
    }
    
    private boolean containsPlant(String name)
    {
        for (int i = 0; i < plantList.length; i++)
        {
            if (plantList[i].equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    public Plant plant(String name)
    {
        int plant_ind = size();
        Plant planted = null;
        if (containsPlant(name) == true) {
            planted = new Plant(name, false, false);
            put(plant_ind + 1, planted);
        }
        return planted;
    }
    
    public Plant water_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name of Plant");
        dialog.setHeaderText("Name of Plant");
        dialog.setContentText("Name of plant to be watered:");
        Optional<String> opt = dialog.showAndWait();
        String name = opt.get();
        return waterPlant(name);
    }
    
    public Plant waterPlant(String name)
    {
        Collection<Plant> plantSet = values();
        Iterator<Plant> iter = plantSet.iterator();
        Plant watered = null;
        while (iter.hasNext())
        {
            Plant next = iter.next();
            if (next.getName().equals(name))
            {
                next.water();
                watered = next;
                break;
            }
        }
        return watered;
    }
    
    public Plant fertilize_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name of Plant");
        dialog.setHeaderText("Name of Plant");
        dialog.setContentText("Name of plant to be fertilized:");
        Optional<String> opt = dialog.showAndWait();
        String name = opt.get();
        return fertilizePlant(name);
    }
    
    public Plant fertilizePlant(String name)
    {
        Collection<Plant> plantSet = values();
        Iterator<Plant> iter = plantSet.iterator();
        Plant fertilized = null;
        while (iter.hasNext())
        {
            Plant next = iter.next();
            if (next.getName().equals(name))
            {
                next.fertilize();
                fertilized = next;
                break;
            }
        }
        return fertilized;
    }
    
    public Plant harvest_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name of Plant");
        dialog.setHeaderText("Name of Plant");
        dialog.setContentText("Name of plant to be harvested:");
        Optional<String> opt = dialog.showAndWait();
        String name = opt.get();
        return harvest(name);
    }
    
    public Plant harvest(String name)
    {
        Collection<Plant> plantSet = values();
        Iterator<Plant> iter = plantSet.iterator();
        Plant harvested = null;
        int harvest_ind = 1;
        while (iter.hasNext())
        {
            Plant next = iter.next();
            if (next.getName().equals(name))
            {
                if (next.isWatered() == true && next.isFertilized())
                {
                    harvested = next;
                    remove(harvest_ind);
                    break;
                }
            }
            harvest_ind++;
        }
        return harvested;
    }
    
    public ObservableList<Plant> toObservableList()
    {
        Collection<Plant> plantSet = values();
        ObservableList<Plant> plantList = FXCollections.observableArrayList();
        Iterator<Plant> iter = plantSet.iterator();
        while (iter.hasNext())
        {
            Plant next = iter.next();
            plantList.add(next);
        }
        return plantList;
    }
    
    public static void main(String[] args)
    {
        Garden g = new Garden();
        System.out.println(g.plant("Carrots"));
        System.out.println(g.plant("Bananas"));
        System.out.println(g.plant("Spinach"));
        System.out.println(g.harvest("Carrots"));
        System.out.println(g.waterPlant("Carrots"));
        System.out.println(g.fertilizePlant("Carrots"));
        System.out.println(g.waterPlant("Bananas"));
        System.out.println(g.fertilizePlant("Bananas"));
        System.out.println(g.harvest("Carrots"));
        System.out.println(g.harvest("Spinach"));
    }
}
