package lv.testtask.service.util;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lv.testtask.validation.IpRestrictionData;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class HazelcastHelper {


    private HazelcastInstance getHazelcastClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("localhost");
        final GroupConfig groupConfig = new GroupConfig();
        groupConfig.setName("name");
        groupConfig.setPassword("myPassword");
        clientConfig.setGroupConfig(groupConfig);
        return  HazelcastClient.newHazelcastClient(clientConfig);
    }

    //TODO remove magic strings
    public IpRestrictionData getIpRestrictionDataForIp(String ip) {
        final HazelcastInstance hazelcastClient = getHazelcastClient();
        final IMap<String, IpRestrictionData> ipRestrictionMap = hazelcastClient.getMap("ipRestrictionMap");
        final IpRestrictionData ipRestrictionData = ipRestrictionMap.get(ip);
        hazelcastClient.shutdown();
        if (ipRestrictionData == null) {
            return  new IpRestrictionData(DateTime.now(), 0);


        }
        return ipRestrictionData;

    }

    public void storeIpRestrictionData(String ip, IpRestrictionData restrictionData) {
        final HazelcastInstance hazelcastClient = getHazelcastClient();
        final IMap<String, IpRestrictionData> ipRestrictionMap = hazelcastClient.getMap("ipRestrictionMap");
        ipRestrictionMap.put(ip, restrictionData);
        hazelcastClient.shutdown();
    }
}
