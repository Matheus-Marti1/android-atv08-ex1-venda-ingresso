/*
@author:<Matheus Augusto Marti>
 */

package br.edu.fateczl.atv8ex1ingressos.model;

public class Ingresso {
    private String identificador;
    private float valor;

    public Ingresso() {
        super();
    }

    public float valorFinal(float taxaConv){
        return this.valor + taxaConv;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
