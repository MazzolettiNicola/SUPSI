package Esercizio2;

public class Esercizio2 {
    /**
     * Metodo per sommare due vettori
     * @param vectorA
     * @param vectorB
     * @return vettore, somma del vettoreA e vettoreB
     */
    public static Vector sum(Vector vectorA, Vector vectorB){
        return new Vector(vectorA.getVector_x()+vectorB.getVector_x(),
                vectorA.getVector_y()+vectorB.getVector_y(),
                vectorA.getVector_z()+vectorB.getVector_z());
    }

    /**
     * Metodo per calcolare la lunghezza di un vettore
     * @param vector
     * @return lunghezza del vettore
     */
    public static double calculateLength(Vector vector){
        return Math.sqrt(Math.pow(vector.getVector_x(), 2) + Math.pow(vector.getVector_y(), 2) + Math.pow(vector.getVector_z(), 2));
    }

    /**
     * Metodo per il calcolo del prodotto scalare di un vettore
     * @param vector
     * @param lambda
     * @return vettore, prodotto scalare
     */
    public static Vector scalarProduct(Vector vector, double lambda){
        return new Vector(vector.getVector_x()*lambda,
                vector.getVector_y()*lambda,
                vector.getVector_z()*lambda);
    }

    /**
     * Metodo per calcolare il vettore opposto di un vettore fornito
     * @param vector, vettore di cui calcolare l'opposto
     * @return vettore_opposto
     */
    public static Vector calculateOpposite(Vector vector){
        return scalarProduct(vector, -1);
    }

    /**
     * Metodo per stampare un vettore formattato con parentesi()
     * @param vector, vettore da stampare
     * @return stringa formattata
     */
    public static String printVector(Vector vector){
        return "(" + vector.getVector_x() + "," + vector.getVector_y() + "," + vector.getVector_z() + ")";
    }

    public static void main(String[] args) {
        Vector vectorA = new Vector(2.2,3.3,4.4);
        Vector vectorB = new Vector(1.5,3.0,4.5);

        System.out.println("Vettore A: " + printVector(vectorA));
        System.out.println("Vettore B: " + printVector(vectorB));

        System.out.println("Somma: " + printVector(sum(vectorA, vectorB)));
        System.out.println("Length vectorA: " + calculateLength(vectorA));
        System.out.println("Prodotto scalare vettoreA con lambda 2: " + printVector(scalarProduct(vectorA, 2)));
        System.out.println("Vettore opposto al vettoreA: " + printVector(calculateOpposite(vectorA)));
    }
}
