
package grafo;

import java.util.ArrayList;

public class BuscaLargura {

    public ArrayList<Vertice> fila = new ArrayList<Vertice>();

    // adicionar os itens no fim e remove no inicio
    public void adicionarFila(Vertice v){
        fila.add(v);
    }
    public Vertice removerFila(){
        if(fila.size() > 0)
            return fila.remove(0);
        else
            return null;
    }

    public void buscaLargura(int grafo){
        System.out.println("\n--------------- Busca em Largura ---------------\n");
        String nome = EntradaUsuario.getString("Nome do Vertice: ", true);
        Vertice vertice = null;
        for(int i = 0; !nome.equals("") && i < Grafo.getGrafos().get(grafo).vertices.size(); i++){
            if(nome.equals(Grafo.getGrafos().get(grafo).vertices.get(i).nome)){
                vertice = Grafo.getGrafos().get(grafo).vertices.get(i);
                vertice.cor = Cor.CINZA;
                vertice.distancia = 0;
            }
            else{
                Grafo.getGrafos().get(grafo).vertices.get(i).cor = Cor.BRANCO;
                Grafo.getGrafos().get(grafo).vertices.get(i).distancia = -1;
            }
        }
        if(vertice == null)
            System.out.println("\nEsse vertice não existe no grafo " + Grafo.getGrafos().get(grafo).nome + "\n");
        else{
            adicionarFila(vertice);
            while((vertice = removerFila()) != null){
                for(int i = 0; i < vertice.arestas.size(); i++){
                    Vertice v = vertice.arestas.get(i).vertice;
                    if(v.cor == Cor.BRANCO){
                        v.cor = Cor.CINZA;
                        v.distancia = vertice.distancia + 1;
                        adicionarFila(v);
                    }
                }
                vertice.cor = Cor.PRETO;
            }
            imprimirBuscaLargura(grafo);
        }
    }

    public void imprimirBuscaLargura(int grafo){
        ArrayList<Vertice> vertices = new ArrayList<Vertice>(Grafo.getGrafos().get(grafo).vertices);
        int cont = 0;
        while(vertices.size() > 0 && cont < Grafo.getGrafos().get(grafo).vertices.size()){
            boolean primeiro = true;
            for(int j = 0; j < vertices.size(); j++){
                Vertice v = vertices.get(j);
                if(v.distancia == cont){
                    if(primeiro){
                        System.out.print("Distancia " + cont + ": " + v.nome);
                        primeiro = false;
                    }else
                        System.out.print(", " + v.nome);
                }
            }
            cont++;
            if(!primeiro)
                System.out.println("");
        }
    }

    public static void menu(int grafo){
        BuscaLargura busca = new BuscaLargura();
        busca.buscaLargura(grafo);
    }

}
