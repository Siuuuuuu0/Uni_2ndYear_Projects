import java.util.ArrayList;
import java.util.Random;
public class Marshall extends Personne{

    private final double NERVOSITE_MARSHALL;

    public Marshall(Wagon wagon, Train train)
    {
        super(wagon, false, train);
        NERVOSITE_MARSHALL = 0.3;
    }

    public void move()
    {
        Random random = new Random();
        int centre = Train.NB_WAGONS/2;
        int movDirection = random.nextBoolean() ? 1 : -1;
        double probabilityCheck = random.nextDouble();
        if(probabilityCheck <= NERVOSITE_MARSHALL) {
            if (this.wagon.idx < centre) {
                this.advances();
            } else if (this.wagon.idx > centre) {
                this.returns();
            } else {
                switch (movDirection) {
                    case (-1): this.returns()
                        ;
                        break;
                    case (1): this.advances()
                        ;
                        break;
                }
            }
        }
    }

    public void scare()
    {
        Random random = new Random();
        ArrayList<Integer> idxBandit = new ArrayList<>();
        for (Personne b : this.train.personnes)
        {
            // sorting only the indexes of Personnes(Bandits) from Marhall's wagon
            if(b.wagon == this.wagon && !b.roof && !(b instanceof Marshall))
            {
                idxBandit.add(train.personnes.indexOf(b));
            }
        }
        if(!idxBandit.isEmpty())
        {
            int randBanditIdx = random.nextInt(idxBandit.size());
            //casting Personne to Bandit to get access to collectedButins (always Bandit type)
            Bandit chosenBandit = (Bandit) train.personnes.get(idxBandit.get(randBanditIdx));
            chosenBandit.lacher();
            chosenBandit.roof = true;
            System.out.println("scared a bandit " + chosenBandit.name);

            // try {int randButin = random.nextInt(chosenBandit.collectedButins.size());
            //     this.wagon.butins.add(chosenBandit.collectedButins.get(randButin));
            //     chosenBandit.collectedButins.remove(randButin);} 
            //     catch(Exception e){}
            //train.personnes.set(idxBandit.get(randBanditIdx), chosenBandit);
            
            
        } else {
            System.out.println("no bandits to scare");
        }
    }
}




