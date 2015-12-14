package helpers;

import org.openqa.selenium.Dimension;

public class LayoutProvider {
    public static final Dimension[] DESKTOP = { new Dimension(1920, 1080), new Dimension(1211, 1050) };
    public static final Dimension[] LAPTOP = { new Dimension(1210, 1024), new Dimension(779, 768) };
    public static final Dimension[] TABLET = { new Dimension(778, 1024), new Dimension(491, 800) };
    public static final Dimension[] MOBILE = { new Dimension(490, 768), new Dimension(360, 640) };

    public static Dimension getRandomResolution(Dimension[] dim) {
        return countRandomSize(dim[0], dim[1]);
    }

    private static Dimension countRandomSize(Dimension minimum, Dimension maximum) {
        double k = Math.random();
        int width = (int) (k * (Math.abs(maximum.getWidth() - minimum.getWidth()))
                + Math.min(maximum.getWidth(), minimum.getWidth()));
        int height = (int) (k * (Math.abs(maximum.getHeight() - minimum.getHeight()))
                + Math.min(maximum.getHeight(), minimum.getHeight()));
        return new Dimension(width, height);
    }
}
