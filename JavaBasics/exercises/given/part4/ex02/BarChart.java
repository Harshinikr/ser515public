import java.awt.*;
import java.util.Vector;

@SuppressWarnings("serial")
public class BarChart extends Canvas {
    protected Vector<Integer> data = new Vector<>();
    protected Vector<String> labels = new Vector<>();
    protected Vector<Color> colors = new Vector<>();

    public void setData(Vector<Integer> data) {
        this.data = data;
    }

    public void setLabels(Vector<String> labels) {
        this.labels = labels;
    }

    public void setColors(Vector<Color> colors) {
        this.colors = colors;
    }

    public void paint(Graphics g) {
        if (data == null || data.size() == 0) {
            g.drawString("No data available.", 50, 50);
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int padding = 40;
        int barWidth = (width - 2 * padding) / data.size();
        int maxVal = 0;

        for (int val : data) {
            if (val > maxVal) maxVal = val;
        }

        int x = padding;
        for (int i = 0; i < data.size(); i++) {
            int value = data.get(i);
            String label = labels.get(i);
            Color color = colors.get(i);

            int barHeight = (int)(((double)value / maxVal) * (height - 2 * padding));
            int y = height - padding - barHeight;

            g.setColor(color);
            g.fillRect(x, y, barWidth - 10, barHeight);

            g.setColor(Color.black);
            g.drawRect(x, y, barWidth - 10, barHeight);
            g.drawString(label, x + 5, height - 10);

            x += barWidth;
        }
    }
}
