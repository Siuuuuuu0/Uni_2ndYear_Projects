import java.util.ArrayList;
import java.util.Random;

public class Bandit extends Personne{
    private static int count;
    public String name; 
    public ArrayList<Butin> collectedButins;

    public Bandit(Wagon wagon, boolean roof, Train train){
        
        super(wagon, roof, train);
        idx = count;
        collectedButins = new ArrayList<>(); 
        count++; 
        name = new String("NOM_BANDIT_" + count);
        
    }

    public void braquer() // or maybe boolean
    {
        if(!this.wagon.butins.isEmpty())
        {
            Random rand = new Random();
            int randNum = rand.nextInt(this.wagon.butins.size());
            if(this.roof && this.wagon.butins.get(randNum).isOnRoof)
            {
                Butin tmp = this.wagon.butins.get(randNum);
                tmp.isOnRoof = false;
                this.collectedButins.add(tmp);
            }
            else if(!this.roof && !this.wagon.butins.get(randNum).isOnRoof)
            {
                this.collectedButins.add(this.wagon.butins.get(randNum));
            }
            else if(this.roof && !this.wagon.butins.get(randNum).isOnRoof)
            {
                int cnt = 0;
                while(!this.wagon.butins.get(randNum).isOnRoof && cnt < 20){
                    randNum = rand.nextInt(this.wagon.butins.size());
                    cnt++;
                }
                if(cnt >= 20)
                {
                    System.out.println("Nothing to collect here");
                    return;
                }
                Butin tmp = this.wagon.butins.get(randNum);
                tmp.isOnRoof = false;
                this.collectedButins.add(tmp);
            }
            else if(!this.roof && this.wagon.butins.get(randNum).isOnRoof)
            {
                int cnt = 0;
                while(this.wagon.butins.get(randNum).isOnRoof && cnt < 20){
                    randNum = rand.nextInt(this.wagon.butins.size());
                    cnt++;
                }
                if(cnt >= 20)
                {
                    System.out.println("Nothing to collect here");
                    return;
                }
                this.collectedButins.add(this.wagon.butins.get(randNum));
            }
            butinsValue+=wagon.butins.get(randNum).Price; 
            this.wagon.butins.remove(randNum);
            System.out.println("collected a treasure");
            System.out.println(collectedButins.size()+ ", name: " + this.name);
            Train.totalButins--;
        } else
            System.out.println("Nothing to collect here");
    }
    public void lacher()
    {
        if(!this.collectedButins.isEmpty())
        {
            int randNum = new Random().nextInt(this.collectedButins.size());
            if(this.roof)
            {
                Butin tmp = this.collectedButins.get(randNum);
                tmp.isOnRoof = true;
                tmp.wagon=this.wagon;
                // System.out.println(wagon.butins.size());
                this.wagon.addButin(tmp);
                // System.out.println(wagon.butins.size());

            }
            else
            {
                // System.out.println(wagon.butins.size());
                Butin tmp = this.collectedButins.get(randNum);
                tmp.isOnRoof = false;
                tmp.wagon=this.wagon;
                this.wagon.addButin(tmp);
                // System.out.println(wagon.butins.size());
            }
            butinsValue-=collectedButins.get(randNum).Price; 
            this.collectedButins.remove(randNum);
            System.out.println("threw a treasure");
            System.out.println(collectedButins.size()+ ", name: " + this.name);
            Train.totalButins++;
        } else
            System.out.println("this bandit's pockets are empty");
    }

    public void shoot(Direction direction)
    {
        Random random = new Random();
        ArrayList<Integer> idxBandit = new ArrayList<>();
        if(this.NB_BALLES != 0) {
            switch (direction) {
                case Up:
                    if (!this.roof) {
                        for (Personne b : train.personnes) {
                            if (b.wagon == this.wagon && b.roof && !(b instanceof Marshall)) {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    } else {
                        System.out.println("shooting in the sky");
                    }
                    break;

                case Down:
                    if (this.roof) {
                        for (Personne b : train.personnes) {
                            if (b.wagon == this.wagon && !b.roof && !(b instanceof Marshall)) {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    } else {
                        System.out.println("shooting the floor");
                    }
                    break;
                case Backwards:
                    if (this.roof)
                    {
                        for (Personne b : train.personnes)
                        {
                            if (b.wagon == this.wagon.next && b.roof && !(b instanceof Marshall))
                            {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    } else if (!this.roof)
                    {
                        for (Personne b : train.personnes)
                        {
                            if (b.wagon == this.wagon.next && !b.roof && !(b instanceof Marshall))
                            {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    }
                    break;
                case Forward:
                    if (this.roof)
                    {
                        for (Personne b : train.personnes)
                        {
                            if (b.wagon == this.wagon.prev && b.roof && !(b instanceof Marshall))
                            {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    } else if (!this.roof)
                    {
                        for (Personne b : train.personnes)
                        {
                            if (b.wagon == this.wagon.prev && !b.roof && !(b instanceof Marshall))
                            {
                                idxBandit.add(train.personnes.indexOf(b));
                            }
                        }
                    }
                    break;
            }
            if (!idxBandit.isEmpty()) {
                int randBanditIdx = random.nextInt(idxBandit.size());
                Bandit chosenBandit = (Bandit) train.personnes.get(idxBandit.get(randBanditIdx));
                chosenBandit.lacher();
//                        int randButin = random.nextInt(chosenBandit.collectedButins.size());
//                        this.wagon.butins.add(chosenBandit.collectedButins.get(randButin));
//                        chosenBandit.collectedButins.remove(randButin);
                train.personnes.set(idxBandit.get(randBanditIdx), chosenBandit);
//              System.out.println("shoot someone in the wagon to the left");
            } else {
                System.out.println("no bandits to shoot");
            }
            this.NB_BALLES--;
        } else {
            System.out.println("no more bullets");
        }

    }

}

