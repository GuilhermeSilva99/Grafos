
package grafo;

import java.io.*;
import java.util.ArrayList;

public class Grafo implements Serializable {
    private static String caminhografo = "src\\Grafos.txt";

    private static ArrayList<Grafo> grafos = new ArrayList<Grafo>();
    public static ArrayList<Grafo> getGrafos(){
        return grafos;
    }

    public String nome;
    public ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    public int numero_arestas = 0;

    public String imprimirVertices(){
        String vertices = "[";
        for(int i = 0; i < this.vertices.size(); i++)
            if(this.vertices.size() - 1 == i){
                vertices += this.vertices.get(i).nome;
            }else
                vertices += this.vertices.get(i).nome + ", ";
        vertices += "]";
        return vertices;
    }

    public String imprimirArestas(){
        String arestas = "[";
        for(int i = 0; i < this.vertices.size(); i++)
            if(this.vertices.get(i).arestas.size() > 0){
                if(this.vertices.size() - 1 == i){
                    arestas += this.vertices.get(i).imprimirArestas();
                }else
                    arestas += this.vertices.get(i).imprimirArestas() + ", ";
            }
        arestas += "]";
        return arestas;
    }

    private void imprimirGrafo(){
        System.out.println("Nome do Grafo: " + this.nome + "{");
        System.out.println("Lista de Vertices: " + this.imprimirVertices());
        System.out.println("Lista de Arestas: " + this.imprimirArestas());
        System.out.println("}");
    }

    private static boolean verificarGrafoExiste(String grafo){
        for(int i = 0; i < grafos.size(); i++)
            if(grafos.get(i).nome.equals(grafo))
                return true;
        return false;
    }

    public static void listarGrafos(){
        System.out.println("\n--------------- Lista de Grafos ---------------\n");
        boolean flag = false;
        for(int i = 0; i < grafos.size(); i++){
            grafos.get(i).imprimirGrafo();
            flag = true;
        }
        if(!flag)
            System.out.println("Lista vazia...");
    }

    public static void Criar(){
        System.out.println("\n--------------- Criar Grafo ---------------\n");
        Grafo grafo = new Grafo();
        boolean flag = false;
        while(!flag){
            grafo.nome = EntradaUsuario.getString("Nome do Grafo: ", false);
            if(verificarGrafoExiste(grafo.nome))
                System.out.println("Esse grafo já existe!");
            else
                flag = true;
        }
        grafos.add(grafo);
        abrirGrafo(grafos.size() - 1);
    }

    public static void Consultar(){
        System.out.println("\n--------------- Consultar Grafo ---------------\n");
        String nome = EntradaUsuario.getString("Nome do grafo: ", true);
        for(int i = 0; !nome.equals("") && i < grafos.size(); i++){
            if(nome.equals(grafos.get(i).nome)){
                grafos.get(i).imprimirGrafo();
                return;
            }
        }
        System.out.println("\nEsse grafo não foi encontrado\n");
    }

    public static void excluir(){
        System.out.println("\n--------------- Excluir Grafo ---------------\n");
        String nome = EntradaUsuario.getString("Nome do grafo: ", true);
        int grafo = -1;
        for(int i = 0; !nome.equals("") && i < grafos.size(); i++){
            if(nome.equals(grafos.get(i).nome)){
                grafo = i;
                break;
            }
        }
        if(grafo == -1)
            System.out.println("\nEsse grafo não foi encontrado\n");
        else{
            System.out.println("\n-- Informações do grafo selecionado --\n");
            grafos.get(grafo).imprimirGrafo();

            if(EntradaUsuario.getString("Você deseja realmente excluir esse grafo? ", false).toLowerCase().equals("sim")){
                grafos.remove(grafo);
                System.out.println("Grafo excluído com sucesso");
            }else
                System.out.println("Exclusão cancelada");
        }
    }

    public static void abrirGrafo(int indice_grafo){
        if(indice_grafo == -1){
            System.out.println("\n--------------- Modificar Grafo ---------------\n");
            String nome = EntradaUsuario.getString("Nome do grafo: ", true);
            for(int i = 0; !nome.equals("") && i < grafos.size(); i++){
                if(nome.equals(grafos.get(i).nome)){
                    indice_grafo = i;
                    break;
                }
            }
        }

        if(indice_grafo == -1)
            System.out.println("Esse grafo não foi encontrado");
        else{
            menuGrafoAberto(indice_grafo);
        }
    }
    private static void buscaProfundidade(int grafo){

    }
    private static void buscaLargura(int grafo){

    }
    private static void buscaEstrela(int grafo){

    }
    private static void printMenuGrafoAberto(String nome){
        System.out.println("\n--------- Menu - Grafo " + nome + " ----------\n");
        System.out.println("Digite 1 para vertices");
        System.out.println("Digite 2 para arestas");
        System.out.println("Digite 3 para fazer busca em profundidade");
        System.out.println("Digite 4 para fazer busca em largura");
        System.out.println("Digite 5 para fazer a busca estrela");
        System.out.println("Digite 6 para sair do grafo " + nome);
    }
    private static void menuGrafoAberto(int grafo){
        String opcao = "0";
        printMenuGrafoAberto(grafos.get(grafo).nome);
        while( !opcao.equals("6") ){
            opcao = EntradaUsuario.getString("Digite sua opção: ", false);
            switch(opcao){
                case "1":
                    Vertice.menu(grafo);
                    break;
                case "2":
                    Aresta.menu(grafo);
                    break;
                case "3":
                    BuscaProfundidade.menu(grafo);
                    break;
                case "4":
                    BuscaLargura.menu(grafo);
                    break;
                case "5":
                    buscaEstrela(grafo);
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Opcão inválida!");
            }
            if(!opcao.equals("6"))
                printMenuGrafoAberto(grafos.get(grafo).nome);
        }
    }


    public static void salvarGrafos() {
        ObjectOutputStream gravarArquivo = null;
        try {
            FileOutputStream arquivo = new FileOutputStream(caminhografo);
            gravarArquivo = new ObjectOutputStream(arquivo);
            for (int i = 0; i < grafos.size(); i++) {
                gravarArquivo.writeObject(grafos.get(i));
            }
            gravarArquivo.close();
            arquivo.close();
        } catch (IOException e) {
        }
    }

    public static void lerGrafos(){

        try{
            FileInputStream arquivo = new FileInputStream(caminhografo);
            try{
                ObjectInputStream lerArquivo = new ObjectInputStream(arquivo);
                Grafo grafo;
                while ((grafo = (Grafo) lerArquivo.readObject())!=null) {
                    grafos.add(grafo);
                }
                lerArquivo.close();
                arquivo.close();
            }catch (Exception e){
            }
        }catch (FileNotFoundException e){
            try {
                FileOutputStream arquivo = new FileOutputStream(caminhografo);
                ObjectOutputStream gravarArquivo = new ObjectOutputStream(arquivo);
                gravarArquivo.close();
                arquivo.close();
            }catch (IOException t){
            }
        }catch (Exception e){
        }
    }
}
