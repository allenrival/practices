package allen_airplane;

import lombok.Data;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年05月09日 14:59
 */
@Data
public class Model {
    private int startx;
    private int starty;
    private int startz;
    private int[][][] model;

    public Model(int startx, int starty, int startz, int[][][] matrix) {
        this.startx = startx;
        this.starty = starty;
        this.startz = startz;
        this.model = matrix;
    }

}
