package fosalgo;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;

public class CraftingCode040 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private static Canvas canvas = null;

    /**
     * @throws HeadlessException
     */
    public CraftingCode040() throws HeadlessException {
        // TODO Auto-generated constructor stub
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(1000, 680);
        this.setContentPane(getJContentPane());
        this.setTitle("Main-Main");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CraftingCode040 thisClass = new CraftingCode040();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
                thisClass.setLocationRelativeTo(null);
            }
        });
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
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

            KeyHandler keyHandler = new KeyHandler();
            //this.addKeyListener(keyHandler);
            this.addKeyListener(keyHandler);

            TranslateHandler translateHandler = new TranslateHandler();
            canvas.addMouseListener(translateHandler);
            canvas.addMouseMotionListener(translateHandler);

            ScaleHandler scaleHandler = new ScaleHandler();
            canvas.addMouseWheelListener(scaleHandler);
        }
        return canvas;
    }

    private static class KeyHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == 37) {
                canvas.keKiri();
            } else if (keyCode == 38) {
                canvas.keBawah();
            } else if (keyCode == 39) {
                canvas.keKanan();
            } else if (keyCode == 40) {
                canvas.keAtas();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

    }//end of class KeyHandler

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

    //class Canvas    
    public class Canvas extends JPanel {

        double translateX;
        double translateY;
        double scale;
        int posX = 1, posY = 1;
        int lebar = 1300;
        int deltaLebar = 50;
        int[][] history = new int[(lebar / deltaLebar)][(lebar / deltaLebar)];

        Canvas() {
            translateX = 0;
            translateY = 0;
            scale = 1;
            setOpaque(false);
            setDoubleBuffered(true);
            history[posX][posY] = 1;
        }

        public void keKiri() {
            posX--;
            if ((posX >= 0) && (posX < history.length) && (posY >= 0) && (posY < history[0].length)) {
                history[posX][posY]++;
            }
            repaint();
        }

        public void keKanan() {
            posX++;
            if ((posX >= 0) && (posX < history.length) && (posY >= 0) && (posY < history[0].length)) {
                history[posX][posY]++;
            }
            repaint();
        }

        public void keAtas() {
            posY++;
            if ((posX >= 0) && (posX < history.length) && (posY >= 0) && (posY < history[0].length)) {
                history[posX][posY]++;
            }
            repaint();
        }

        public void keBawah() {
            posY--;
            if ((posX >= 0) && (posX < history.length) && (posY >= 0) && (posY < history[0].length)) {
                history[posX][posY]++;
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
            //bikin gridnya
            ourGraphics.setColor(Color.GRAY);
            float gridWidth = 0;
            float[] dash = {10, 5, 10, 5};
            Stroke stroke1 = new BasicStroke(gridWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0);
            ourGraphics.setStroke(stroke1);
            //---------------------------------------------------------------------------------------------------
            for (int i = 0; i <= lebar; i += deltaLebar) {
                ourGraphics.drawLine(0, i, lebar, i);
                ourGraphics.drawLine(i, 0, i, lebar);
            }

            for (int i = 0; i < history.length; i++) {
                for (int j = 0; j < history[i].length; j++) {
                    if (history[i][j] >= 8) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.8F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 7) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.7F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 6) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.6F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 5) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.5F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 4) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.4F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 3) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.3F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 2) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.2F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    } else if (history[i][j] >= 1) {
                        ourGraphics.setColor(Color.red);
                        ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.1F));
                        ourGraphics.fillRect(i * deltaLebar, j * deltaLebar, deltaLebar, deltaLebar);
                    }
                }
            }
            ourGraphics.setColor(Color.red);
            ourGraphics.setComposite(AlphaComposite.SrcOver.derive(0.9F));
            ourGraphics.fillRect(posX * deltaLebar, posY * deltaLebar, deltaLebar, deltaLebar);
            ourGraphics.dispose();
        }//end of paint
    }//end of class

}
