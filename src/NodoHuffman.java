public class NodoHuffman implements Comparable<NodoHuffman> {
    char caracter;
    int frecuencia;
    NodoHuffman izquierda;
    NodoHuffman derecha;

    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    public NodoHuffman(char caracter, int frecuencia, NodoHuffman izquierda, NodoHuffman derecha) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public int compareTo(NodoHuffman otro) {
        return this.frecuencia - otro.frecuencia;
    }
}
