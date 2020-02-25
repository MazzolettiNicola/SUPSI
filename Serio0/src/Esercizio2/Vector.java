package Esercizio2;

public class Vector {
    /**
     * Coordinata x del vettore
     */
    private double vector_x;
    /**
     * Coordinata y del vettore
     */
    private double vector_y;
    /**
     * Coordinata z del vettore
     */
    private double vector_z;

    /**
     * Costruttore della classe Vector
     * @param vector_x
     * @param vector_y
     * @param vector_z
     */
    public Vector(double vector_x, double vector_y, double vector_z) {
        this.vector_x = vector_x;
        this.vector_y = vector_y;
        this.vector_z = vector_z;
    }

    public double getVector_x() {
        return vector_x;
    }

    public double getVector_y() {
        return vector_y;
    }

    public double getVector_z() {
        return vector_z;
    }
}
