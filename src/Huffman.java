import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        String texto = "estamos bien";
        System.out.println("Texto original: " + texto);

        Map<Character, Integer> frecuencias = obtenerFrecuencias(texto);
        NodoHuffman raiz = construirArbolHuffman(frecuencias);
        Map<Character, String> codigos = generarCodigos(raiz);

        System.out.println("Codificación Huffman:");
        for (Map.Entry<Character, String> entry : codigos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        String textoCodificado = codificarTexto(texto, codigos);
        System.out.println("Texto codificado: " + textoCodificado);
    }

    private static Map<Character, Integer> obtenerFrecuencias(String texto) {
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }
        return frecuencias;
    }

    private static NodoHuffman construirArbolHuffman(Map<Character, Integer> frecuencias) {
        PriorityQueue<NodoHuffman> cola = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            cola.offer(new NodoHuffman(entry.getKey(), entry.getValue()));
        }

        while (cola.size() > 1) {
            NodoHuffman izquierda = cola.poll();
            NodoHuffman derecha = cola.poll();
            NodoHuffman padre = new NodoHuffman('\0', izquierda.frecuencia + derecha.frecuencia, izquierda, derecha);
            cola.offer(padre);
        }

        return cola.poll();
    }

    private static Map<Character, String> generarCodigos(NodoHuffman raiz) {
        Map<Character, String> codigos = new HashMap<>();
        generarCodigosRec(raiz, "", codigos);
        return codigos;
    }

    private static void generarCodigosRec(NodoHuffman nodo, String codigo, Map<Character, String> codigos) {
        if (nodo == null) return;
        if (nodo.izquierda == null && nodo.derecha == null) {
            codigos.put(nodo.caracter, codigo);
        }
        generarCodigosRec(nodo.izquierda, codigo + "0", codigos);
        generarCodigosRec(nodo.derecha, codigo + "1", codigos);
    }

    private static String codificarTexto(String texto, Map<Character, String> codigos) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            resultado.append(codigos.get(c));
        }
        return resultado.toString();
    }
}


