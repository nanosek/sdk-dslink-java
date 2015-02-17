package org.dsa.iot.dslink.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dsa.iot.dslink.responses.Response;
import org.dsa.iot.dslink.util.Client;

/**
 * Posted from a completed response
 * @author Samuel Grenier
 */
@Getter
@AllArgsConstructor
public class ResponseEvent extends Event {

    private final Client client;
    private final int rid;
    private final String name;
    private final Response<?> response;
}
