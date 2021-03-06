package trianguloapp;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ismael López Martínez
 * Sir Pinski Triangulo
 */
public class JFormTriangulo extends javax.swing.JFrame {

    /**
     * Creates new form JFormTriangulo
     */
    public JFormTriangulo() {
        initComponents();
        puntos= new ArrayList<>();
        creaTriangulo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1024, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFormTriangulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFormTriangulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFormTriangulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFormTriangulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              JFormTriangulo t=  new JFormTriangulo(); 
             
              t.setVisible(true);
               /*Aqui mando a llamar los puntos que generare en aleatorio*/
            
            }
        });
    }
    
    int x[]={20,420,220};
    int y[]={430,430,80};
    Graphics2D g2d;
    ArrayList<Point2D> puntos;
    Polygon triangulo;

    
    
    
    /***
     * Dibuja el triangulo
     */
    private void creaTriangulo(){
        triangulo= new Polygon(x,y,3);
        puntos.clear();
        for (int i = 0; i < triangulo.xpoints.length; i++) {
                //System.out.printf("(%d,%d)",triangulo.xpoints[x],triangulo.ypoints[x]);
                Point2D p=new Point2D.Double(triangulo.xpoints[i],triangulo.ypoints[i]);
                puntos.add(p);
        }
    }
    
    /***
     * Reescritura del metodo paint aqui se invoca los random para generar los puntos
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        
        g2d= (Graphics2D)g;
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.black);
        g2d.drawPolygon(triangulo);
        puntos.forEach((p) -> {
          g2d.drawString(String.format("* (%d,%d)", (int)p.getX(),(int)p.getY()), (int)p.getX(),(int)p.getY());
        });
       
       /*Integer iteraciones=0;
       iteraciones=graficarPunto(new Point2D.Double( 254,249),iteraciones);
       System.out.println("iteraciones:"+iteraciones);*/
       random(1000);
       
     }
    
    /**
     * Genera puntos aleatorios
     * @param cantidadDePuntos 
     */
    public void random(int cantidadDePuntos){
        
        Random aleatorio = new Random(System.currentTimeMillis());
        ArrayList<String> posiciones= new ArrayList<>();
         // Produce numeros aleatorios dentro del plano cartesiano 
        try{
            for(int i=0;i<=cantidadDePuntos;i++){
                    System.out.printf("punto %d de %d %s",i,cantidadDePuntos,System.lineSeparator());
            double aleatorioX=aleatorio.nextInt(420);
            double aleatorioY=aleatorio.nextInt(430);
            Integer iteraciones=0;
            iteraciones=this.graficarPunto(new Point2D.Double(aleatorioX,aleatorioY),iteraciones);
            //Solo si sobrevivio más de 10 iteraciones los dibujo
            if(iteraciones>=10){
                posiciones.add(aleatorioX+","+aleatorioY+","+iteraciones.toString());
                dibujaPunto(new Point2D.Double(aleatorioX,aleatorioY),"*",Color.red,"%s");
                //System.out.printf("iteraciones totales (%s,%s) :%d%s",aleatorioX,aleatorioY,iteraciones,System.lineSeparator());
                System.out.printf("%s,%s :%d%s",aleatorioX,aleatorioY,iteraciones,System.lineSeparator());
             }
        }
     }catch(Exception e){
         e.printStackTrace();

     }
     //System.out.println("Total de posiciones generadas de forma aleatoria: "+cantidadDePuntos);
                 

   }
    
    /**
     * Método que grafica y calcula el nuevo en base a la formula de punto medio
     * @param px
     * @param iteraciones
     * @return 
     */
    
    public int graficarPunto(Point2D px, Integer iteraciones){
       
        //Color random=randomColor();
      
        iteraciones++;
        try{
        if(triangulo.contains(px)){  
            Point2D aristaCercano=devuelveAristaCercano(px);
            Point2D nuevoPunto=devuelveNuevoPunto(aristaCercano,px);
            if(px.equals(nuevoPunto))
                return iteraciones;
           //dibujaPunto(aristaCercano,"a",random,"%s");
           //dibujaPunto(px,"*",Color.blue,"%s");
           //dibujaPunto(nuevoPunto,"c",random,"%s");
           //g2d.drawLine((int)aristaCercano.getX(),(int) aristaCercano.getY(), (int)nuevoPunto.getX(), (int)nuevoPunto.getY());
           //g2d.drawLine((int)aristaCercano.getX(),(int) aristaCercano.getY(), (int)px.getX(), (int)px.getY());

           return graficarPunto(nuevoPunto,iteraciones);
        }
        else 
            return iteraciones;
        }catch(Exception e){
         return iteraciones;   
        }
        
    }
    
    /**
     * Sibuja un putno con el api Java2D
     * @param px
     * @param c
     * @param color
     * @param format 
     */
    private void dibujaPunto(Point2D px,String c, Color color,String format){
        //sg2d.setColor(color);
        g2d.drawString(String.format(format,c, (int)px.getX(),(int)px.getY()), (int)px.getX(),(int)px.getY());
    }
    
    
    /***
     *Devuelve el punto más cercano en base a un punto de referencia 
     *
     * @param px
     * @return 
     */
    public Point2D devuelveAristaCercano(Point2D px){
        Point2D arista=null;
         Double menor=Double.MAX_VALUE;
         for (Point2D punto:puntos) {
             if(punto.distance(px)<menor){
                 menor=punto.distance(px);
                 arista=punto;
             }
            //System.out.printf("Distancia de punto (%f,%f) es: %f %s",punto.getX(),punto.getY(), punto.distance(px),System.lineSeparator());
         }
       //System.out.printf("Punto menor (%f,%f) de (%f,%f)  con distancia: %f %s",arista.getX(),arista.getY(),px.getX(),px.getY(), menor,System.lineSeparator());
        return arista;
    }
    
    
    
    /**
     * Devuelve el nuevo punto despejando la formula de punto medio
     * @param verticeCercano
     * @param px
     * @return 
     */
     public Point2D devuelveNuevoPunto(Point2D verticeCercano, Point2D px){
        Point2D nuevoPunto=null;
         double xn=(px.getX()*2)-verticeCercano.getX();
         double yn=(px.getY()*2)-verticeCercano.getY();
         nuevoPunto=new Point2D.Double(xn,yn);
         return nuevoPunto;
    }
    
    public Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
