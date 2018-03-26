package cn.waynechu.sort.quick.threeways;

import cn.waynechu.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @author waynechu
 * Created 2018-03-26 13:18
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private ThreeWaysQuickSortData data;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        // 设置Content Pane内容面板
        this.setContentPane(canvas);
        // 窗口大小不可变
        setResizable(false);
        // 默认关闭操作
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口可见
        setVisible(true);
        // 调整frame大小以适合子组件的首选大小和布局
        pack();
    }

    public void render(ThreeWaysQuickSortData data) {
        this.data = data;
        // 重新绘制
        repaint();
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 启用双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 获得绘图上下文环境
            Graphics2D graphics2D = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            // 设置画笔宽度
            AlgoVisHelper.setStrokeWidth(graphics2D, 1);
            // 绘制图形
            if (data != null) {
                int w = canvasWidth / data.getNumbers().length;
                for (int i = 0; i < data.getNumbers().length; i++) {
                    // 为不同排序状态设置不同的颜色
                    if (i > data.left && i <= data.right) {
                        // 当前正在处理的区间
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.BlueGrey);
                    } else {
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Grey);
                    }
                    if (i <= data.lessThan && i >= data.left + 1) {
                        // 小于基准数的元素区间
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Amber);
                    }
                    if (i >= data.greaterThan && i <= data.right) {
                        // 大于基准数的元素区间
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Orange);
                    }
                    if (i > data.lessThan && i < data.currentElement) {
                        // 等于基准数的元素区间已经是排序好的区间，和fixedPivot颜色相同
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Blue);
                    }
                    if (i == data.currentPivot) {
                        // 当前选取的基准数
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Red);
                    }
                    if (data.fixedPivot[i]) {
                        // 找到正确位置的元素
                        AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Blue);
                    }
                    AlgoVisHelper.fillRectangle(graphics2D, i * w, canvasHeight - data.getNumbers()[i], w - 1, data.getNumbers()[i]);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
