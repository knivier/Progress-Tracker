public class entry {
    private int days; //of the current month
    private int weight; //pounds
    private int height; //inches
    private int age; //years

    public entry(int adays, int aweight, int aheight, int aage){
        days = adays;
        weight = aweight;
        height = aheight;
        age = aage;
    }

    public int getDays(){
        return days;
    }

    public int getWeight(){
        return weight;
    }

    public int getHeight(){
        return height;
    }

    public int getAge(){
        return age;
    }

    public double getBMI(){
        double bmi = (weight / Math.pow(height, 2)) * 703;
        return bmi;
    }
}
