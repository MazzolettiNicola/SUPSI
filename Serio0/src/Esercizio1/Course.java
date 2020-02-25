package Esercizio1;

/**
 * Enum per i giorni della settimana
 */
enum WeekDays {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}

public class Course {
    /**
     * Numero massimo di iscritti per un corso
     */
    private static final int MAX_SUBSCRIBERS = 10;

    /**
     * Nome del corso
     */
    private String courseName;
    /**
     * Istruttore del corso
     */
    private Person courseTeacher;
    /**
     * Giorno della settimana in cui si svolge il corso
     */
    private WeekDays weekDay;
    /**
     * Palestra in cui si svolge il corso
     */
    private Gym gym;

    /**
     * Iscritti il corso
     */
    private Person[] subscribers;
    /**
     * Numero di iscritti al corso
     */
    private int nSubscribers;

    /**
     * Costruttore della classe Esercizio1.Course
     * @param courseName
     * @param courseTeacher
     * @param weekDay
     * @param gym
     */
    public Course(String courseName, Person courseTeacher, WeekDays weekDay, Gym gym) {
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.weekDay = weekDay;
        this.subscribers = new Person[MAX_SUBSCRIBERS];
        nSubscribers = 0;
        this.gym = gym;
    }

    /**
     * Metodo che ritorna tutti gli iscritti del corso sotto forma di stringa
     * @return subs, stringa formattata di tutti gli iscritti del corso
     */
    public String getSubscribers() {
        String subs = "[";
        for(int i = 0; i < MAX_SUBSCRIBERS; i++){
            if(subscribers[i] != null) {
                String person = subscribers[i].getFirstName() + " " + subscribers[i].getLastName();
                if(subscribers[i+1] == null || i+1 == MAX_SUBSCRIBERS) subs += person;
                else subs += person + ", ";
            }else break;
        }
        subs += "]";

        return subs;
    }

    /**
     * Metodo per controllare se una persona è iscritta al corso
     * @param person, persona da controllare
     * @return true, se la persona è iscritta al corso
     */
    public boolean isSubscribed(Person person){
        for(int i = 0; i < subscribers.length; i++){
            if(subscribers[i] != null){
                if(subscribers[i].equals(person)) return true;
            }
        }

        return false;
    }

    /**
     * Metodo per iscrivere una persona ad un corso
     * @param newSubscriber, persona da iscrivere
     */
    public void subscribePerson(Person newSubscriber){
        //controllo che la persona sia iscritta alla palestra
        if(this.gym.isSubscribed(newSubscriber)) {
            //controllo che l'oggetto palestra e persona non siano null e che ci sia spazio per nuove iscrizioni al corso
            if(this.gym != null && newSubscriber != null && this.nSubscribers < MAX_SUBSCRIBERS){
                //se la persona non è già iscritta al corso, la iscrivo
                if(!isSubscribed(newSubscriber)){
                    subscribers[nSubscribers] = newSubscriber;
                    nSubscribers++;
                    System.out.println("La persona è stata iscritta al corso");
                }else System.out.println("La persona è già iscritta al seguente corso");
            }else System.out.println("I posti disponibili per questo corso sono terminati");
        }else System.out.println("La persona deve prima iscriversi alla palestra prima di potersi iscrivere ad un corso");
    }

    /**
     * Metodo per stampare le informazioni di un corso
     */
    public void printInfos(){
        System.out.println("Nome corso: " + this.courseName);
        System.out.println("Istruttore: " + this.courseTeacher.printInfos());
        System.out.println("Giorno della settimana: " + this.weekDay);
        System.out.println("Iscritti: " + this.getSubscribers());
        System.out.println();
    }
}
