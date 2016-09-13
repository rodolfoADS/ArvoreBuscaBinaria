package arvore;

public class Arvore {

    static No raiz = null;

    public static void main(String[] args) {

        /*inserirIterativo(9);
        inserirIterativo(15);
        inserirIterativo(50);
        inserirIterativo(10);
        inserirIterativo(652);
        inserirIterativo(3);
        inserirIterativo(155);*/
        inserir(9);
        inserir(15);
        inserir(50);
        inserir(10);
        inserir(3);
        inserir(99);
        inserir(596);
        inserir(155);
        inserir(526);
        inserir(896);

        exibir(raiz, 0);

    }

    public static void inserir(int x) {
        if (raiz == null) {
            raiz = new No(x);
        } else {
            inserirRecursivo(x, raiz);
        }
    }

    private static void inserirRecursivo(int x, No temp) {
        if (x > temp.valor) {
            if (temp.dir == null) {
                temp.dir = new No(x);
            } else {
                inserirRecursivo(x, temp.dir);
            }
        } else if (temp.esq == null) {
            temp.esq = new No(x);
        } else {
            inserirRecursivo(x, temp.esq);
        }

    }

    public static void inserirIterativo(int x) {
        No novo = new No(x);

        if (raiz == null) {
            raiz = novo;
        } else {
            No temp = raiz;
            boolean inserido = false;

            while (!inserido) {
                if (novo.valor <= temp.valor) {
                    if (temp.esq == null) {
                        temp.esq = novo;
                        inserido = true;
                    } else {
                        temp = temp.esq;
                    }
                } else if (temp.dir == null) {
                    temp.dir = novo;
                    inserido = true;
                } else {
                    temp = temp.dir;
                }
            }
        }

    }

    static void exibeArvore() {
        if (raiz == null) {
            System.out.println("Arvore Vazia...");
        } else {
            exibir(raiz, 0);
        }
    }

    public static void exibir(No temp, int x) {
        if (temp.dir != null) {
            exibir(temp.dir, x + 1);
        } else {
            System.out.println();
        }

        for (int i = 1; i <= x; i++) {
            System.out.print("| ");
        }

        if ((temp.esq == null) && (temp.dir == null)) {
            System.out.print("[" + temp.valor + "]");
        } else {
            System.out.print(temp.valor);
        }

        if (temp.esq != null) {
            exibir(temp.esq, x + 1);
        } else {
            System.out.println();
        }
    }

    public static int contaNo(No n) {
        int count = 1;

        if (n.esq != null) {
            count += contaNo(n.esq);
        }
        if (n.dir != null) {
            count += contaNo(n.dir);
        }
        return count;

    }

    public static int contaFolha(No n) {
        int folha = 0;
        if (n.esq != null) {
            folha += contaFolha(n.esq);
        }

        if (n.dir != null) {
            folha += contaFolha(n.dir);
        }

        if ((n.esq == null) && (n.dir == null)) {
            if (n != raiz) {
                folha++;
            }
        }
        return folha;
    }

    public static boolean removeFolha(No n, int x) {
        if (n.esq != null) {
            if (removeFolha(n.esq, x)) {
                n.esq = null;
                //System.out.println("Valor Removido");
            }
        }

        if (n.dir != null) {
            if (removeFolha(n.dir, x)) {
                n.dir = null;
                //System.out.println("Valor Removido");
            }
        }

        if ((n.valor == x) && (n.esq == null) && (n.dir == null)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeFolhaImpar(No n) {
        if (n.esq != null) {
            if (removeFolhaImpar(n.esq)) {
                n.esq = null;
                //System.out.println("Valor Removido");
            }
        }

        if (n.dir != null) {
            if (removeFolhaImpar(n.dir)) {
                n.dir = null;
                //System.out.println("Valor Removido");
            }
        }

        if ((n.valor % 2 != 0) && (n.esq == null) && (n.dir == null)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeFolhaPar(No n) {
        if (n.esq != null) {
            if (removeFolhaPar(n.esq)) {
                n.esq = null;
                //System.out.println("Valor Removido");
            }
        }

        if (n.dir != null) {
            if (removeFolhaPar(n.dir)) {
                n.dir = null;
                //System.out.println("Valor Removido");
            }
        }

        if ((n.valor % 2 == 0) && (n.esq == null) && (n.dir == null)) {
            return true;
        } else {
            return false;
        }
    }

}
