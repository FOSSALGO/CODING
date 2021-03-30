package fosalgo;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CraftingCode039 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private static Canvas canvas = null;

    public CraftingCode039() throws HeadlessException {
        // TODO Auto-generated constructor stub
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(1000, 680);
        this.setContentPane(getJContentPane());
        this.setTitle("Main-Main Graph");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CraftingCode039 thisClass = new CraftingCode039();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
                thisClass.setLocationRelativeTo(null);
            }
        });
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getCanvas(), BorderLayout.CENTER);//
        }
        return jContentPane;
    }

    private Canvas getCanvas() {
        if (canvas == null) {
            canvas = new Canvas();

            TranslateHandler translateHandler = new TranslateHandler();
            canvas.addMouseListener(translateHandler);
            canvas.addMouseMotionListener(translateHandler);

            ScaleHandler scaleHandler = new ScaleHandler();
            canvas.addMouseWheelListener(scaleHandler);
        }
        return canvas;
    }

    private static class TranslateHandler implements MouseListener, MouseMotionListener {

        private int lastOffsetX;
        private int lastOffsetY;

        public void mousePressed(MouseEvent e) {
            // capture starting point
            lastOffsetX = e.getX();
            lastOffsetY = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            // new x and y are defined by current mouse location subtracted
            // by previously processed mouse location
            int newX = e.getX() - lastOffsetX;
            int newY = e.getY() - lastOffsetY;

            // increment last offset to last processed by drag event.
            lastOffsetX += newX;
            lastOffsetY += newY;

            // update the canvas locations
            canvas.translateX += newX;
            canvas.translateY += newY;

            // schedule a repaint.
            canvas.repaint();
        }

        public void mouseClicked(MouseEvent e) {
            int X = e.getX();
            int Y = e.getY();
            double translateX = canvas.translateX;
            double translateY = canvas.translateY;
            double scale = canvas.scale;
            int titik_y = (int) Math.floor((Y - translateY) / (scale * canvas.delta1));
            int titik_x = (int) Math.floor((X - translateX) / (scale * canvas.delta1));
            if (titik_x < 0) {
                titik_x = 0;
            }
            if (titik_y < 0) {
                titik_y = 0;
            }
            if (titik_x > canvas.node.length) {
                titik_x = canvas.node.length;
            }
            if (titik_y > canvas.node[0].length) {
                titik_y = canvas.node[0].length;
            }
            canvas.addNode(titik_x, titik_y);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }//end of class TranslateHandler

    private static class ScaleHandler implements MouseWheelListener {

        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                canvas.scale += (.1 * e.getWheelRotation());
                canvas.scale = Math.max(0.00001, canvas.scale);
                canvas.repaint();
            }
        }
    }//end of class ScaleHandler

    //Canvas.java
    public class Canvas extends JPanel {

        double translateX;
        double translateY;
        double scale;
        //int posX=1,posY=1;
        int lebar = 1300;
        int delta1 = 20;
        int delta2 = 50;
        int delta3 = 100;

        int[][] node = new int[(lebar / delta1)][(lebar / delta1)];

        Canvas() {
            translateX = 0;
            translateY = 0;
            scale = 1;
            setOpaque(false);
            setDoubleBuffered(true);
        }

        public void addNode(int x, int y) {
            if ((x >= 0) && (y >= 0) && (x < node.length) && (y < node[0].length)) {
                node[x][y] = 1;
            }
            repaint();
        }

        public void paint(Graphics g) {
            //---------------------------------------------------------------------------------------------------
            AffineTransform tx = new AffineTransform();
            tx.translate(translateX, translateY);
            tx.scale(scale, scale);
            Graphics2D ourGraphics = (Graphics2D) g;
            ourGraphics.setColor(Color.WHITE);
            ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
            ourGraphics.fillRect(0, 0, getWidth(), getHeight());
            ourGraphics.setTransform(tx);
            ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            //---------------------------------------------------------------------------------------------------
            ourGraphics.setColor(Color.gray);
            float gridWidth = 0;
            float[] dash1 = {10, 5, 10, 5};
            Stroke stroke1 = new BasicStroke(gridWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash1, 0);
            float[] dash2 = {20, 5, 20, 5};
            Stroke stroke2 = new BasicStroke(gridWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash2, 0);
            float[] dash3 = {4, 2, 4, 2};
            Stroke stroke3 = new BasicStroke(gridWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash3, 0);

            //---------------------------------------------------------------------------------------------------
            //bikin gridnya
            ourGraphics.setColor(Color.black);
            for (int i = 0; i <= lebar; i += delta1) {
                ourGraphics.setStroke(stroke3);
                ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.2F));
                ourGraphics.drawLine(0, i, lebar, i);
                ourGraphics.drawLine(i, 0, i, lebar);
            }
            for (int i = 0; i <= lebar; i += delta2) {
                ourGraphics.setStroke(stroke2);
                ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.4F));
                ourGraphics.drawLine(0, i, lebar, i);
                ourGraphics.drawLine(i, 0, i, lebar);
            }
            for (int i = 0; i <= lebar; i += delta3) {
                ourGraphics.setStroke(stroke1);
                ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
                ourGraphics.drawLine(0, i, lebar, i);
                ourGraphics.drawLine(i, 0, i, lebar);
            }

            //bikin edge dan nodenya
            for (int i = 0; i < node.length; i++) {
                for (int j = 0; j < node[i].length; j++) {
                    if (node[i][j] == 1) {
                        int x0 = delta1 * i;
                        int y0 = delta1 * j;
                        int x1 = (delta1 / 2) + x0;
                        int y1 = (delta1 / 2) + y0;
                        //bikin garis edge
                        for (int k = 0; k < node.length; k++) {
                            for (int l = 0; l < node[k].length; l++) {
                                if ((node[k][l] == 1) && (!((k == j) && (l == i)))) {
                                    int x2 = (delta1 * k) + (delta1 / 2);
                                    int y2 = (delta1 * l) + (delta1 / 2);
                                    ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
                                    ourGraphics.setStroke(new BasicStroke(1));
                                    ourGraphics.setColor(Color.decode("#3498db"));
                                    ourGraphics.drawLine(x1, y1, x2, y2);
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < node.length; i++) {
                for (int j = 0; j < node[i].length; j++) {
                    if (node[i][j] == 1) {
                        int x0 = delta1 * i;
                        int y0 = delta1 * j;
                        int x1 = -2 + x0;
                        int y1 = -2 + y0;
                        //bikin lingkaran node                    
                        ourGraphics.setColor(Color.decode("#e74c3c"));
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
                        ourGraphics.fillOval(x0, y0, delta1, delta1);
                        ourGraphics.setStroke(stroke1);
                        ourGraphics.setColor(Color.BLACK);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
                        ourGraphics.drawOval(x1, y1, 3 + delta1, 3 + delta1);
                    }
                }
            }
            ourGraphics.dispose();
        }//end of paint
    }//end of class
}
