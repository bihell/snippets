package wordCounter;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class TopologyFields {
	public static void main(String[] args) throws InterruptedException {

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReader());
		builder.setBolt("word-counter", new WordCounter(),2)
				.fieldsGrouping("word-reader", new Fields("word"));


		Config conf = new Config();
		conf.put("fileToRead", "/Users/haseochen/Downloads/Untitled-1.hql");
		conf.put("dirToWrite", "/Users/haseochen/Downloads/wordCountoutput/");
		conf.setDebug(true);

		LocalCluster cluster = new LocalCluster();
		try{
			cluster.submitTopology("WordCounter-Topology", conf, builder.createTopology());
			Thread.sleep(10000);
		}
		finally {
			cluster.shutdown();
		}

	}

}
