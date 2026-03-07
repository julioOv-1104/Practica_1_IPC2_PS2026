
package Modelos;


public class Partida {
    
    private int id_partida;
    private Usuario usuario = new Usuario();
    private int puntaje_total = 0;
    private int nivel_alcanzado = 1;

    public Partida(int id_partida, Usuario usuario) {
        this.id_partida = id_partida;
        this.usuario = usuario;
    }
    
    public Partida(String usuario, int puntaje, int nivel) {
        this.id_partida = id_partida;
        this.usuario.setNombre(usuario);
        this.puntaje_total = puntaje;
        this.nivel_alcanzado = nivel;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPuntaje_total() {
        return puntaje_total;
    }

    public void setPuntaje_total(int puntaje_total) {
        this.puntaje_total = puntaje_total;
    }

    public int getNivel_alcanzado() {
        return nivel_alcanzado;
    }

    public void setNivel_alcanzado(int nivel_alcanzado) {
        this.nivel_alcanzado = nivel_alcanzado;
    }
    
    
    
}
