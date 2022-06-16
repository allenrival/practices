package dom_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @Description: TODO
 * @author: allen
 * @date: 2022年03月07日 9:10
 */
public class Main {
    static Document document;
    static {
        try {
            document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    static String airLineName="航线名称";
    static int nodeNumber=1; //航点数量
    static String speed="2"; //飞机执行任务飞行速度；
    static String gospeed="13";
    static class AirNode{
        String misHeading="118";//航点偏航角度
        String misShootingDistance="118"; //航点距离拍摄点的距离
        String misShootingPointLongitude="118"; //拍摄点经度
        String misShootingPointLatitude="118";  //拍摄点维度
        String misShootingPointHigh="118";  //拍摄点海拔
        String misGimbalPitch="118";  //云台俯视角度
        ArrayList<ArrayList<String>> misActions; //拍摄角度
        String misPointLongitude="118"; //航点经度
        String misPointLatitude="118";  //航点维度
        String misPointHigh="118";  //航点点海拔
    }
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\practices.xml");
        Element kml = document.createElement("kml");
        kml.setAttribute("xmlns","http://www.opengis.net/kml/2.2");

        Element dt = document.createElement("Document");

        append(dt,"name",airLineName);
        append(dt,"open","1");

        Element extendedData = document.createElement("ExtendedData");
        extendedData.setAttribute("xmlns:mis","www.dji.com");
        append(extendedData,"mis:type","Waypoint");
        dt.appendChild(extendedData);
        Element folder = document.createElement("Folder");
        append(folder,"name","waypoints");
        append(folder,"description","Waypoints in the Mission");
        StringBuilder sb = new StringBuilder();
        AirNode airNode = new AirNode();
        ArrayList<String>list=new ArrayList();
        list.add("-125");
        list.add("-5");
        list.add("拍照");
        airNode.misActions=new ArrayList<>();
        airNode.misActions.add(list);
        for(int i=1;i<=nodeNumber;i++){
            Element placemark = document.createElement("Placemark");
            append(placemark,"name","Waypoint"+i);
            append(placemark,"description","Waypoint");
            append(placemark,"visibility","1");
            Element extendedData1 = document.createElement("ExtendedData");
            extendedData1.setAttribute("xmlns:mis","www.dji.com");
            append(extendedData1,"mis:heading",airNode.misHeading);
            append(extendedData1,"mis:speed",speed);
            append(extendedData1,"mis:shootingDistance",airNode.misShootingDistance);
            append(extendedData1,"mis:shootingPointCoordinates",airNode.misShootingPointLongitude+","+airNode.misShootingPointLatitude+","+airNode.misShootingPointHigh);
            append(extendedData1,"mis:useWaylineAltitude","false");
            append(extendedData1,"mis:gimbalPitch",airNode.misGimbalPitch);
            append(extendedData1,"mis:shootingPointName",String.valueOf(i));
            append(extendedData1,"mis:turnMode","Auto");
            for (ArrayList<String> temp : airNode.misActions) {
                append(extendedData1, "mis:actions", "AircraftYaw", "param", temp.get(0), "label", "飞行器偏航角");
                append(extendedData1, "mis:actions", "GimbalPitch", "param", temp.get(1), "label", "云台俯仰角度");
                append(extendedData1, "mis:actions", "ShootPhoto", "label", temp.get(2), "targetMode", temp.get(0).equals(airNode.misHeading) && temp.get(1).equals(airNode.misGimbalPitch) ? "1" : "0");
            }
            placemark.appendChild(extendedData1);
            Element point = document.createElement("Point");
            append(point,"altitudeMode","absolute");
            append(point,"coordinates",airNode.misPointLongitude+","+airNode.misPointLatitude+","+airNode.misPointHigh);
            placemark.appendChild(point);
            folder.appendChild(placemark);
            sb.append(airNode.misPointLongitude);
            sb.append(",");
            sb.append(airNode.misPointLatitude);
            sb.append(",");
            sb.append(airNode.misPointHigh);
            sb.append(",");
        }
        dt.appendChild(folder);
        Element placemark = document.createElement("Placemark");
        append(placemark,"name","Wayline");
        append(placemark,"description","Wayline");
        append(placemark,"visibility","1");
        Element extendedData1 = document.createElement("ExtendedData");
        extendedData1.setAttribute("xmlns:mis","www.dji.com");
        append(extendedData1,"mis:autoFlightSpeed",speed);
        append(extendedData1,"mis:actionOnFinish","GoHome");
        append(extendedData1,"mis:headingMode","UsePointSetting");
        append(extendedData1,"mis:idleVel",String.valueOf(13));
        append(extendedData1,"shootingDistance",String.valueOf(2.5));
        append(extendedData1,"mis:ratio","1");
        Element drone = document.createElement("mis:droneInfo");
        append(drone,":mis:droneType","P4R");  //飞机型号
        Element droneHeight = document.createElement("mis:droneHeight");
        append(droneHeight,"mis:useAbsolute","true");
        append(droneHeight,"mis:hasTakeoffHeight","false");
        append(droneHeight,"mis:takeoffHeight","0.0");
        drone.appendChild(droneHeight);
        extendedData1.appendChild(drone);
        placemark.appendChild(extendedData1);
        Element lineString = document.createElement("LineString");
        append(lineString,"tessellate","1");
        append(lineString,"altitudeMode","absolute");
        sb.deleteCharAt(sb.length()-1);
        append(lineString,"coordinates",sb.toString());
        placemark.appendChild(lineString);
        dt.appendChild(placemark);
        kml.appendChild(dt);
        document.appendChild(kml);
        //输出xml
        TransformerFactory  factory= TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(document),new StreamResult(fos));
    }
    public static void append(Element el,String name,String content){
        Element element = document.createElement(name);
        if(content!=null) element.setTextContent(content);
        el.appendChild(element);
    }
    public static void append(Element el,String name,String content,String key,String value){
        Element element = document.createElement(name);
        if(content!=null) element.setTextContent(content);
        element.setAttribute(key,value);
        el.appendChild(element);
    }
    public static void append(Element el,String name,String content,String key,String value,String key1,String value1){
        Element element = document.createElement(name);
        if(content!=null) element.setTextContent(content);
        element.setAttribute(key,value);
        element.setAttribute(key1,value1);
        el.appendChild(element);
    }
}
