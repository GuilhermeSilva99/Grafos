
package grafo;

import java.io.Serializable;
import java.util.ArrayList;

enum Cor implements Serializable {BRANCO, CINZA, PRETO}

public class Vertice implements Serializable {
    // Atributos para busca em largura
    public double distancia;
    public Vertice pai;
    public double fim;
    public Cor cor;

    public String nome;
    public ArrayList<Aresta> arestas = new ArrayList<Aresta>();

    public String imprimirArestas(){
        String arestas = "";
        for(int i = 0; i < this.arestas.size(); i++)
            if(this.arestas.size() - 1 == i){
                arestas += nome + '-' + this.arestas.get(i).vertice.nome + "=" + this.arestas.get(i).comprimento;
            }else
                arestas += nome + '-' + this.arestas.get(i).vertice.nome + "=" + this.arestas.get(i).comprimento + ", ";
        return arestas;
    }

    public static Vertice PesquisarVertice(int grafo, String nome){
        Vertice vertice = null;
        for(int i = 0; i < Grafo.getGrafos().get(grafo).vertices.size(); i++)
            if(Grafo.getGrafos().get(grafo).vertices.get(i).nome.equals(nome)){
                vertice = Grafo.getGrafos().get(grafo).vertices.get(i);
                break;
            }
        return vertice;
    }

    public static void listarVertices(int grafo){
        System.out.println("\n----- Lista de todos os vertices do grafo " + Grafo.getGrafos().get(grafo).nome + " -----\n");
        System.out.println(Grafo.getGrafos().get(grafo).imprimirVertices());
    }

    public static boolean verificarVerticeExiste(Grafo grafo, String vertice){
        for(int i = 0; i < grafo.vertices.size(); i++)
            if(grafo.vertices.get(i).nome.equals(vertice))
                return true;
        return false;
    }

    private static void adicionarVertice(int grafo){
        System.out.println("\n--------------- Adicionar Vertice ---------------\n");
        Vertice vertice = new Vertice();
        boolean flag = false;
        while(!flag){
            vertice.nome = EntradaUsuario.getString("Nome do Vertice: ", false);
            if(verificarVerticeExiste(Grafo.getGrafos().get(grafo), vertice.nome))
                System.out.println("Esse vertice já existe!");
            else
                flag = true;
        }
        Grafo.getGrafos().get(grafo).vertices.add(vertice);
        System.out.println("\nVertice Adicionado ao grafo " + Grafo.getGrafos().get(grafo).nome);
    }

    private static void excluirVertice(int grafo){
        System.out.println("\n--------------- Excluir Vertice ---------------\n");
        String nome = EntradaUsuario.getString("Nome do Vertice: ", true);
        int vertice = -1;
        for(int i = 0; !nome.equals("") && i < Grafo.getGrafos().get(grafo).vertices.size(); i++){
            if(nome.equals(Grafo.getGrafos().get(grafo).vertices.get(i).nome)){
                vertice = i;
                break;
            }
        }
        if(vertice == -1)
            System.out.println("\nEsse Vertice não foi encontrado\n");
        else if(Grafo.getGrafos().get(grafo).vertices.get(vertice).arestas.size() > 0)
            System.out.println("\nO vertice não pode ser excluído pois ele possui arestas\n");
        else{
            if(EntradaUsuario.getString("Você deseja realmente excluir esse vertice? ", false).toLowerCase().equals("sim")){
                Grafo.getGrafos().get(grafo).vertices.remove(vertice);
                System.out.println("Vertice excluído com sucesso");
            }else
                System.out.println("Exclusão cancelada");
        }
    }

    private static void printMenu(String nome){
        System.out.println("\n--------- Menu - Vertices do Grafo " + nome + " ----------\n");
        System.out.println("Digite 1 para adicionar vertices");
        System.out.println("Digite 2 para excluir vertices");
        System.out.println("Digite 3 para listar todos os vertices");
        System.out.println("Digite 4 para sair de vertices do grafo " + nome);
    }
    public static void menu(int grafo){
        String opcao = "0";
        printMenu(Grafo.getGrafos().get(grafo).nome);
        while( !opcao.equals("4") ){
            opcao = EntradaUsuario.getString("Digite sua opção: ", false);
            switch(opcao){
                case "1":
                    adicionarVertice(grafo);
                    break;
                case "2":
                    excluirVertice(grafo);
                    break;
                case "3":
                    listarVertices(grafo);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Opcão inválida!");
            }
            if(!opcao.equals("4"))
                printMenu(Grafo.getGrafos().get(grafo).nome);
        }
    }
}
