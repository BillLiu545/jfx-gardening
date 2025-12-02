
public class Plant implements Comparable<Plant>
{
    private String name;
    private boolean watered, fertilized;
    public Plant (String name, boolean watered, boolean fertilized)
    {
        this.name = name;
        this.watered = watered;
        this.fertilized = fertilized;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean isWatered()
    {
        return watered;
    }
    
    public boolean isFertilized()
    {
        return fertilized;
    }
    
    public void water()
    {
        watered = true;
    }
    
    public void fertilize()
    {
        fertilized = true;
    }
    
    public int compareTo(Plant other)
    {
        if (name.equals(other.name))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
    public String toString()
    {
         return name + " - " + "Watered: " + watered + "/Fertilized: " + fertilized;
    }
}
