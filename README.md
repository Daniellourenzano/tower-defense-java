# tower-defense-java
a tower defense made in java.
// INIMIGO:
public class Inimigo {
    
   public float vida;
   float tempo_de_chegada_segundos;
   float altura;
   float comprimento;
   String poder;
   int posicaox = 0;
   int posicaoy = 0;
   
   
   void receber_dano(float V,float D, String torre){
       float s;
       s = V - D;
       this.vida = s; 
       System.out.println("a torre " + torre + "deu " + D + "de dano no inimigo alfa sobrando de vida: " + s);
   }
   
   public Inimigo(float v, float t, float a, float c, String p){
       this.vida = v;
       this.tempo_de_chegada_segundos = t;
       this.altura = a;
 
       this.comprimento = c;
   }
   
  
       
    void percurso(float D, String torrenome) throws InterruptedException{
        for(int i = 0; i < 5; i++){
        System.out.println("posição inimigo:(" + this.posicaox + ", " + this.posicaoy + ")");
        this.posicaox = this.posicaox + 1;
        Thread.sleep((long) (this.tempo_de_chegada_segundos * 10));
        }
        System.out.println("posição inimigo:(" + this.posicaox + ", " + this.posicaoy + ")");
        
        for(int i = 0; i <= 3; i++){
        this.posicaoy = this.posicaoy + 1;
        Thread.sleep((long) (this.tempo_de_chegada_segundos * 10));
        System.out.println("posição inimigo:(" + this.posicaox + ", " + this.posicaoy + ")");
        }
        
        
        float s;
        for(int i = 0; i < 5; i++){
        this.posicaox = this.posicaox + 1;
        Thread.sleep((long) (this.tempo_de_chegada_segundos * 10));
       
      
       s = this.vida - D;
       this.vida = s; 
       if (s>0){
       System.out.println("a torre " + torrenome + " deu " + D + " de dano no inimigo alfa sobrando de vida: " + s);
       }
        
       System.out.println("posição inimigo:(" + this.posicaox + ", " + this.posicaoy + ")");
        
      
       if(s < 0) {
           System.out.println("o inimigo morreu");
       i = 6;}
       else {
           System.out.println("a torre " + torrenome + " deu " + D + " de dano no inimigo");
       }
                
        }
        
        if (this.vida > 0){
         System.out.println("chegou na torre com " + this.vida + " pontos de vida");}
       
    }
       
               }


   //TORRE
   public class torre {
    public float altura;

    public float comprimento;
    public String poder;
    public float dano_por_segundo;
    int posicaox;
    int posicaoy;
    
    public torre(float a, float c, float d, String p, int x, int y){
        this.altura = a;
        this.comprimento = c;
        this.dano_por_segundo = d;
        this.poder = p;
        this.posicaox = x;
        this.posicaoy = y;
    }
    
    
}

//PRINCIPAL
public class Projeto {

    public static void main(String[] args) throws InterruptedException {
    torre torre_alfa = new torre(20, 10, 30, "Bola de fogo", 10, 0);
    Inimigo inimigo_alfa = new Inimigo(500, 120, 5, 5, "toxico");
    
   
   inimigo_alfa.percurso(torre_alfa.dano_por_segundo, "alfa");
    
    }}

   
