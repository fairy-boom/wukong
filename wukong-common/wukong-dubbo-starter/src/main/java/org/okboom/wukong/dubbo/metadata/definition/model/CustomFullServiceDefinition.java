package org.okboom.wukong.dubbo.metadata.definition.model;

import java.util.Map;

/**
 * @author tookbra
 */
public class CustomFullServiceDefinition extends CustomServiceDefinition {

    private Map<String, String> parameters;

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "FullServiceDefinition{" +
                "parameters=" + parameters +
                "} " + super.toString();
    }
}
