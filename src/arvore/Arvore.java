package arvore;

public class Arvore {
    
    public static noA inicio = null;

    public static void main(String[] args) {
        // Abre o arquivo
        leArquivo();

        //REMOVE FOLHAS PARES 
         removePares();
         System.out.println("Número de folhas que restam é: "+contaFolha(inicio));
        
        // NO MAIS A DIRETA         
         noA no=noMaisADireita(inicio.esq);
         System.out.println("No Mais a Direita da Sub-Arvore a esquerda:"+no.valor);
        
         
         //REMOVE MULTIPLOS DE 5
         remover();
         System.out.println("Número de folhas que restam é"+contaFolhas(inicio));
        
               
        //QUANTOS NOS TEM SOMENTE 1 FILHO
        quantosTemUmFilho(inicio);
        System.out.println("Número de nós que possuem somente um filho é:"+exibeNos(inicio));
        

         System.out.println(inicio.valor);
    }

    static void leArquivo() {
        Scanner ler = new Scanner(System.in);

        String nome = "";
        JFileChooser arquivo = new JFileChooser();
        int retorno = arquivo.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            nome = arquivo.getSelectedFile().getAbsolutePath();
        }
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);
            System.out.println(nome);
            String linha = lerArq.readLine();
            while (linha != null) {
                insereNoA(Integer.parseInt(linha));
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

    static void insereNoA(int v) {
        if (inicio == null) {
            inicio = new noA(v);
        } else {
            insereNoARec(inicio, v);
        }
    }

    static void insereNoARec(noA n, int v) {
        if (v <= n.valor) {
            if (n.esq == null) {
                n.esq = new noA(v);
            } else {
                insereNoARec(n.esq, v);
            }
        } else if (n.dir == null) {
            n.dir = new noA(v);
        } else {
            insereNoARec(n.dir, v);
        }
    }

    static void exibeArvore() {
        if (inicio == null) {
            System.out.println("Arvore Vazia...");
        } else {
            exibeNoA(inicio, 0);
        }
    }

    static void exibeNoA(noA n, int x) {
        if (n.dir != null) {
            exibeNoA(n.dir, x + 1);
        } else {
            System.out.println();
        }
        for (int i = 1; i <= x; i++) {
            System.out.print("|  ");
        }
        if ((n.esq == null) && (n.dir == null)) {
            System.out.print("[" + n.valor + "]");
        } else {
            System.out.print(n.valor);
        }
        if (n.esq != null) {
            exibeNoA(n.esq, x + 1);
        } else {
            System.out.println();
        }

    }

    static int contaNiveis(noA n) {
        int x = 0, xe = 1, xd = 1;

        if (n.esq != null) {
            xe += contaNiveis(n.esq);
        }
        if (n.dir != null) {
            xd += contaNiveis(n.dir);
        }

        if (xd > xe) {
            return xd;
        } else {
            return xe;
        }
    }

    static int profundidade(noA temp) {
        int xesq = 1;
        int xdir = 1;
        if (temp.esq != null) {
            xesq += profundidade(temp.esq);
        }
        if (temp.dir != null) {
            xdir += profundidade(temp.dir);
        }
        if (xdir > xesq) {
            return xdir;
        } else {
            return xesq;
        }
    }

    static int profundidadeEsq(noA temp) {
        int xesq = 1;
        if (temp.esq != null) {
            xesq += profundidade(temp.esq);
        }
        return xesq;
    }

    static int profundidadeDir(noA temp) {
        int xdir = 1;
        if (temp.dir != null) {
            xdir += profundidade(temp.dir);
        }
        return xdir;
    }

    static int contaFolha(noA temp) {
        int x = 0;
        if ((temp.esq == null) && (temp.dir == null)) {
            x = 1;
        } else {
            if (temp.esq != null) {
                x += contaFolha(temp.esq);
            }
            if (temp.dir != null) {
                x += contaFolha(temp.dir);
            }
        }
        return x;
    }

    static int contaFolhaEsq(noA temp) {
        int x = 0;
        if ((temp.esq == null) && (temp.dir == null)) {
            x = 1;
        } else if (temp.esq != null) {
            x += contaFolha(temp.esq);
        }
        return x;
    }

    static int contaFolhaDir(noA temp) {
        int x = 0;
        if ((temp.esq == null) && (temp.dir == null)) {
            x = 1;
        } else if (temp.dir != null) {
            x += contaFolha(temp.dir);
        }
        return x;
    }

    
    static int contaFolhas(noA n) {
        int x = 0;

        if (n.esq != null) {
            x += contaFolhas(n.esq);
        }
        if (n.dir != null) {
            x += contaFolhas(n.dir);
        }

        if ((n.esq == null) && (n.dir == null)) {
            x++;
        }

        return x;
    }

    static void removeFolhas(noA n) {
        if (n.esq != null) {
            if (temFilho(n.esq)) {
                removeFolhas(n.esq);
            } else if (n.esq.valor % 2 == 0) {
                n.esq = null;
            }
        }
        if (n.dir != null) {
            if (temFilho(n.dir)) {
                removeFolhas(n.dir);
            } else if (n.dir.valor % 2 == 0) {
                n.dir = null;
            }
        }
    }

    static void exibeFolhas(noA n) {

        if (n.esq != null) {
            exibeFolhas(n.esq);
        }
        if (n.dir != null) {
            exibeFolhas(n.dir);
        }

        if ((n.esq == null) && (n.dir == null)) {
            System.out.println(n.valor);
        }

    }

    static boolean temUmFilho(noA n) {
        boolean retorno = false;
        if ((n.esq == null) && (n.dir == null)) {
            retorno = false;
        }
        if ((n.esq != null) && (n.dir != null)) {
            retorno = false;
        }
        if (n.dir == null && n.esq != null) {
            retorno = true;
        }
        if (n.dir != null && n.esq == null) {
            retorno = true;
        }

        return retorno;
    }

    static int exibeNos(noA n) {
        int count = 0;
        if (inicio == null) {
            System.out.println("Arvore Vazia...");
        } else {
            if (n.dir != null) {
                if (temUmFilho(n) == true) {
                    count++;
                }
                count += exibeNos(n.dir);
            }
            if (n.esq != null) {
                if (temUmFilho(n) == true) {
                    count++;
                }
                count += exibeNos(n.esq);
            }
        }
        return count;
    } 

    static void ExibirNos(noA n) {
        if (inicio == null) {
            System.out.println("Arvore Vazia...");
        } else {
            if (n.dir != null) {
                ExibirNos(n.dir);
            }
            if (n.esq != null) {
                ExibirNos(n.esq);
            }
            if ((n.esq == null) && (n.dir == null)) {
                if (n.valor % 5 == 0) {
                    System.out.print("[" + n.valor + "]");
                    removeNo(inicio, n.valor);
                }
            }
        }
    }

    static void removeNo(noA n, int x) {
        int f = contaFilhos(n, x);
        switch (f) {
            case -1:
                System.out.println("O nó não existe na árvore");
                break;
            case 0:
                removeFolha(n, x);
                break;
            case 1:
                removeUmFilho(n, x);
                break;
            case 2:
                removeDoisFilhos(n, x);
                break;
        }
    } 

    static void removeFolha(noA n, int x) {
        if (n.esq != null) {
            if (n.esq.valor == x) {
                n.esq = null;
            } else if (n.valor > x) {
                removeFolha(n.esq, x);
            }
        }
    } 
    
    static void removeUmFilho(noA n, int x) {
        if (n.valor == x) {
            if (n.esq != null) {
                n.valor = n.esq.valor;
                n.dir = n.esq.dir;
                n.esq = n.esq.esq;
            }
            if (n.dir != null) {
                n.valor = n.dir.valor;
                n.esq = n.dir.esq;
                n.dir = n.dir.dir;
            }
        } else if (x < n.valor) {
            removeUmFilho(n.esq, x);
        } else {
            removeUmFilho(n.dir, x);
        }
    } 
    
    static void removeDoisFilhos(noA n, int x) {
        if (n.valor == x) {
            noA tmp = noMaisAEsquerda(n.dir);
            int novovalor = tmp.valor;
            removeNo(n, tmp.valor);
            n.valor = novovalor;
        } else if (x < n.valor) {
            removeDoisFilhos(n.esq, x);
        } else {
            removeDoisFilhos(n.dir, x);
        }
    }

    static noA noMaisADireita(noA n) {
        noA retorno = null;
        if (n.dir != null) {
            retorno = noMaisADireita(n.dir);
        } else {
            retorno = n;
        }
        return retorno;
    } 

    static noA girarDireita(noA x) {
        noA y = x.esq;
        x.esq = y.dir;
        y.dir = x;
        return y;
    }

    static noA girarEsquerda(noA x) {
        noA y = x.dir;
        x.dir = y.esq;
        y.esq = x;
        return y;
    }

    static int contaFilhos(noA n, int x) {
        int nro_filhos = 0;

        if (n.valor % x == 0) {
            if (n.esq != null) {
                nro_filhos++;
            }
            if (n.dir != null) {
                nro_filhos++;
            }
        } else {
            if (x < n.valor) {
                if (n.esq != null) {
                    nro_filhos += contaFilhos(n.esq, x);
                } else {
                    return -1;
                }
            }
            if (x > n.valor) {
                if (n.dir != null) {
                    nro_filhos += contaFilhos(n.dir, x);
                } else {
                    return -1;
                }
            }
        }
        return nro_filhos;
    }

    static noA noMaisAEsquerda(noA n) {
        noA retorno = null;
        if (n.esq != null) {
            retorno = noMaisAEsquerda(n.esq);
        } else {
            retorno = n;
        }
        return retorno;
    }

    static boolean temFilho(noA n) {
        if ((n.esq == null) && (n.dir == null)) {
            return false;
        } else {
            return true;
        }
    }

    static int contarNos(noA n) {
        int x = 0;
        if (n == null) {
            return x;
        } else {
            x++;
            x += contarNos(n.esq);
            x += contarNos(n.dir);
        }
        return x;
    }

    static int contaFolhasPares(noA n) {
        int x = 0;
        if (n.esq != null) {
            x += contaFolhasPares(n.esq);
        }
        if (n.dir != null) {
            x += contaFolhasPares(n.dir);
        }
        if ((n.esq == null) && (n.dir == null)) {
            if (n.valor % 2 == 0) {
                x++;
            }
        }
        return x;
    }

    static void removeFolhasPares(noA n) {
        if (n.esq != null) {
            if (temFilho(n.esq)) {
                removeFolhasPares(n.esq);
            } else if (n.esq.valor % 2 == 0) {
                n.esq = null;
            }
        }
        if (n.dir != null) {
            if (temFilho(n.dir)) {
                removeFolhasPares(n.dir);
            } else if (n.dir.valor % 2 == 0) {
                n.dir = null;
            }
        }
    }

    static void removePares() {
        do {
            removeFolhasPares(inicio);
        } while (contaFolhasPares(inicio) != 0);
    }

    static int quantosTemUmFilho(noA n) {
        int x = 0;
        if (n != null) {
            if (n.esq != null) {
                x += quantosTemUmFilho(n.esq);
            }
            if (n.dir != null) {
                x += quantosTemUmFilho(n.dir);
            }
            if ((n.esq == null && n.dir != null) || (n.dir == null && n.esq != null)) {
                x++;
            }
        }
        return x;
    }

    static int contaFolhasMultiplo357(noA n) {
        int x = 0;
        if (n.esq != null) {
            x += contaFolhasMultiplo357(n.esq);
        }
        if (n.dir != null) {
            x += contaFolhasMultiplo357(n.dir);
        }
        if ((n.esq == null) && (n.dir == null)) {
            if ((n.valor % 3 == 0) || (n.valor % 5 == 0) || (n.valor % 7 == 0)) {
                x++;
            }
        }
        return x;
    }

    static void removeFolhas357(noA n) {
        if (n.esq != null) {
            if (temFilho(n.esq)) {
                removeFolhas357(n.esq);
            } else if ((n.esq.valor % 3 == 0) || (n.esq.valor % 5 == 0) || (n.esq.valor % 7 == 0)) {
                n.esq = null;
            }
        }
        if (n.dir != null) {
            if (temFilho(n.dir)) {
                removeFolhas357(n.dir);
            } else if ((n.dir.valor % 3 == 0) || (n.dir.valor % 5 == 0) || (n.dir.valor % 7 == 0)) {
                n.dir = null;
            }
        }
    }

    static void remove357() {
        do {
            removeFolhas357(inicio);
        } while (contaFolhasMultiplo357(inicio) != 0);
    }

    static void removeTodos(noA n) {
        int x = 5;
        if (n != null) {
            if (n.dir != null) {
                removeTodos(n.dir);
            }
            if (n.esq != null) {
                removeTodos(n.esq);
            }
            if (n.valor % x == 0) {
                removeNo(inicio, n.valor);
            }
        }
    }
    static void remover() {
        int x = 5;
        if (inicio.valor % x == 0) {
            if ((inicio.esq == null) && (inicio.dir == null)) {
                inicio = null;
            } else if ((inicio.esq != null) && (inicio.dir != null)) {
                removeDoisFilhos(inicio, x);
            } else if (inicio.esq == null) {
                inicio = inicio.dir;
            } else {
                inicio = inicio.esq;
            }
        }
        removeTodos(inicio);
    }
    static void remover357() {
        int x = 5;
        int y = 3;
        int z = 7;
        if (inicio.valor % x == 0 && y == 0 & z == 0) {
            if ((inicio.esq == null) && (inicio.dir == null)) {
                inicio = null;
            } else if ((inicio.esq != null) && (inicio.dir != null)) {
                removeDoisFilhos(inicio, x);
            } else if (inicio.esq == null) {
                inicio = inicio.dir;
            } else {
                inicio = inicio.esq;
            }
        }
        removeTodos(inicio);
    }

}
