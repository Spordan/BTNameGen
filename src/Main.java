
public class Main {
    public static void main(String[] args) {
        System.out.println("@ NAME GENERATOR @\n");

        BTNameGen bt = new BTNameGen();
        System.out.println(bt.GenerateName());
        System.out.println("\n\n");
        System.out.println(bt.GenerateShip());
    }
}
