
package grafo;

import java.io.Serializable;

public class Aresta implements Serializable {
    public Vertice vertice;
    public double comprimento;

    public Aresta(Vertice vertice, double comprimento){
        this.vertice = vertice;
        this.comprimento = comprimento;
    }

    private static void listarTodasArestas(int grafo){
        System.out.println("\n----- Lista de todas as arestas do grafo " + Grafo.getGrafos().get(grafo).nome + " -----\n");
        System.out.println(Grafo.getGrafos().get(grafo).imprimirArestas());
    }

    private static void listarArestasVertice(int grafo){
        System.out.println("\n--------------- Arestas de um Vertice ---------------\n");
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
        else if(Grafo.getGrafos().get(grafo).vertices.get(vertice).arestas.size() == 0)
            System.out.println("\nEsse vertice não contem arestas\n");
        else{
            System.out.println("----- Arestas do Vertice " + nome + " -----");
            System.out.println("[" + Grafo.getGrafos().get(grafo).vertices.get(vertice).imprimirArestas() + "]");
        }
    }

    private static boolean verificarArestaExiste(Vertice vertice1, Vertice vertice2){
        for(int i = 0; i < vertice1.arestas.size(); i++)
            if(vertice1.arestas.get(i).vertice.equals(vertice2)){
                return true;
            }
        return false;
    }

    private static void adicionarAresta(int grafo){
        Vertice.listarVertices(grafo);
        System.out.println("\n---------- Adicionar Aresta ao Grafo " + Grafo.getGrafos().get(grafo).nome + " ----------");
        Vertice vertice1 = null;
        Vertice vertice2 = null;
        String nome_vertice1 = EntradaUsuario.getString("Nome do Primeiro Vertice: ", false);
        String nome_vertice2 = EntradaUsuario.getString("Nome do Segundo Vertice: ", false);
        if((vertice1 = Vertice.PesquisarVertice(grafo, nome_vertice1)) == null)
            System.out.println("O primeiro vertice não existe no grafo " + Grafo.getGrafos().get(grafo).nome + "!");
        else if((vertice2 = Vertice.PesquisarVertice(grafo, nome_vertice2)) == null)
            System.out.println("O segundo vertice não existe no grafo " + Grafo.getGrafos().get(grafo).nome + "!");
        else if(verificarArestaExiste(vertice1, vertice2))
            System.out.println("Essa aresta já existe");
        else{
            double comprimento = EntradaUsuario.getNumero("Digite o comprimento da aresta: ", false);
            //int v1 = Grafo.getGrafos().get(grafo).vertices.indexOf(vertice1);
            //int v2 = Grafo.getGrafos().get(grafo).vertices.indexOf(vertice2);
            vertice1.arestas.add(new Aresta(vertice2, comprimento));
            vertice2.arestas.add(new Aresta(vertice1, comprimento));
        }
    }

    private static void auxExclusao(Vertice vertice1, Vertice vertice2){
        int aresta1 = -1;
        int aresta2 = -1;
        for(int i = 0; i < vertice1.arestas.size(); i++)
            if(vertice1.arestas.get(i).vertice.equals(vertice2)){
                aresta1 = i;
                break;
            }
        for(int i = 0; i < vertice2.arestas.size(); i++)
            if(vertice2.arestas.get(i).vertice.equals(vertice1)){
                aresta2 = i;
                break;
            }

        vertice1.arestas.remove(aresta1);
        vertice2.arestas.remove(aresta2);
    }
    private static void excluirAresta(int grafo){
        System.out.println("\n--------------- Excluir Aresta ---------------\n");
        String nome_vertice1 = EntradaUsuario.getString("Nome do Primeiro Vertice: ", true);
        String nome_vertice2 = EntradaUsuario.getString("Nome do Segundo Vertice: ", true);
        Vertice vertice1 = null;
        Vertice vertice2 = null;
        if((vertice1 = Vertice.PesquisarVertice(grafo, nome_vertice1)) != null &&
                (vertice2 = Vertice.PesquisarVertice(grafo, nome_vertice2)) != null &&
                verificarArestaExiste(vertice1, vertice2)){

            if(EntradaUsuario.getString("Você deseja realmente excluir essa aresta? ", false).toLowerCase().equals("sim")){
                auxExclusao(vertice1, vertice2);
                System.out.println("Aresta excluída com sucesso");
            }else
                System.out.println("Exclusão cancelada");
        }
        else
            System.out.println("Essa aresta não existe");
    }

    private static void printMenu(String nome){
        System.out.println("\n--------- Menu - Arestas do Grafo " + nome + " ----------\n");
        System.out.println("Digite 1 para adicionar arestas");
        System.out.println("Digite 2 para excluir arestas");
        System.out.println("Digite 3 para listar arestas de um determinado vertice");
        System.out.println("Digite 4 para listar todos as arestas");
        System.out.println("Digite 5 para sair de arestas do grafo " + nome);
    }
    public static void menu(int grafo){
        String opcao = "0";
        printMenu(Grafo.getGrafos().get(grafo).nome);
        while( !opcao.equals("5") ){
            opcao = EntradaUsuario.getString("Digite sua opção: ", false);
            switch(opcao){
                case "1":
                    adicionarAresta(grafo);
                    break;
                case "2":
                    excluirAresta(grafo);
                    break;
                case "3":
                    listarArestasVertice(grafo);
                    break;
                case "4":
                    listarTodasArestas(grafo);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opcão inválida!");
            }
            if(!opcao.equals("5"))
                printMenu(Grafo.getGrafos().get(grafo).nome);
        }
    }
}
