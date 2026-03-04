
package Modelos;

public class ConfigNivel {
    
    private int nivel;
    private int tiempo;

    public ConfigNivel(int nivel, int tiempo) {
        this.nivel = nivel;
        this.tiempo = tiempo;
    }


    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    
    
}
