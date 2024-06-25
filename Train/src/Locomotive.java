public class Locomotive extends Wagon{
    public Locomotive(int remaining, Train train)
    {
        super(null, remaining, train);
        this.addButin(new Butin(ButinType.Magot, this));
    }
}
