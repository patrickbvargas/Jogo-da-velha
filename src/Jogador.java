public class Jogador {

    private String nome;
    private int pontos;
    private char simbolo;

    public Jogador (String nome, char simbolo){
        this.nome = nome;
        this.simbolo = simbolo;
        pontos = 0;
    }
    
    public String getNome(){
        return nome;
    }

    public int getPontos(){
        return pontos;
    }

    public char getSimbolo(){
        return simbolo;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPontos(int pontos){
        this.pontos = pontos;
    }

    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
    }

    public void registraPonto(){
        this.pontos += 1;
    }

    @Override
    public String toString(){
        return "Nome: " + this.nome + " - Símbolo: " + this.simbolo + " - Pontos: " + this.pontos;    
    }
    
}

