package create.topic;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Map;

public class Ex1CreateTopic {

    public static void main(String[] args) throws Exception {
        Map<String, Object> properties = Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091");

        try (var client = Admin.create(properties)) {
            var requestedTopic = new NewTopic("ex1-topic", 2, (short) 2);
            var requestedTopic2 = new NewTopic("ex1-topic2", 2, (short) 6);

            var topicResult = client.createTopics(List.of(requestedTopic, requestedTopic2));
            // не факт, что топик уже создан, пользоваться им нельзя

            topicResult.topicId("ex1-topic").get();
            topicResult.topicId("ex1-topic2").get();
            // топик точно создан, можете пользоваться
        }
    }
}
