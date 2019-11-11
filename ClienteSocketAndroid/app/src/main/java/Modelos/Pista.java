
package Modelos;

import java.io.Serializable;

/**
 *
 * @author DAM2
 */
public class Pista implements Serializable {

    public int id, num;
    public String tipo;

    public Pista(int id, int num, String tipo) {
        this.id = id;
        this.num = num;
        this.tipo = tipo;
    }

    public Pista() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
