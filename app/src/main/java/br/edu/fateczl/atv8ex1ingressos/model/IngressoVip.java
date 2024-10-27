/*
@author:<Matheus Augusto Marti>
 */

package br.edu.fateczl.atv8ex1ingressos.model;

public class IngressoVip extends Ingresso{
    @Override
    public float valorFinal(float taxaConv) {
        return (float) ((getValor() + taxaConv) * 1.18);
    }
}
