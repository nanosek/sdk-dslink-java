package org.dsa.iot.dslink.methods;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.dsa.iot.dslink.node.Node;
import org.dsa.iot.dslink.util.StreamState;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * @author Samuel Grenier
 */
@AllArgsConstructor
public class RemoveMethod extends Method {
    @NonNull
    private final Node node;

    /**
     * References a configuration or attribute. Can be null to reference
     * the node itself.
     */
    private final String specialPath;

    @Override
    public JsonArray invoke(JsonObject request) {
        boolean config = false;
        String name = null;
        if (specialPath != null)
            name = specialPath.substring(1);
        if (name != null && specialPath.startsWith("$"))
            config = true;
        else if (name != null && specialPath.startsWith("@"))
            config = false;
        else if (name != null)
            throw new RuntimeException("Invalid node prefix: " + specialPath);

        if (name != null && config)
            node.setConfiguration(name, null);
        else if (name != null)
            node.setAttribute(name, null);
        else
            node.setValue(null, false);

        setState(StreamState.CLOSED);
        return null;
    }
}
