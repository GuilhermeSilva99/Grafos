package grafo;

import java.util.ArrayList;

public class BuscaProfundidade {

    public void buscaProfundidade(int grafo){
        System.out.println("\n--------------- Busca em Largura ---------------\n");
        String decisao = EntradaUsuario.getString("Começar de um no especifico? s/n ", true);
        if(decisao.equals("s")) {
            String nome = EntradaUsuario.getString("Nome do Vertice: ", true);
            Vertice vertice = null;
            for (int i = 0; !nome.equals("") && i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                if (nome.equals(Grafo.getGrafos().get(grafo).vertices.get(i).nome)) {
                    vertice = Grafo.getGrafos().get(grafo).vertices.get(i);
                    vertice.cor = Cor.CINZA;
                    vertice.distancia = 0;
                } else {
                    Grafo.getGrafos().get(grafo).vertices.get(i).cor = Cor.BRANCO;
                    Grafo.getGrafos().get(grafo).vertices.get(i).distancia = -1;
                }
            }
            if (vertice == null)
                System.out.println("\nEsse vertice não existe no grafo " + Grafo.getGrafos().get(grafo).nome + "\n");
            else {
                int tempo = 0;
                tempo =  buscaAux(grafo, vertice, tempo);
                for (int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                    if (Grafo.getGrafos().get(grafo).vertices.get(i).cor == Cor.BRANCO) {
                        tempo = buscaAux(grafo, Grafo.getGrafos().get(grafo).vertices.get(i), tempo);
                    }
                }
                double cont = vertice.fim;
                for(int i = 0; i < cont; i++){
                    for(int j = 0; j < Grafo.getGrafos().get(grafo).vertices.size(); j++){
                        if(Grafo.getGrafos().get(grafo).vertices.get(j).distancia == i){
                            System.out.println("nome " + Grafo.getGrafos().get(grafo).vertices.get(j).nome + ": " + Grafo.getGrafos().get(grafo).vertices.get(j).distancia + " - " + Grafo.getGrafos().get(grafo).vertices.get(j).fim);
                            break;
                        }
                    }
                }

                /*for (int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                    System.out.println("nome " + Grafo.getGrafos().get(grafo).vertices.get(i).nome + ": " + Grafo.getGrafos().get(grafo).vertices.get(i).distancia + " - " + Grafo.getGrafos().get(grafo).vertices.get(i).fim);
                }*/

            }
        }else {
            for (int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                Grafo.getGrafos().get(grafo).vertices.get(i).cor = Cor.BRANCO;
                Grafo.getGrafos().get(grafo).vertices.get(i).distancia = -1;
            }
            int tempo = 0;
            for (int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                if (Grafo.getGrafos().get(grafo).vertices.get(i).cor == Cor.BRANCO) {
                    tempo = buscaAux(grafo, Grafo.getGrafos().get(grafo).vertices.get(i), tempo);
                }
            }
            for (int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++) {
                System.out.println("nome " + Grafo.getGrafos().get(grafo).vertices.get(i).nome + ": " + Grafo.getGrafos().get(grafo).vertices.get(i).distancia + " - " + Grafo.getGrafos().get(grafo).vertices.get(i).fim);
            }
        }
    }

    private int buscaAux(int indexGrafo, Vertice v1, int tempo){
        v1.cor = Cor.CINZA;
        tempo = tempo + 1;
        v1.distancia = tempo;
        for(int i =0; i < Grafo.getGrafos().get(indexGrafo).vertices.size(); i++){
            if(verificarAdjacencia(Grafo.getGrafos().get(indexGrafo).vertices.get(i),v1)){
                if(Grafo.getGrafos().get(indexGrafo).vertices.get(i).cor == Cor.BRANCO){
                    Grafo.getGrafos().get(indexGrafo).vertices.get(i).pai = v1;
                    tempo = buscaAux(indexGrafo,Grafo.getGrafos().get(indexGrafo).vertices.get(i),tempo);
                }
            }
        }
        v1.cor = Cor.PRETO;
        tempo = tempo + 1;
        v1.fim = tempo;
        return tempo;
    }

    public boolean verificarAdjacencia(Vertice vt1, Vertice vt2){
        boolean achou1 = false, achou2 = false;
        for(int i = 0; i < vt1.arestas.size();i++){
            if(vt1.arestas.get(i).vertice.nome.equals(vt2.nome)){
                achou1 = true;
                break;
            }
        }
        for(int i = 0; i < vt2.arestas.size();i++){
            if(vt2.arestas.get(i).vertice.nome.equals(vt1.nome)){
                achou2 = true;

                break;
            }
        }

        if(achou1 && achou2){
            return true;
        }else {
            return false;
        }
    }


    public static void menu(int grafo){
        BuscaProfundidade busca = new BuscaProfundidade();
        busca.buscaProfundidade(grafo);
    }
}
