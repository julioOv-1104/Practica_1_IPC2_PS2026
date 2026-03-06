
package Modelos;


public class Partida {
    
    private int id_partida;
    private Usuario usuario;
    private int puntaje_total = 0;
    private int nivel_alcanzado = 1;

    public Partida(int id_partida, Usuario usuario) {
        this.id_partida = id_partida;
        this.usuario = usuario;
    }
    
    
    
}
