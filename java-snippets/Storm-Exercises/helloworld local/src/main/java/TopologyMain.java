import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;


public class TopologyMain {
    public static void main(String[] args) throws InterruptedException {

        //Build Topology
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("Yahoo-Finance-Spout", new yfSpout());
        builder.setBolt("Yahoo-Finance-Bolt", new yfBolt())
                .shuffleGrouping("Yahoo-Finance-Spout");

        StormTopology topology = builder.createTopology();
        //Configuration
        Config conf = new Config();
        conf.setDebug(true);
        conf.put("fileToWrite", "/Users/haseochen/Desktop/outputa.txt");

        //Submit Topology to cluster
        LocalCluster cluster = new LocalCluster();
        try {
            cluster.submitTopology("MyTopology", conf, topology);  // 本地执行
//            StormSubmitter.submitTopology("MyTopology", conf, topology);
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
