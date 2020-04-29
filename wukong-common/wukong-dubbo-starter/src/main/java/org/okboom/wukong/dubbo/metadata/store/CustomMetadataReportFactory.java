package org.okboom.wukong.dubbo.metadata.store;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.metadata.report.MetadataReport;
import org.apache.dubbo.metadata.report.support.AbstractMetadataReportFactory;

/**
 * @author tookbra
 * @date 2020/4/28
 * @description
 */
public class CustomMetadataReportFactory extends AbstractMetadataReportFactory {
    @Override
    protected MetadataReport createMetadataReport(URL url) {
        return new CustomMetadataReport(url);
    }
}
