package org.okboom.wukong.dubbo.metadata.definition.model;

import org.apache.dubbo.metadata.definition.model.ServiceDefinition;
import org.apache.dubbo.metadata.definition.model.TypeDefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义服务元数据
 * @author tookbra
 */
public class CustomServiceDefinition implements Serializable {

    private static final long serialVersionUID = -2639275643956538896L;

    private String canonicalName;
    private String codeSource;
    private List<CustomMethodDefinition> methods;
    private List<TypeDefinition> types;

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getCodeSource() {
        return codeSource;
    }

    public List<CustomMethodDefinition> getMethods() {
        if (methods == null) {
            methods = new ArrayList<>();
        }
        return methods;
    }

    public List<TypeDefinition> getTypes() {
        if (types == null) {
            types = new ArrayList<>();
        }
        return types;
    }

    public String getUniqueId() {
        return canonicalName + "@" + codeSource;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public void setCodeSource(String codeSource) {
        this.codeSource = codeSource;
    }

    public void setMethods(List<CustomMethodDefinition> methods) {
        this.methods = methods;
    }

    public void setTypes(List<TypeDefinition> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "ServiceDefinition [canonicalName=" + canonicalName + ", codeSource=" + codeSource + ", methods="
                + methods + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceDefinition)) {
            return false;
        }
        ServiceDefinition that = (ServiceDefinition) o;
        return Objects.equals(getCanonicalName(), that.getCanonicalName()) &&
                Objects.equals(getCodeSource(), that.getCodeSource()) &&
                Objects.equals(getMethods(), that.getMethods()) &&
                Objects.equals(getTypes(), that.getTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCanonicalName(), getCodeSource(), getMethods(), getTypes());
    }
}
