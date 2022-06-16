package proj4allen;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;
/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月08日 15:40
 */
public class Test1 {
    public static void main(String[] args) {
        double[] doubles = WGS84To2436(624387.8727218998, 3352760.376813505);
        System.out.println(doubles[0]+" "+doubles[1]);
    }

    public static double[] WGS84To2436(double x,double y){
        CRSFactory crsFactory = new CRSFactory();
        //源坐标系统
        String SourceCRS = "32649";
        String SourceCRS_params="+proj=utm +zone=49 +datum=WGS84 +units=m +no_defs ";
        CoordinateReferenceSystem source = crsFactory.createFromParameters(SourceCRS, SourceCRS_params);

        //目标坐标系统
        String TargetCRS = "4326";
        String TargetCRS_params="+proj=longlat +datum=WGS84 +no_defs ";
        CoordinateReferenceSystem target = crsFactory.createFromParameters(TargetCRS, TargetCRS_params);

        //定义转换类WGS84转2436
        CoordinateTransformFactory ctf = new CoordinateTransformFactory();
        CoordinateTransform transform = ctf.createTransform(source,target);

        //WGS84坐标系转换
        ProjCoordinate projCoordinate = new ProjCoordinate(x,y);
        transform.transform(projCoordinate, projCoordinate);
        //projCoordinate.x 和 projCoordinate.y 就是转换结果
        return new double[]{projCoordinate.x,projCoordinate.y};
    }

}