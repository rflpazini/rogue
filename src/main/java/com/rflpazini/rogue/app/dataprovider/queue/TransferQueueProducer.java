package com.rflpazini.rogue.app.dataprovider.queue;

import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import com.rflpazini.rogue.domain.dataprovider.QueueProvider;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Stateless
public class TransferQueueProducer implements QueueProvider<Transfer> {

  @Resource(lookup = "jms/JmsFactory")
  private ConnectionFactory jmsFactory;

  @Resource(lookup = "jms/JmsQueue")
  private Queue jmsQueue;

  public void post(Transfer transfer) {
    try (Connection connection = jmsFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(jmsQueue)) {

      TextMessage message = session.createTextMessage(convertTransferToJson(transfer));
      producer.send(message);

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  private String convertTransferToJson(Transfer transfer) {
    Jsonb jsonb = JsonbBuilder.create();
    return jsonb.toJson(transfer);
  }
}
