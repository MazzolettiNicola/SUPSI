package Esercizio1;

public class Gym {
    /**
     * Massimo dei corsi aggiungibili ad una palestra
     */
    private static final int MAX_COURSES = 10;
    /**
     * Massimo dei clienti di una palestra
     */
    private static final int MAX_SUBSCRIBERS = 100;

    /**
     * Nome della palestra
     */
    private String gymName;

    /**
     * Array dei corsi inseriti in una palestra
     */
    private Course[] gymCourses;
    /**
     * Conteggio dei corsi aggiunti alla palestra
     */
    private int cAdded = 0;

    /**
     * Iscritti di una palestra
     */
    private Person[] gymSubscribers;
    /**
     * Conteggio delle persone iscritte alla palestra
     */
    private int pSubscribed = 0;

    /**
     * Costruttore della classe Esercizio1.Gym
     * @param gymName, nome della palestra
     */
    public Gym(String gymName) {
        this.gymName = gymName;
        this.gymCourses = new Course[MAX_COURSES];
        this.gymSubscribers = new Person[MAX_SUBSCRIBERS];
    }

    /**
     * Metodo che aggiunge un corso alla palestra
     * @param newCourse, nuovo corso da aggiungere
     */
    public void addCourse(Course newCourse){
        //Controllo che il corso da inserire non sia null e che ci sia ancora disponibilità di aggiungere ulteriori corsi
        if(newCourse != null && cAdded < MAX_COURSES){
            //controllo che il corso non sia già stato inserito
            boolean isPresent = false;
            for(int i = 0; i < MAX_COURSES -1; i++){
                if(gymCourses[i] != null) {
                    if (gymCourses[i].equals(newCourse)) {
                        isPresent = true;
                        System.out.println("Il corso è già stato inserito nella palestra");
                        break;
                    }
                }else break;
            }

            //se il corso non è già stato inserito nella palestra, lo aggiungo
            if(!isPresent){
                gymCourses[cAdded] = newCourse;
                cAdded++;
                System.out.println("Il corso è stato aggiunto alla palestra");
            }
        }else System.out.println("Corsi massimi offerti dalla palestra raggiunti");
    }

    /**
     * Metodo per controllare se una persona è iscritta alla palestra
     * @param person, persona che si vuole controllare
     * @return true, se la persona è presente
     */
    public boolean isSubscribed(Person person){
        //controllo che la persona non sia già stata iscritta alla palestra
        boolean isPresent = false;
        for(int i = 0; i < gymSubscribers.length; i++){
            if(gymSubscribers[i] != null) {
                if (gymSubscribers[i].equals(person)) {
                    isPresent = true;
                    break;
                }
            }else break;
        }

        return isPresent;
    }

    /**
     * Metodo per iscrivere una persona alla palestra
     * @param newPerson, persona da iscrivere
     */
    public void subscribePerson(Person newPerson){
        if(newPerson != null && pSubscribed < MAX_SUBSCRIBERS) {
            if(!isSubscribed(newPerson)) {
                gymSubscribers[pSubscribed] = newPerson;
                pSubscribed++;
                System.out.println("Persona iscritta alla palestra, la persona ora può iscriversi a qualsiasi corso");
            }else System.out.println("La persona è già stata iscritta alla palestra");
        }else System.out.println("La palestra è piena, non si accettano più iscrizioni");
    }

    /**
     * Metodo per stampare i corsi presenti nella palestra
     */
    public void printGymCourses(){
        System.out.println(this.gymName + " courses list:");
        for(int i = 0; i < MAX_COURSES; i++){
            if(gymCourses[i] != null) {
                gymCourses[i].printInfos();
            }else break;
        }
    }

    /**
     * Metodo per stampare gli iscritti di una palestra
     */
    public void printGymSubscribers(){
        System.out.println(this.gymName + " subscribers list: ");
        for(int i = 0; i < MAX_SUBSCRIBERS; i++){
            if(gymSubscribers[i] != null){
                System.out.println("- " + gymSubscribers[i].printInfos());
            }
        }
    }

    public static void main(String[] args) {
        Person cliente = new Person("Nicola", "Mazzoletti", 1998);
        Person clienteB = new Person("Luca", "Verdi", 1987);
        Person istruttoreA = new Person("Mario", "Rossi", 1967);
        Person istruttoreB = new Person("Marco", "Giannini", 1980);

        Gym gym = new Gym("ITGym");
        Course programmazione_java = new Course("Programmazione java", istruttoreA, WeekDays.MONDAY, gym);
        Course programmazione_python = new Course("Progrannazione python", istruttoreB, WeekDays.TUESDAY, gym);

        gym.addCourse(programmazione_java);
        gym.addCourse(programmazione_python);
        System.out.println();

        gym.subscribePerson(cliente);
        programmazione_java.subscribePerson(cliente);
        programmazione_python.subscribePerson(clienteB);
        gym.subscribePerson(clienteB);
        programmazione_java.subscribePerson(clienteB);
        System.out.println();

        gym.printGymCourses();
        gym.printGymSubscribers();
    }
}
