
package grafo;

public class Main {

    public static void menu(){
        System.out.println("\n------------------- Menu --------------------\n");
        System.out.println("Digite 1 para criar um novo grafo");
        System.out.println("Digite 2 para abrir um grafo");
        System.out.println("Digite 3 para consultar um grafo");
        System.out.println("Digite 4 para excluir um grafo");
        System.out.println("Digite 5 para listar todos os grafos");
        System.out.println("Digite 6 para Sair");
    }

    public static void main(String[] args) {
        String opcao = "0";
        Grafo.lerGrafos();
        menu();
        while( !opcao.equals("6") ){
            opcao = EntradaUsuario.getString("Digite sua opção: ", false);
            switch(opcao){
                case "1":
                    Grafo.Criar();
                    Grafo.salvarGrafos();
                    break;
                case "2":
                    Grafo.abrirGrafo(-1);
                    break;
                case "3":
                    Grafo.Consultar();
                    break;
                case "4":
                    Grafo.excluir();
                    break;
                case "5":
                    Grafo.listarGrafos();
                    break;
                case "6":
                    Grafo.salvarGrafos();
                    break;
                default:
                    System.out.println("Opcão inválida!");
            }
            if(!opcao.equals("6"))
                menu();
        }
    }

}
