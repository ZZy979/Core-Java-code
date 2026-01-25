package splitPane;

import javax.swing.ImageIcon;

import static util.ResourceUtil.getImageIcon;

/**
 * Describes a planet.
 */
public class Planet {
    private String name;
    private double radius;
    private int moons;
    private ImageIcon image;

    /**
     * Constructs a planet.
     * @param n the planet name
     * @param r the planet radius
     * @param m the number of moons
     */
    public Planet(String n, double r, int m) {
        name = n;
        radius = r;
        moons = m;
        image = getImageIcon("/" + name + ".gif");
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets a description of the planet.
     * @return the description
     */
    public String getDescription() {
        return "Radius: " + radius + "\nMoons: " + moons + "\n";
    }

    /**
     * Gets an image of the planet.
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }
}
