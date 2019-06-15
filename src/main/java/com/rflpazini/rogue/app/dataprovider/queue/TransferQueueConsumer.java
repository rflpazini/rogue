package com.rflpazini.rogue.app.dataprovider.queue;

import com.rflpazini.rogue.app.entrypoint.model.Transfer;
import com.rflpazini.rogue.domain.usecase.TransferService;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@MessageDriven
public class TransferQueueConsumer implements MessageListener {

  @Inject TransferService transferService;

  @Override
  public void onMessage(Message message) {

    TextMessage textMessage = (TextMessage) message;

    try {
      String incomingText = textMessage.getText();

      Jsonb jsonb = JsonbBuilder.create();

      Transfer parsedMessage = jsonb.fromJson(incomingText, Transfer.class);
      transferService.updateTransfer(parsedMessage);

    } catch (JMSException e) {
      System.err.println(e.getMessage());
    }
  }
}
