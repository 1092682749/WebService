package test;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            // wsdl地址
            call.setTargetEndpointAddress(new URL("http://localhost:8080/mycodews_war_exploded/services/HelloWSService?wsdl"));
            // 调用的方法
            call.setOperationName(new QName("http://com/dyz", "sayHello"));
            // 设置调用参数
            call.addParameter("msg", Constants.XSD_STRING, ParameterMode.IN);
            // 设置返回类型
            call.setReturnType(Constants.XSD_STRING);
            String result = (String) call.invoke(new Object[]{" my service"});
            System.out.println(result);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
